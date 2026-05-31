package androidx.compose.foundation;

import android.view.View;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatableNode_androidKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.GlobalPositionAwareModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: compiled from: Magnifier.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u009b\u0001\u0012\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n\u0012\u001b\b\u0002\u0010\u000b\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\r\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\u008f\u0001\u0010S\u001a\u00020\u000e2\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\n2\u0019\u0010\u000b\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00122\u0014\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u00072\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\bT\u0010UJ\b\u0010V\u001a\u00020\u000eH\u0016J\b\u0010W\u001a\u00020\u000eH\u0016J\b\u0010X\u001a\u00020\u000eH\u0016J\b\u0010Y\u001a\u00020\u000eH\u0002J\b\u0010Z\u001a\u00020\u000eH\u0002J\b\u0010[\u001a\u00020\u000eH\u0002J\f\u0010\\\u001a\u00020\u000e*\u00020]H\u0016J\u0010\u0010^\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020BH\u0016J\f\u0010`\u001a\u00020\u000e*\u00020aH\u0016R+\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0002\b\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR-\u0010\u000b\u001a\u0015\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007¢\u0006\u0002\b\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u001d\"\u0004\b!\u0010\u001fR(\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001d\"\u0004\b#\u0010\u001fR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001c\u0010\u0013\u001a\u00020\rX\u0086\u000e¢\u0006\u0010\n\u0002\u00100\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001c\u0010\u0014\u001a\u00020\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\u00103\u001a\u0004\b1\u0010%\"\u0004\b2\u0010'R\u001c\u0010\u0016\u001a\u00020\u0015X\u0086\u000e¢\u0006\u0010\n\u0002\u00103\u001a\u0004\b4\u0010%\"\u0004\b5\u0010'R\u001a\u0010\u0017\u001a\u00020\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010)\"\u0004\b7\u0010+R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010@X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010C\u001a\u0004\u0018\u00010B2\b\u0010A\u001a\u0004\u0018\u00010B8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\bH\u0010I\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u0016\u0010J\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010KX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010L\u001a\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bM\u0010-R\u0010\u0010N\u001a\u00020\tX\u0082\u000e¢\u0006\u0004\n\u0002\u00100R\u0010\u0010O\u001a\u0004\u0018\u00010PX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010Q\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010RX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006b"}, d2 = {"Landroidx/compose/foundation/MagnifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/GlobalPositionAwareModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/SemanticsModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "sourceCenter", "Lkotlin/Function1;", "Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/ExtensionFunctionType;", "magnifierCenter", "onSizeChanged", "Landroidx/compose/ui/unit/DpSize;", "", "zoom", "", "useTextDefault", "", "size", "cornerRadius", "Landroidx/compose/ui/unit/Dp;", "elevation", "clippingEnabled", "platformMagnifierFactory", "Landroidx/compose/foundation/PlatformMagnifierFactory;", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;FZJFFZLandroidx/compose/foundation/PlatformMagnifierFactory;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSourceCenter", "()Lkotlin/jvm/functions/Function1;", "setSourceCenter", "(Lkotlin/jvm/functions/Function1;)V", "getMagnifierCenter", "setMagnifierCenter", "getOnSizeChanged", "setOnSizeChanged", "getZoom", "()F", "setZoom", "(F)V", "getUseTextDefault", "()Z", "setUseTextDefault", "(Z)V", "getSize-MYxV2XQ", "()J", "setSize-EaSLcWc", "(J)V", "J", "getCornerRadius-D9Ej5fM", "setCornerRadius-0680j_4", "F", "getElevation-D9Ej5fM", "setElevation-0680j_4", "getClippingEnabled", "setClippingEnabled", "getPlatformMagnifierFactory", "()Landroidx/compose/foundation/PlatformMagnifierFactory;", "setPlatformMagnifierFactory", "(Landroidx/compose/foundation/PlatformMagnifierFactory;)V", "view", "Landroid/view/View;", "density", "magnifier", "Landroidx/compose/foundation/PlatformMagnifier;", "<set-?>", "Landroidx/compose/ui/layout/LayoutCoordinates;", "layoutCoordinates", "getLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "layoutCoordinates$delegate", "Landroidx/compose/runtime/MutableState;", "anchorPositionInRootState", "Landroidx/compose/runtime/State;", "anchorPositionInRoot", "getAnchorPositionInRoot-F1C5BW0", "sourceCenterInRoot", "previousSize", "Landroidx/compose/ui/unit/IntSize;", "drawSignalChannel", "Lkotlinx/coroutines/channels/Channel;", "update", "update-5F03MCQ", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;FZJFFZLkotlin/jvm/functions/Function1;Landroidx/compose/foundation/PlatformMagnifierFactory;)V", "onAttach", "onDetach", "onObservedReadsChanged", "recreateMagnifier", "updateMagnifier", "updateSizeIfNecessary", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "onGloballyPositioned", "coordinates", "applySemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class MagnifierNode extends Modifier.Node implements GlobalPositionAwareModifierNode, DrawModifierNode, SemanticsModifierNode, ObserverModifierNode {
    public static final int $stable = 8;
    private State<Offset> anchorPositionInRootState;
    private boolean clippingEnabled;
    private float cornerRadius;
    private Density density;
    private Channel<Unit> drawSignalChannel;
    private float elevation;

    /* JADX INFO: renamed from: layoutCoordinates$delegate, reason: from kotlin metadata */
    private final MutableState layoutCoordinates;
    private PlatformMagnifier magnifier;
    private Function1<? super Density, Offset> magnifierCenter;
    private Function1<? super DpSize, Unit> onSizeChanged;
    private PlatformMagnifierFactory platformMagnifierFactory;
    private IntSize previousSize;
    private long size;
    private Function1<? super Density, Offset> sourceCenter;
    private long sourceCenterInRoot;
    private boolean useTextDefault;
    private View view;
    private float zoom;

    public /* synthetic */ MagnifierNode(Function1 function1, Function1 function12, Function1 function13, float f, boolean z, long j, float f2, float f3, boolean z2, PlatformMagnifierFactory platformMagnifierFactory, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, function12, function13, f, z, j, f2, f3, z2, platformMagnifierFactory);
    }

    private MagnifierNode(Function1<? super Density, Offset> function1, Function1<? super Density, Offset> function12, Function1<? super DpSize, Unit> function13, float zoom, boolean useTextDefault, long size, float cornerRadius, float elevation, boolean clippingEnabled, PlatformMagnifierFactory platformMagnifierFactory) {
        this.sourceCenter = function1;
        this.magnifierCenter = function12;
        this.onSizeChanged = function13;
        this.zoom = zoom;
        this.useTextDefault = useTextDefault;
        this.size = size;
        this.cornerRadius = cornerRadius;
        this.elevation = elevation;
        this.clippingEnabled = clippingEnabled;
        this.platformMagnifierFactory = platformMagnifierFactory;
        this.layoutCoordinates = SnapshotStateKt.mutableStateOf(null, SnapshotStateKt.neverEqualPolicy());
        this.sourceCenterInRoot = Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    }

    public /* synthetic */ MagnifierNode(Function1 function1, Function1 function12, Function1 function13, float f, boolean z, long j, float f2, float f3, boolean z2, PlatformMagnifierFactory platformMagnifierFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function1, (i & 2) != 0 ? null : function12, (i & 4) != 0 ? null : function13, (i & 8) != 0 ? Float.NaN : f, (i & 16) != 0 ? false : z, (i & 32) != 0 ? DpSize.INSTANCE.m8257getUnspecifiedMYxV2XQ() : j, (i & 64) != 0 ? Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM() : f2, (i & 128) != 0 ? Dp.INSTANCE.m8170getUnspecifiedD9Ej5fM() : f3, (i & 256) != 0 ? true : z2, (i & 512) != 0 ? PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform() : platformMagnifierFactory, null);
    }

    public final Function1<Density, Offset> getSourceCenter() {
        return this.sourceCenter;
    }

    public final void setSourceCenter(Function1<? super Density, Offset> function1) {
        this.sourceCenter = function1;
    }

    public final Function1<Density, Offset> getMagnifierCenter() {
        return this.magnifierCenter;
    }

    public final void setMagnifierCenter(Function1<? super Density, Offset> function1) {
        this.magnifierCenter = function1;
    }

    public final Function1<DpSize, Unit> getOnSizeChanged() {
        return this.onSizeChanged;
    }

    public final void setOnSizeChanged(Function1<? super DpSize, Unit> function1) {
        this.onSizeChanged = function1;
    }

    public final float getZoom() {
        return this.zoom;
    }

    public final void setZoom(float f) {
        this.zoom = f;
    }

    public final boolean getUseTextDefault() {
        return this.useTextDefault;
    }

    public final void setUseTextDefault(boolean z) {
        this.useTextDefault = z;
    }

    /* JADX INFO: renamed from: getSize-MYxV2XQ, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    /* JADX INFO: renamed from: setSize-EaSLcWc */
    public final void m350setSizeEaSLcWc(long j) {
        this.size = j;
    }

    /* JADX INFO: renamed from: getCornerRadius-D9Ej5fM, reason: from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* JADX INFO: renamed from: setCornerRadius-0680j_4 */
    public final void m348setCornerRadius0680j_4(float f) {
        this.cornerRadius = f;
    }

    /* JADX INFO: renamed from: getElevation-D9Ej5fM, reason: from getter */
    public final float getElevation() {
        return this.elevation;
    }

    /* JADX INFO: renamed from: setElevation-0680j_4 */
    public final void m349setElevation0680j_4(float f) {
        this.elevation = f;
    }

    public final boolean getClippingEnabled() {
        return this.clippingEnabled;
    }

    public final void setClippingEnabled(boolean z) {
        this.clippingEnabled = z;
    }

    public final PlatformMagnifierFactory getPlatformMagnifierFactory() {
        return this.platformMagnifierFactory;
    }

    public final void setPlatformMagnifierFactory(PlatformMagnifierFactory platformMagnifierFactory) {
        this.platformMagnifierFactory = platformMagnifierFactory;
    }

    private final LayoutCoordinates getLayoutCoordinates() {
        State $this$getValue$iv = this.layoutCoordinates;
        return (LayoutCoordinates) $this$getValue$iv.getValue();
    }

    private final void setLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        MutableState $this$setValue$iv = this.layoutCoordinates;
        $this$setValue$iv.setValue(layoutCoordinates);
    }

    /* JADX INFO: renamed from: getAnchorPositionInRoot-F1C5BW0 */
    private final long m344getAnchorPositionInRootF1C5BW0() {
        if (this.anchorPositionInRootState == null) {
            this.anchorPositionInRootState = SnapshotStateKt.derivedStateOf(new Function0() { // from class: androidx.compose.foundation.MagnifierNode$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MagnifierNode.getAnchorPositionInRoot_F1C5BW0$lambda$0(this.f$0);
                }
            });
        }
        State<Offset> state = this.anchorPositionInRootState;
        return state != null ? state.getValue().m5078unboximpl() : Offset.INSTANCE.m5083getUnspecifiedF1C5BW0();
    }

    static final Offset getAnchorPositionInRoot_F1C5BW0$lambda$0(MagnifierNode this$0) {
        LayoutCoordinates layoutCoordinates = this$0.getLayoutCoordinates();
        return Offset.m5057boximpl(layoutCoordinates != null ? LayoutCoordinatesKt.positionInRoot(layoutCoordinates) : Offset.INSTANCE.m5083getUnspecifiedF1C5BW0());
    }

    /* JADX INFO: renamed from: update-5F03MCQ */
    public final void m351update5F03MCQ(Function1<? super Density, Offset> sourceCenter, Function1<? super Density, Offset> magnifierCenter, float zoom, boolean useTextDefault, long size, float cornerRadius, float elevation, boolean clippingEnabled, Function1<? super DpSize, Unit> onSizeChanged, PlatformMagnifierFactory platformMagnifierFactory) {
        float previousZoom = this.zoom;
        long previousSize = this.size;
        float previousCornerRadius = this.cornerRadius;
        boolean previousUseTextDefault = this.useTextDefault;
        float previousElevation = this.elevation;
        boolean previousClippingEnabled = this.clippingEnabled;
        PlatformMagnifierFactory previousPlatformMagnifierFactory = this.platformMagnifierFactory;
        View previousView = this.view;
        Density previousDensity = this.density;
        this.sourceCenter = sourceCenter;
        this.magnifierCenter = magnifierCenter;
        this.zoom = zoom;
        this.useTextDefault = useTextDefault;
        this.size = size;
        this.cornerRadius = cornerRadius;
        this.elevation = elevation;
        this.clippingEnabled = clippingEnabled;
        this.onSizeChanged = onSizeChanged;
        this.platformMagnifierFactory = platformMagnifierFactory;
        View view = DelegatableNode_androidKt.requireView(this);
        Density density = DelegatableNodeKt.requireDensity(this);
        boolean previousClippingEnabled2 = (this.magnifier == null || ((Magnifier_androidKt.equalsIncludingNaN(zoom, previousZoom) || platformMagnifierFactory.getCanUpdateZoom()) && DpSize.m8245equalsimpl0(size, previousSize) && Dp.m8155equalsimpl0(cornerRadius, previousCornerRadius) && Dp.m8155equalsimpl0(elevation, previousElevation) && useTextDefault == previousUseTextDefault && clippingEnabled == previousClippingEnabled && Intrinsics.areEqual(platformMagnifierFactory, previousPlatformMagnifierFactory) && Intrinsics.areEqual(view, previousView) && Intrinsics.areEqual(density, previousDensity))) ? false : true;
        if (previousClippingEnabled2) {
            recreateMagnifier();
        }
        updateMagnifier();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        onObservedReadsChanged();
        this.drawSignalChannel = ChannelKt.Channel$default(0, null, null, 7, null);
        BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(null), 1, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.MagnifierNode$onAttach$1 */
    /* JADX INFO: compiled from: Magnifier.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.MagnifierNode$onAttach$1", f = "Magnifier.android.kt", i = {}, l = {382, 386}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MagnifierNode.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x0029  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x003f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x003d -> B:31:0x0020). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x004e -> B:41:0x0051). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r6) {
            /*
                r5 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r5.label
                switch(r1) {
                    case 0: goto L1c;
                    case 1: goto L17;
                    case 2: goto L12;
                    default: goto L9;
                }
            L9:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r0)
                throw r6
            L12:
                kotlin.ResultKt.throwOnFailure(r6)
                r1 = r5
                goto L51
            L17:
                kotlin.ResultKt.throwOnFailure(r6)
                r1 = r5
                goto L36
            L1c:
                kotlin.ResultKt.throwOnFailure(r6)
                r1 = r5
            L20:
                androidx.compose.foundation.MagnifierNode r2 = androidx.compose.foundation.MagnifierNode.this
                kotlinx.coroutines.channels.Channel r2 = androidx.compose.foundation.MagnifierNode.access$getDrawSignalChannel$p(r2)
                if (r2 == 0) goto L36
                r3 = r1
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r4 = 1
                r1.label = r4
                java.lang.Object r2 = r2.receive(r3)
                if (r2 != r0) goto L36
                return r0
            L36:
                androidx.compose.foundation.MagnifierNode r2 = androidx.compose.foundation.MagnifierNode.this
                androidx.compose.foundation.PlatformMagnifier r2 = androidx.compose.foundation.MagnifierNode.access$getMagnifier$p(r2)
                if (r2 == 0) goto L20
                androidx.compose.foundation.MagnifierNode$onAttach$1$$ExternalSyntheticLambda0 r2 = new androidx.compose.foundation.MagnifierNode$onAttach$1$$ExternalSyntheticLambda0
                r2.<init>()
                r3 = r1
                kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                r4 = 2
                r1.label = r4
                java.lang.Object r2 = androidx.compose.runtime.MonotonicFrameClockKt.withFrameMillis(r2, r3)
                if (r2 != r0) goto L51
                return r0
            L51:
                androidx.compose.foundation.MagnifierNode r2 = androidx.compose.foundation.MagnifierNode.this
                androidx.compose.foundation.PlatformMagnifier r2 = androidx.compose.foundation.MagnifierNode.access$getMagnifier$p(r2)
                if (r2 == 0) goto L20
                r2.updateContent()
                goto L20
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MagnifierNode.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier != null) {
            platformMagnifier.dismiss();
        }
        this.magnifier = null;
    }

    static final Unit onObservedReadsChanged$lambda$0(MagnifierNode this$0) {
        this$0.updateMagnifier();
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.MagnifierNode$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return MagnifierNode.onObservedReadsChanged$lambda$0(this.f$0);
            }
        });
    }

    private final void recreateMagnifier() {
        PlatformMagnifier platformMagnifier = this.magnifier;
        if (platformMagnifier != null) {
            platformMagnifier.dismiss();
        }
        View viewRequireView = this.view;
        if (viewRequireView == null) {
            viewRequireView = DelegatableNode_androidKt.requireView(this);
        }
        View it = viewRequireView;
        this.view = it;
        View view = viewRequireView;
        Density densityRequireDensity = this.density;
        if (densityRequireDensity == null) {
            densityRequireDensity = DelegatableNodeKt.requireDensity(this);
        }
        Density density = densityRequireDensity;
        this.density = density;
        this.magnifier = this.platformMagnifierFactory.mo376createnHHXs2Y(view, this.useTextDefault, this.size, this.cornerRadius, this.elevation, this.clippingEnabled, density, this.zoom);
        updateSizeIfNecessary();
    }

    /* JADX WARN: Removed duplicated region for block: B:68:0x008c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void updateMagnifier() {
        /*
            r17 = this;
            r0 = r17
            androidx.compose.ui.unit.Density r1 = r0.density
            if (r1 != 0) goto L11
            r1 = r0
            androidx.compose.ui.node.DelegatableNode r1 = (androidx.compose.ui.node.DelegatableNode) r1
            androidx.compose.ui.unit.Density r1 = androidx.compose.ui.node.DelegatableNodeKt.requireDensity(r1)
            r2 = r1
            r3 = 0
            r0.density = r2
        L11:
            kotlin.jvm.functions.Function1<? super androidx.compose.ui.unit.Density, androidx.compose.ui.geometry.Offset> r2 = r0.sourceCenter
            java.lang.Object r2 = r2.invoke(r1)
            androidx.compose.ui.geometry.Offset r2 = (androidx.compose.ui.geometry.Offset) r2
            long r2 = r2.m5078unboximpl()
            r4 = r2
            r6 = 0
            r7 = 9223372034707292159(0x7fffffff7fffffff, double:NaN)
            long r9 = r4 & r7
            r11 = 9205357640488583168(0x7fc000007fc00000, double:2.247117487993712E307)
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            r10 = 1
            r13 = 0
            if (r9 == 0) goto L33
            r4 = r10
            goto L34
        L33:
            r4 = r13
        L34:
            if (r4 == 0) goto Lab
            long r4 = r0.m344getAnchorPositionInRootF1C5BW0()
            r6 = 0
            long r14 = r4 & r7
            int r9 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
            if (r9 == 0) goto L43
            r4 = r10
            goto L44
        L43:
            r4 = r13
        L44:
            if (r4 == 0) goto Lab
            long r4 = r0.m344getAnchorPositionInRootF1C5BW0()
            long r4 = androidx.compose.ui.geometry.Offset.m5073plusMKHz9U(r4, r2)
            r0.sourceCenterInRoot = r4
            kotlin.jvm.functions.Function1<? super androidx.compose.ui.unit.Density, androidx.compose.ui.geometry.Offset> r4 = r0.magnifierCenter
            if (r4 == 0) goto L8c
        L58:
            java.lang.Object r4 = r4.invoke(r1)
            androidx.compose.ui.geometry.Offset r4 = (androidx.compose.ui.geometry.Offset) r4
            long r4 = r4.m5078unboximpl()
            androidx.compose.ui.geometry.Offset r4 = androidx.compose.ui.geometry.Offset.m5057boximpl(r4)
            long r5 = r4.m5078unboximpl()
            r9 = 0
            r14 = r5
            r16 = 0
            long r7 = r7 & r14
            int r7 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r7 == 0) goto L75
            goto L76
        L75:
            r10 = r13
        L76:
            if (r10 == 0) goto L7a
            goto L7b
        L7a:
            r4 = 0
        L7b:
            if (r4 == 0) goto L8c
        L7e:
            long r4 = r4.m5078unboximpl()
            r6 = 0
            long r7 = r0.m344getAnchorPositionInRootF1C5BW0()
            long r4 = androidx.compose.ui.geometry.Offset.m5073plusMKHz9U(r7, r4)
            goto L92
        L8c:
            androidx.compose.ui.geometry.Offset$Companion r4 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r4 = r4.m5083getUnspecifiedF1C5BW0()
        L92:
            r9 = r4
            androidx.compose.foundation.PlatformMagnifier r4 = r0.magnifier
            if (r4 != 0) goto L9b
            r0.recreateMagnifier()
        L9b:
            androidx.compose.foundation.PlatformMagnifier r6 = r0.magnifier
            if (r6 == 0) goto La7
            long r7 = r0.sourceCenterInRoot
            float r11 = r0.zoom
            r6.mo375updateWko1d7g(r7, r9, r11)
        La7:
            r0.updateSizeIfNecessary()
            return
        Lab:
            androidx.compose.ui.geometry.Offset$Companion r4 = androidx.compose.ui.geometry.Offset.INSTANCE
            long r4 = r4.m5083getUnspecifiedF1C5BW0()
            r0.sourceCenterInRoot = r4
            androidx.compose.foundation.PlatformMagnifier r4 = r0.magnifier
            if (r4 == 0) goto Lba
            r4.dismiss()
        Lba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.MagnifierNode.updateMagnifier():void");
    }

    private final void updateSizeIfNecessary() {
        Density density;
        PlatformMagnifier magnifier = this.magnifier;
        if (magnifier != null && (density = this.density) != null && !IntSize.m8318equalsimpl(magnifier.mo374getSizeYbymL2g(), this.previousSize)) {
            Function1<? super DpSize, Unit> function1 = this.onSizeChanged;
            if (function1 != null) {
                function1.invoke(DpSize.m8236boximpl(density.mo430toDpSizekrfVVM(IntSizeKt.m8333toSizeozmzZPI(magnifier.mo374getSizeYbymL2g()))));
            }
            this.previousSize = IntSize.m8313boximpl(magnifier.mo374getSizeYbymL2g());
        }
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        $this$draw.drawContent();
        Channel<Unit> channel = this.drawSignalChannel;
        if (channel != null) {
            ChannelResult.m10450boximpl(channel.mo10436trySendJP2dKIU(Unit.INSTANCE));
        }
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public void onGloballyPositioned(LayoutCoordinates coordinates) {
        setLayoutCoordinates(coordinates);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public void applySemantics(SemanticsPropertyReceiver $this$applySemantics) {
        $this$applySemantics.set(Magnifier_androidKt.getMagnifierPositionInRoot(), new Function0() { // from class: androidx.compose.foundation.MagnifierNode$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Offset.m5057boximpl(this.f$0.sourceCenterInRoot);
            }
        });
    }
}
