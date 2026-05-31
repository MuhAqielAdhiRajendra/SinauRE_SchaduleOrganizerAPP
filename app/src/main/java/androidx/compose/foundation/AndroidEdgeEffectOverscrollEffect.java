package androidx.compose.foundation;

import android.content.Context;
import android.os.Build;
import android.widget.EdgeEffect;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AndroidOverscroll.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\n\b\u0001\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ3\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\"2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r0$H\u0016¢\u0006\u0004\b%\u0010&J<\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\"\u0010*\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020)\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0,\u0012\u0006\u0012\u0004\u0018\u00010-0+H\u0096@¢\u0006\u0004\b.\u0010/J\u0017\u00103\u001a\u00020\u00132\u0006\u00104\u001a\u000201H\u0000¢\u0006\u0004\b5\u00106J\u000f\u00109\u001a\u00020\rH\u0000¢\u0006\u0004\b:\u0010;J\r\u0010B\u001a\u00020\u0013H\u0000¢\u0006\u0002\bCJ\b\u0010D\u001a\u00020\u0013H\u0002J\u0017\u0010E\u001a\u00020\u00172\u0006\u0010 \u001a\u00020\rH\u0002¢\u0006\u0004\bF\u0010GJ\u0017\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bK\u0010LJ\u0017\u0010M\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bN\u0010LJ\u0017\u0010O\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bP\u0010LJ\u0017\u0010Q\u001a\u00020I2\u0006\u0010J\u001a\u00020\rH\u0002¢\u0006\u0004\bR\u0010LR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001a\u00020\u00178\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u000201X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u0014\u00102\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u001bR\u0010\u00107\u001a\u000208X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010<\u001a\u00020=X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010>\u001a\u00020?X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010A¨\u0006S"}, d2 = {"Landroidx/compose/foundation/AndroidEdgeEffectOverscrollEffect;", "Landroidx/compose/foundation/OverscrollEffect;", "context", "Landroid/content/Context;", "density", "Landroidx/compose/ui/unit/Density;", "glowColor", "Landroidx/compose/ui/graphics/Color;", "glowDrawPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "<init>", "(Landroid/content/Context;Landroidx/compose/ui/unit/Density;JLandroidx/compose/foundation/layout/PaddingValues;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "pointerPosition", "Landroidx/compose/ui/geometry/Offset;", "J", "edgeEffectWrapper", "Landroidx/compose/foundation/EdgeEffectWrapper;", "redrawSignal", "Landroidx/compose/runtime/MutableState;", "", "getRedrawSignal$foundation", "()Landroidx/compose/runtime/MutableState;", "invalidationEnabled", "", "getInvalidationEnabled$foundation$annotations", "()V", "getInvalidationEnabled$foundation", "()Z", "setInvalidationEnabled$foundation", "(Z)V", "scrollCycleInProgress", "applyToScroll", "delta", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "performScroll", "Lkotlin/Function1;", "applyToScroll-Rhakbz0", "(JILkotlin/jvm/functions/Function1;)J", "applyToFling", "velocity", "Landroidx/compose/ui/unit/Velocity;", "performFling", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "applyToFling-BMRW4eQ", "(JLkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "containerSize", "Landroidx/compose/ui/geometry/Size;", "isInProgress", "updateSize", "size", "updateSize-uvyYCjk$foundation", "(J)V", "pointerId", "Landroidx/compose/ui/input/pointer/PointerId;", "displacement", "displacement-F1C5BW0$foundation", "()J", "pointerInputNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "node", "Landroidx/compose/ui/node/DelegatableNode;", "getNode", "()Landroidx/compose/ui/node/DelegatableNode;", "invalidateOverscroll", "invalidateOverscroll$foundation", "animateToReleaseIfNeeded", "releaseOppositeOverscroll", "releaseOppositeOverscroll-k-4lQ0M", "(J)Z", "pullTop", "", "scroll", "pullTop-k-4lQ0M", "(J)F", "pullBottom", "pullBottom-k-4lQ0M", "pullLeft", "pullLeft-k-4lQ0M", "pullRight", "pullRight-k-4lQ0M", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidEdgeEffectOverscrollEffect implements OverscrollEffect {
    public static final int $stable = 0;
    private long containerSize;
    private final Density density;
    private final EdgeEffectWrapper edgeEffectWrapper;
    private boolean invalidationEnabled;
    private final DelegatableNode node;
    private long pointerId;
    private final SuspendingPointerInputModifierNode pointerInputNode;
    private long pointerPosition;
    private final MutableState<Unit> redrawSignal;
    private boolean scrollCycleInProgress;

    public /* synthetic */ AndroidEdgeEffectOverscrollEffect(Context context, Density density, long j, PaddingValues paddingValues, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, density, j, paddingValues);
    }

    public static /* synthetic */ void getInvalidationEnabled$foundation$annotations() {
    }

    private AndroidEdgeEffectOverscrollEffect(Context context, Density density, long glowColor, PaddingValues glowDrawPadding) {
        GlowOverscrollNode glowOverscrollNode;
        this.density = density;
        this.pointerPosition = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
        this.edgeEffectWrapper = new EdgeEffectWrapper(context, ColorKt.m5367toArgb8_81llA(glowColor));
        this.redrawSignal = SnapshotStateKt.mutableStateOf(Unit.INSTANCE, SnapshotStateKt.neverEqualPolicy());
        this.invalidationEnabled = true;
        this.containerSize = Size.INSTANCE.m5146getZeroNHjbRc();
        this.pointerId = PointerId.m6627constructorimpl(-1L);
        this.pointerInputNode = SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1

            /* JADX INFO: renamed from: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1$1, reason: invalid class name */
            /* JADX INFO: compiled from: AndroidOverscroll.android.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1$1", f = "AndroidOverscroll.android.kt", i = {0, 1}, l = {788, 792}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture"}, s = {"L$0", "L$0"}, v = 1)
            static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ AndroidEdgeEffectOverscrollEffect this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(AndroidEdgeEffectOverscrollEffect androidEdgeEffectOverscrollEffect, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.this$0 = androidEdgeEffectOverscrollEffect;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                    anonymousClass1.L$0 = obj;
                    return anonymousClass1;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((AnonymousClass1) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0075 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:16:0x0076  */
                /* JADX WARN: Removed duplicated region for block: B:19:0x0099  */
                /* JADX WARN: Removed duplicated region for block: B:25:0x00c9  */
                /* JADX WARN: Removed duplicated region for block: B:32:0x00f9  */
                /* JADX WARN: Removed duplicated region for block: B:35:0x0104  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x011f  */
                /* JADX WARN: Removed duplicated region for block: B:39:0x0121  */
                /* JADX WARN: Removed duplicated region for block: B:41:0x0124  */
                /* JADX WARN: Removed duplicated region for block: B:43:0x0132  */
                /* JADX WARN: Removed duplicated region for block: B:47:0x00f1 A[SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0076 -> B:17:0x007d). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r21) {
                    /*
                        Method dump skipped, instruction units count: 328
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect$pointerInputNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$SuspendingPointerInputModifierNode, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$SuspendingPointerInputModifierNode, new AnonymousClass1(this.this$0, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        });
        if (Build.VERSION.SDK_INT >= 31) {
            glowOverscrollNode = new StretchOverscrollNode(this.pointerInputNode, this, this.edgeEffectWrapper);
        } else {
            glowOverscrollNode = new GlowOverscrollNode(this.pointerInputNode, this, this.edgeEffectWrapper, glowDrawPadding);
        }
        this.node = glowOverscrollNode;
    }

    public final MutableState<Unit> getRedrawSignal$foundation() {
        return this.redrawSignal;
    }

    /* JADX INFO: renamed from: getInvalidationEnabled$foundation, reason: from getter */
    public final boolean getInvalidationEnabled() {
        return this.invalidationEnabled;
    }

    public final void setInvalidationEnabled$foundation(boolean z) {
        this.invalidationEnabled = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0267  */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToScroll-Rhakbz0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long mo264applyToScrollRhakbz0(long r34, int r36, kotlin.jvm.functions.Function1<? super androidx.compose.ui.geometry.Offset, androidx.compose.ui.geometry.Offset> r37) {
        /*
            Method dump skipped, instruction units count: 1023
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect.mo264applyToScrollRhakbz0(long, int, kotlin.jvm.functions.Function1):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0018  */
    @Override // androidx.compose.foundation.OverscrollEffect
    /* JADX INFO: renamed from: applyToFling-BMRW4eQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object mo263applyToFlingBMRW4eQ(long r22, kotlin.jvm.functions.Function2<? super androidx.compose.ui.unit.Velocity, ? super kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity>, ? extends java.lang.Object> r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            Method dump skipped, instruction units count: 524
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.AndroidEdgeEffectOverscrollEffect.mo263applyToFlingBMRW4eQ(long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public boolean isInProgress() {
        EdgeEffectWrapper this_$iv = this.edgeEffectWrapper;
        EdgeEffect it = this_$iv.topEffect;
        if (it != null) {
            if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(it) == 0.0f)) {
                return true;
            }
        }
        EdgeEffect it2 = this_$iv.bottomEffect;
        if (it2 != null) {
            if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(it2) == 0.0f)) {
                return true;
            }
        }
        EdgeEffect it3 = this_$iv.leftEffect;
        if (it3 != null) {
            if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(it3) == 0.0f)) {
                return true;
            }
        }
        EdgeEffect it4 = this_$iv.rightEffect;
        if (it4 != null) {
            if (!(EdgeEffectCompat.INSTANCE.getDistanceCompat(it4) == 0.0f)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: updateSize-uvyYCjk$foundation, reason: not valid java name */
    public final void m266updateSizeuvyYCjk$foundation(long size) {
        boolean initialSetSize = Size.m5133equalsimpl0(this.containerSize, Size.INSTANCE.m5146getZeroNHjbRc());
        boolean differentSize = !Size.m5133equalsimpl0(size, this.containerSize);
        this.containerSize = size;
        if (differentSize) {
            EdgeEffectWrapper edgeEffectWrapper = this.edgeEffectWrapper;
            int bits$iv$iv$iv = (int) (size >> 32);
            int width$iv = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv));
            int bits$iv$iv$iv2 = (int) (size & 4294967295L);
            int height$iv = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv2));
            edgeEffectWrapper.m340updateSizeozmzZPI(IntSize.m8316constructorimpl((((long) width$iv) << 32) | (((long) height$iv) & 4294967295L)));
        }
        if (initialSetSize || !differentSize) {
            return;
        }
        animateToReleaseIfNeeded();
    }

    /* JADX INFO: renamed from: displacement-F1C5BW0$foundation, reason: not valid java name */
    public final long m265displacementF1C5BW0$foundation() {
        long $this$isSpecified$iv = this.pointerPosition;
        long pointer = ((9223372034707292159L & $this$isSpecified$iv) > InlineClassHelperKt.UnspecifiedPackedFloats ? 1 : ((9223372034707292159L & $this$isSpecified$iv) == InlineClassHelperKt.UnspecifiedPackedFloats ? 0 : -1)) != 0 ? this.pointerPosition : SizeKt.m5147getCenteruvyYCjk(this.containerSize);
        long arg0$iv = pointer;
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv);
        long arg0$iv2 = this.containerSize;
        int bits$iv$iv$iv2 = (int) (arg0$iv2 >> 32);
        float x = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv2);
        long arg0$iv3 = pointer;
        int bits$iv$iv$iv3 = (int) (arg0$iv3 & 4294967295L);
        float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv3);
        long arg0$iv4 = this.containerSize;
        int bits$iv$iv$iv4 = (int) (arg0$iv4 & 4294967295L);
        float y = fIntBitsToFloat2 / Float.intBitsToFloat(bits$iv$iv$iv4);
        long v1$iv$iv = Float.floatToRawIntBits(x);
        long v2$iv$iv = Float.floatToRawIntBits(y);
        return Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L));
    }

    @Override // androidx.compose.foundation.OverscrollEffect
    public DelegatableNode getNode() {
        return this.node;
    }

    public final void invalidateOverscroll$foundation() {
        if (this.invalidationEnabled) {
            this.redrawSignal.setValue(Unit.INSTANCE);
        }
    }

    private final void animateToReleaseIfNeeded() {
        boolean needsInvalidation = false;
        EdgeEffectWrapper this_$iv = this.edgeEffectWrapper;
        EdgeEffect it = this_$iv.topEffect;
        boolean z = true;
        if (it != null) {
            it.onRelease();
            needsInvalidation = !it.isFinished();
        }
        EdgeEffect it2 = this_$iv.bottomEffect;
        if (it2 != null) {
            it2.onRelease();
            needsInvalidation = !it2.isFinished() || needsInvalidation;
        }
        EdgeEffect it3 = this_$iv.leftEffect;
        if (it3 != null) {
            it3.onRelease();
            needsInvalidation = !it3.isFinished() || needsInvalidation;
        }
        EdgeEffect it4 = this_$iv.rightEffect;
        if (it4 != null) {
            it4.onRelease();
            if (it4.isFinished() && !needsInvalidation) {
                z = false;
            }
            needsInvalidation = z;
        }
        if (needsInvalidation) {
            invalidateOverscroll$foundation();
        }
    }

    /* JADX INFO: renamed from: releaseOppositeOverscroll-k-4lQ0M, reason: not valid java name */
    private final boolean m262releaseOppositeOverscrollk4lQ0M(long delta) {
        boolean needsInvalidation = false;
        if (this.edgeEffectWrapper.isLeftAnimating()) {
            int bits$iv$iv$iv = (int) (delta >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv) < 0.0f) {
                int bits$iv$iv$iv2 = (int) (delta >> 32);
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateLeftEffect(), Float.intBitsToFloat(bits$iv$iv$iv2));
                needsInvalidation = this.edgeEffectWrapper.isLeftAnimating();
            }
        }
        if (this.edgeEffectWrapper.isRightAnimating()) {
            int bits$iv$iv$iv3 = (int) (delta >> 32);
            if (Float.intBitsToFloat(bits$iv$iv$iv3) > 0.0f) {
                int bits$iv$iv$iv4 = (int) (delta >> 32);
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateRightEffect(), Float.intBitsToFloat(bits$iv$iv$iv4));
                needsInvalidation = needsInvalidation || this.edgeEffectWrapper.isRightAnimating();
            }
        }
        if (this.edgeEffectWrapper.isTopAnimating()) {
            int bits$iv$iv$iv5 = (int) (delta & 4294967295L);
            if (Float.intBitsToFloat(bits$iv$iv$iv5) < 0.0f) {
                int bits$iv$iv$iv6 = (int) (delta & 4294967295L);
                EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateTopEffect(), Float.intBitsToFloat(bits$iv$iv$iv6));
                needsInvalidation = needsInvalidation || this.edgeEffectWrapper.isTopAnimating();
            }
        }
        if (!this.edgeEffectWrapper.isBottomAnimating()) {
            return needsInvalidation;
        }
        int bits$iv$iv$iv7 = (int) (delta & 4294967295L);
        if (Float.intBitsToFloat(bits$iv$iv$iv7) <= 0.0f) {
            return needsInvalidation;
        }
        int bits$iv$iv$iv8 = (int) (4294967295L & delta);
        EdgeEffectCompat.INSTANCE.onReleaseWithOppositeDelta(this.edgeEffectWrapper.getOrCreateBottomEffect(), Float.intBitsToFloat(bits$iv$iv$iv8));
        return needsInvalidation || this.edgeEffectWrapper.isBottomAnimating();
    }

    /* JADX INFO: renamed from: pullTop-k-4lQ0M, reason: not valid java name */
    private final float m261pullTopk4lQ0M(long scroll) {
        long arg0$iv = m265displacementF1C5BW0$foundation();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float displacementX = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll & 4294967295L);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        long arg0$iv2 = this.containerSize;
        int bits$iv$iv$iv3 = (int) (arg0$iv2 & 4294967295L);
        float pullY = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect topEffect = this.edgeEffectWrapper.getOrCreateTopEffect();
        float fOnPullDistanceCompat = EdgeEffectCompat.INSTANCE.onPullDistanceCompat(topEffect, pullY, displacementX);
        long arg0$iv3 = this.containerSize;
        int bits$iv$iv$iv4 = (int) (arg0$iv3 & 4294967295L);
        float consumed = fOnPullDistanceCompat * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(topEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (4294967295L & scroll);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }

    /* JADX INFO: renamed from: pullBottom-k-4lQ0M, reason: not valid java name */
    private final float m258pullBottomk4lQ0M(long scroll) {
        long arg0$iv = m265displacementF1C5BW0$foundation();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        float displacementX = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll & 4294967295L);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        long arg0$iv2 = this.containerSize;
        int bits$iv$iv$iv3 = (int) (arg0$iv2 & 4294967295L);
        float pullY = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect bottomEffect = this.edgeEffectWrapper.getOrCreateBottomEffect();
        float f = -EdgeEffectCompat.INSTANCE.onPullDistanceCompat(bottomEffect, -pullY, 1.0f - displacementX);
        long arg0$iv3 = this.containerSize;
        int bits$iv$iv$iv4 = (int) (arg0$iv3 & 4294967295L);
        float consumed = f * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(bottomEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (4294967295L & scroll);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }

    /* JADX INFO: renamed from: pullLeft-k-4lQ0M, reason: not valid java name */
    private final float m259pullLeftk4lQ0M(long scroll) {
        long arg0$iv = m265displacementF1C5BW0$foundation();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        float displacementY = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        long arg0$iv2 = this.containerSize;
        int bits$iv$iv$iv3 = (int) (arg0$iv2 >> 32);
        float pullX = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect leftEffect = this.edgeEffectWrapper.getOrCreateLeftEffect();
        float fOnPullDistanceCompat = EdgeEffectCompat.INSTANCE.onPullDistanceCompat(leftEffect, pullX, 1.0f - displacementY);
        long arg0$iv3 = this.containerSize;
        int bits$iv$iv$iv4 = (int) (arg0$iv3 >> 32);
        float consumed = fOnPullDistanceCompat * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(leftEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (scroll >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }

    /* JADX INFO: renamed from: pullRight-k-4lQ0M, reason: not valid java name */
    private final float m260pullRightk4lQ0M(long scroll) {
        long arg0$iv = m265displacementF1C5BW0$foundation();
        int bits$iv$iv$iv = (int) (4294967295L & arg0$iv);
        float displacementY = Float.intBitsToFloat(bits$iv$iv$iv);
        int bits$iv$iv$iv2 = (int) (scroll >> 32);
        float fIntBitsToFloat = Float.intBitsToFloat(bits$iv$iv$iv2);
        long arg0$iv2 = this.containerSize;
        int bits$iv$iv$iv3 = (int) (arg0$iv2 >> 32);
        float pullX = fIntBitsToFloat / Float.intBitsToFloat(bits$iv$iv$iv3);
        EdgeEffect rightEffect = this.edgeEffectWrapper.getOrCreateRightEffect();
        float f = -EdgeEffectCompat.INSTANCE.onPullDistanceCompat(rightEffect, -pullX, displacementY);
        long arg0$iv3 = this.containerSize;
        int bits$iv$iv$iv4 = (int) (arg0$iv3 >> 32);
        float consumed = f * Float.intBitsToFloat(bits$iv$iv$iv4);
        if (EdgeEffectCompat.INSTANCE.getDistanceCompat(rightEffect) == 0.0f) {
            return consumed;
        }
        int bits$iv$iv$iv5 = (int) (scroll >> 32);
        return Float.intBitsToFloat(bits$iv$iv$iv5);
    }
}
