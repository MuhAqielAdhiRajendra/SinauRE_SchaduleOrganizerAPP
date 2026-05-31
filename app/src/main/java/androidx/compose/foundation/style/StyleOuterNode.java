package androidx.compose.foundation.style;

import androidx.collection.MutableObjectList;
import androidx.compose.foundation.border.BorderLogic;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.text.modifiers.TextStyleProviderNode;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalAccessorScope;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.graphics.shadow.DropShadowPainter;
import androidx.compose.ui.graphics.shadow.InnerShadowPainter;
import androidx.compose.ui.graphics.shadow.Shadow;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: StyleModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¦\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\bB\u0019\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010;\u001a\u00020\"2\u0006\u0010<\u001a\u00020=2\b\b\u0002\u0010>\u001a\u00020\"H\u0000¢\u0006\u0002\b?J\b\u0010@\u001a\u00020\"H\u0002J\b\u0010A\u001a\u00020\"H\u0002J#\u0010B\u001a\u00020C*\u00020D2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020HH\u0016¢\u0006\u0004\bI\u0010JJ\f\u0010K\u001a\u00020\u0010*\u00020\"H\u0002J\f\u0010L\u001a\u00020\u0010*\u00020\"H\u0002J\f\u0010X\u001a\u00020P*\u00020OH\u0002J#\u0010b\u001a\u00020a*\u00020c2\u0006\u0010d\u001a\u00020Z2\u0006\u0010e\u001a\u00020_H\u0002¢\u0006\u0004\bf\u0010gJ\f\u0010h\u001a\u00020P*\u00020cH\u0016J$\u0010p\u001a\u00020P*\u00020c2\u0006\u0010q\u001a\u00020=2\u0006\u0010e\u001a\u00020_2\u0006\u0010r\u001a\u00020kH\u0002J\u0016\u0010s\u001a\u00020P2\u0006\u0010t\u001a\u00020u2\u0006\u0010e\u001a\u00020_J\u0012\u0010p\u001a\u00020P*\u00020c2\u0006\u0010v\u001a\u00020\"J$\u0010{\u001a\u00020P*\u00020c2\u0006\u0010q\u001a\u00020=2\u0006\u0010e\u001a\u00020_2\u0006\u0010r\u001a\u00020kH\u0002J\u0016\u0010|\u001a\u00020P2\u0006\u0010t\u001a\u00020u2\u0006\u0010e\u001a\u00020_J\u0012\u0010{\u001a\u00020P*\u00020c2\u0006\u0010v\u001a\u00020\"J\u0080\u0001\u0010}\u001a\u00020P*\u00020c2\u0006\u0010e\u001a\u00020_2\u0006\u0010~\u001a\u00020\u00102\u0006\u0010\u007f\u001a\u00020\u00102\u0007\u0010\u0080\u0001\u001a\u00020\u00102\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\n\u0010\u0083\u0001\u001a\u0005\u0018\u00010\u0084\u00012\b\u0010\u0085\u0001\u001a\u00030\u0082\u00012\n\u0010\u0086\u0001\u001a\u0005\u0018\u00010\u0084\u00012\b\u0010\u0087\u0001\u001a\u00030\u0082\u00012\n\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0084\u00012\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001J\u0012\u0010\u0090\u0001\u001a\u00020P2\t\b\u0002\u0010\u0091\u0001\u001a\u00020\u0010J\t\u0010\u0092\u0001\u001a\u00020PH\u0016J\u0007\u0010\u0099\u0001\u001a\u00020PJ'\u0010\u009f\u0001\u001a\u00030 \u00012\b\u0010¡\u0001\u001a\u00030¢\u00012\b\u0010£\u0001\u001a\u00030 \u0001H\u0016¢\u0006\u0006\b¤\u0001\u0010¥\u0001J\u0019\u0010¬\u0001\u001a\u0004\u0018\u00010\"2\u0006\u0010<\u001a\u00020=H\u0000¢\u0006\u0003\b\u00ad\u0001J\u0011\u0010°\u0001\u001a\u0004\u0018\u00010\"H\u0000¢\u0006\u0003\b±\u0001J\u0017\u0010²\u0001\u001a\u00020P2\u0006\u0010\u000b\u001a\u00020\"H\u0000¢\u0006\u0003\b³\u0001J\t\u0010´\u0001\u001a\u00020PH\u0002J\t\u0010µ\u0001\u001a\u00020PH\u0002J\t\u0010¶\u0001\u001a\u00020PH\u0016R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00148@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R$\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\f@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020\"8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010/\u001a\n\u0012\u0004\u0012\u00020.\u0018\u000100X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00104\u001a\u0004\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00106\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n8@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R-\u0010M\u001a\u0015\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u00020P\u0018\u00010N¢\u0006\u0002\bQX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010S\"\u0004\bT\u0010UR%\u0010V\u001a\u0013\u0012\u0004\u0012\u00020O\u0012\u0004\u0012\u00020P0N¢\u0006\u0002\bQ8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bW\u0010SR\u0010\u0010Y\u001a\u00020ZX\u0082\u000e¢\u0006\u0004\n\u0002\u0010[R\u0010\u0010\\\u001a\u0004\u0018\u00010]X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010^\u001a\u0004\u0018\u00010_X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010`\u001a\u0004\u0018\u00010aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010i\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010k\u0018\u00010jX\u0082\u000e¢\u0006\u0004\n\u0002\u0010lR\u001a\u0010m\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010n\u0018\u00010jX\u0082\u000e¢\u0006\u0004\n\u0002\u0010oR\u001a\u0010w\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010k\u0018\u00010jX\u0082\u000e¢\u0006\u0004\n\u0002\u0010lR\u001a\u0010x\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010y\u0018\u00010jX\u0082\u000e¢\u0006\u0004\n\u0002\u0010zR\u0017\u0010\u008d\u0001\u001a\u00020u8VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\"\u0010\u0093\u0001\u001a\u0005\u0018\u00010\u0094\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001\"\u0006\b\u0097\u0001\u0010\u0098\u0001R+\u0010\u009a\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u0001*\n\u0012\u0005\u0012\u0003H\u009b\u00010\u009c\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R(\u0010¦\u0001\u001a\u000b\u0012\u0004\u0012\u00020\u0000\u0018\u00010§\u0001X\u0080\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b¨\u0001\u0010©\u0001\"\u0006\bª\u0001\u0010«\u0001R\u0011\u0010®\u0001\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010¯\u0001\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006·\u0001"}, d2 = {"Landroidx/compose/foundation/style/StyleOuterNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/TraversableNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/runtime/CompositionLocalAccessorScope;", "Landroidx/compose/foundation/text/modifiers/TextStyleProviderNode;", "styleState", "Landroidx/compose/foundation/style/StyleState;", "style", "Landroidx/compose/foundation/style/Style;", "<init>", "(Landroidx/compose/foundation/style/StyleState;Landroidx/compose/foundation/style/Style;)V", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "innerNodeField", "Landroidx/compose/foundation/style/StyleInnerNode;", "getInnerNodeField$foundation", "()Landroidx/compose/foundation/style/StyleInnerNode;", "setInnerNodeField$foundation", "(Landroidx/compose/foundation/style/StyleInnerNode;)V", "value", "innerNode", "getInnerNode$foundation", "setInnerNode$foundation", "getStyle$foundation", "()Landroidx/compose/foundation/style/Style;", "setStyle$foundation", "(Landroidx/compose/foundation/style/Style;)V", "_resolved", "Landroidx/compose/foundation/style/ResolvedStyle;", "_bufferOrNull", "bufferNonNull", "getBufferNonNull", "()Landroidx/compose/foundation/style/ResolvedStyle;", "animations", "Landroidx/compose/foundation/style/StyleAnimations;", "getAnimations$foundation", "()Landroidx/compose/foundation/style/StyleAnimations;", "setAnimations$foundation", "(Landroidx/compose/foundation/style/StyleAnimations;)V", "borderLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "borderLayerProvider", "Lkotlin/Function0;", "borderLogic", "Landroidx/compose/foundation/border/BorderLogic;", "_state", "currentInteractionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "state", "getState$foundation", "()Landroidx/compose/foundation/style/StyleState;", "setState$foundation", "(Landroidx/compose/foundation/style/StyleState;)V", "resolveAnimatedStyleFor", "flags", "", "base", "resolveAnimatedStyleFor$foundation", "currentLayerStyle", "currentLayoutStyle", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "shouldPlaceRelativeToRight", "shouldPlaceRelativeToBottom", "layerBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "", "Lkotlin/ExtensionFunctionType;", "getLayerBlock$foundation", "()Lkotlin/jvm/functions/Function1;", "setLayerBlock$foundation", "(Lkotlin/jvm/functions/Function1;)V", "layerBlockNonNull", "getLayerBlockNonNull$foundation", "updateLayer", "lastSize", "Landroidx/compose/ui/geometry/Size;", "J", "lastLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "lastShape", "Landroidx/compose/ui/graphics/Shape;", "lastOutline", "Landroidx/compose/ui/graphics/Outline;", "getOutline", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "size", "shape", "getOutline-12SF9DM", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;JLandroidx/compose/ui/graphics/Shape;)Landroidx/compose/ui/graphics/Outline;", "draw", "lastInnerShadow", "", "Landroidx/compose/ui/graphics/shadow/Shadow;", "[Landroidx/compose/ui/graphics/shadow/Shadow;", "cachedInnerShadowPainters", "Landroidx/compose/ui/graphics/shadow/InnerShadowPainter;", "[Landroidx/compose/ui/graphics/shadow/InnerShadowPainter;", "drawInnerShadow", "index", "shadow", "reconcileInnerShadowCache", "shadowOrArray", "", "resolved", "lastDropShadow", "cachedDropShadowPainters", "Landroidx/compose/ui/graphics/shadow/DropShadowPainter;", "[Landroidx/compose/ui/graphics/shadow/DropShadowPainter;", "drawDropShadow", "reconcileDropShadowCache", "drawForShape", "hasBackground", "hasBorder", "hasForeground", "bgColor", "Landroidx/compose/ui/graphics/Color;", "bgBrush", "Landroidx/compose/ui/graphics/Brush;", "borderColor", "borderBrush", "foregroundColor", "foregroundBrush", "borderWidth", "", "drawForShape-9zt3ed4", "(Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;Landroidx/compose/ui/graphics/Shape;ZZZJLandroidx/compose/ui/graphics/Brush;JLandroidx/compose/ui/graphics/Brush;JLandroidx/compose/ui/graphics/Brush;F)V", "traverseKey", "getTraverseKey", "()Ljava/lang/Object;", "resolveStyleAndInvalidate", "initial", "onObservedReadsChanged", "sourceJob", "Lkotlinx/coroutines/Job;", "getSourceJob", "()Lkotlinx/coroutines/Job;", "setSourceJob", "(Lkotlinx/coroutines/Job;)V", "updateInteractionSources", "currentValue", "T", "Landroidx/compose/runtime/CompositionLocal;", "getCurrentValue", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "computeInheritedTextStyle", "Landroidx/compose/ui/text/TextStyle;", TypedValues.CycleType.S_WAVE_PHASE, "Landroidx/compose/foundation/text/modifiers/StylePhase;", "fallback", "computeInheritedTextStyle-B-LjeIk", "(ILandroidx/compose/ui/text/TextStyle;)Landroidx/compose/ui/text/TextStyle;", "ancestorNodes", "Landroidx/collection/MutableObjectList;", "getAncestorNodes$foundation", "()Landroidx/collection/MutableObjectList;", "setAncestorNodes$foundation", "(Landroidx/collection/MutableObjectList;)V", "resolveInheritedStyle", "resolveInheritedStyle$foundation", "cachedInheritedStyle", "inheritedStyleDirty", "getCachedInheritedStyle", "getCachedInheritedStyle$foundation", "saveInheritedStyles", "saveInheritedStyles$foundation", "invalidateTextLayout", "invalidateTextDraw", "onDetach", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class StyleOuterNode extends DelegatingNode implements LayoutModifierNode, DrawModifierNode, TraversableNode, CompositionLocalConsumerModifierNode, ObserverModifierNode, CompositionLocalAccessorScope, TextStyleProviderNode {
    public static final int $stable = 8;
    private ResolvedStyle _bufferOrNull;
    private StyleState _state;
    private MutableObjectList<StyleOuterNode> ancestorNodes;
    private StyleAnimations animations;
    private GraphicsLayer borderLayer;
    private Function0<GraphicsLayer> borderLayerProvider;
    private DropShadowPainter[] cachedDropShadowPainters;
    private ResolvedStyle cachedInheritedStyle;
    private InnerShadowPainter[] cachedInnerShadowPainters;
    private InteractionSource currentInteractionSource;
    private boolean inheritedStyleDirty;
    private StyleInnerNode innerNodeField;
    private Shadow[] lastDropShadow;
    private Shadow[] lastInnerShadow;
    private LayoutDirection lastLayoutDirection;
    private Outline lastOutline;
    private Shape lastShape;
    private long lastSize;
    private Function1<? super GraphicsLayerScope, Unit> layerBlock;
    private Job sourceJob;
    private Style style;
    private ResolvedStyle _resolved = new ResolvedStyle();
    private final BorderLogic borderLogic = new BorderLogic();

    public StyleOuterNode(StyleState styleState, Style style) {
        this.style = style;
        this._state = styleState == null ? new MutableStyleState(null) : styleState;
        this.lastSize = Size.INSTANCE.m5145getUnspecifiedNHjbRc();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    /* JADX INFO: renamed from: getInnerNodeField$foundation, reason: from getter */
    public final StyleInnerNode getInnerNodeField() {
        return this.innerNodeField;
    }

    public final void setInnerNodeField$foundation(StyleInnerNode styleInnerNode) {
        this.innerNodeField = styleInnerNode;
    }

    public final StyleInnerNode getInnerNode$foundation() {
        StyleInnerNode styleInnerNode = this.innerNodeField;
        if (styleInnerNode != null) {
            return styleInnerNode;
        }
        throw new IllegalStateException("StyleOuterNode with no corresponding StyleInnerNode".toString());
    }

    public final void setInnerNode$foundation(StyleInnerNode value) {
        this.innerNodeField = value;
    }

    /* JADX INFO: renamed from: getStyle$foundation, reason: from getter */
    public final Style getStyle() {
        return this.style;
    }

    public final void setStyle$foundation(Style value) {
        this.style = value;
        resolveStyleAndInvalidate$default(this, false, 1, null);
    }

    private final ResolvedStyle getBufferNonNull() {
        if (this._bufferOrNull == null) {
            this._bufferOrNull = new ResolvedStyle();
        }
        ResolvedStyle resolvedStyle = this._bufferOrNull;
        Intrinsics.checkNotNull(resolvedStyle);
        return resolvedStyle;
    }

    /* JADX INFO: renamed from: getAnimations$foundation, reason: from getter */
    public final StyleAnimations getAnimations() {
        return this.animations;
    }

    public final void setAnimations$foundation(StyleAnimations styleAnimations) {
        this.animations = styleAnimations;
    }

    /* JADX INFO: renamed from: getState$foundation, reason: from getter */
    public final StyleState get_state() {
        return this._state;
    }

    public final void setState$foundation(StyleState value) {
        if (!Intrinsics.areEqual(this._state, value)) {
            this._state = value;
            resolveStyleAndInvalidate$default(this, false, 1, null);
            LayoutModifierNodeKt.invalidateLayer(getInnerNode$foundation());
        }
    }

    public static /* synthetic */ ResolvedStyle resolveAnimatedStyleFor$foundation$default(StyleOuterNode styleOuterNode, int i, ResolvedStyle resolvedStyle, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            resolvedStyle = styleOuterNode._resolved;
        }
        return styleOuterNode.resolveAnimatedStyleFor$foundation(i, resolvedStyle);
    }

    public final ResolvedStyle resolveAnimatedStyleFor$foundation(int flags, ResolvedStyle base) {
        StyleAnimations animations = this.animations;
        if (animations != null && animations.isNotEmpty()) {
            return animations.withAnimations(DelegatableNodeKt.requireDensity(this), base, this, flags);
        }
        return base;
    }

    private final ResolvedStyle currentLayerStyle() {
        return resolveAnimatedStyleFor$foundation$default(this, 4, null, 2, null);
    }

    private final ResolvedStyle currentLayoutStyle() {
        return resolveAnimatedStyleFor$foundation$default(this, 8, null, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:73:0x016c  */
    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public androidx.compose.ui.layout.MeasureResult mo67measure3p2s80s(androidx.compose.ui.layout.MeasureScope r27, androidx.compose.ui.layout.Measurable r28, final long r29) {
        /*
            Method dump skipped, instruction units count: 521
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.style.StyleOuterNode.mo67measure3p2s80s(androidx.compose.ui.layout.MeasureScope, androidx.compose.ui.layout.Measurable, long):androidx.compose.ui.layout.MeasureResult");
    }

    static final Unit measure_3p2s80s$lambda$0(StyleOuterNode this$0, long $constraints, Placeable $placeable, float $end, float $start, float $bottom, float $top, Placeable.PlacementScope $this$layout) {
        int iRound;
        int y;
        ResolvedStyle resolvedLayoutStyle = this$0.currentLayoutStyle();
        if (this$0.shouldPlaceRelativeToRight(resolvedLayoutStyle)) {
            iRound = (Constraints.m8103getMaxWidthimpl($constraints) - $placeable.getWidth()) - Math.round($end);
        } else {
            iRound = Math.round($start);
        }
        int x = iRound;
        if (this$0.shouldPlaceRelativeToBottom(resolvedLayoutStyle)) {
            y = (Constraints.m8102getMaxHeightimpl($constraints) - $placeable.getHeight()) - Math.round($bottom);
        } else {
            y = Math.round($top);
        }
        if ((resolvedLayoutStyle.flags & 4) != 0) {
            Placeable.PlacementScope.placeWithLayer$default($this$layout, $placeable, x, y, 0.0f, this$0.getLayerBlockNonNull$foundation(), 4, (Object) null);
        } else {
            Placeable.PlacementScope.place$default($this$layout, $placeable, x, y, 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    private final boolean shouldPlaceRelativeToRight(ResolvedStyle $this$shouldPlaceRelativeToRight) {
        float $this$isSpecified$iv = $this$shouldPlaceRelativeToRight.getRight();
        if (!Float.isNaN($this$isSpecified$iv)) {
            float $this$isUnspecified$iv = $this$shouldPlaceRelativeToRight.getLeft();
            if (Float.isNaN($this$isUnspecified$iv)) {
                return true;
            }
        }
        return false;
    }

    private final boolean shouldPlaceRelativeToBottom(ResolvedStyle $this$shouldPlaceRelativeToBottom) {
        float $this$isSpecified$iv = $this$shouldPlaceRelativeToBottom.getBottom();
        if (!Float.isNaN($this$isSpecified$iv)) {
            float $this$isUnspecified$iv = $this$shouldPlaceRelativeToBottom.getTop();
            if (Float.isNaN($this$isUnspecified$iv)) {
                return true;
            }
        }
        return false;
    }

    public final Function1<GraphicsLayerScope, Unit> getLayerBlock$foundation() {
        return this.layerBlock;
    }

    public final void setLayerBlock$foundation(Function1<? super GraphicsLayerScope, Unit> function1) {
        this.layerBlock = function1;
    }

    public final Function1<GraphicsLayerScope, Unit> getLayerBlockNonNull$foundation() {
        Function1 function1 = this.layerBlock;
        if (function1 == null) {
            final StyleOuterNode $this$_get_layerBlockNonNull__u24lambda_u240 = this;
            Function1<GraphicsLayerScope, Unit> function12 = new Function1() { // from class: androidx.compose.foundation.style.StyleOuterNode$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return StyleOuterNode._get_layerBlockNonNull_$lambda$0$0(this.f$0, (GraphicsLayerScope) obj);
                }
            };
            $this$_get_layerBlockNonNull__u24lambda_u240.layerBlock = function12;
            return function12;
        }
        return function1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _get_layerBlockNonNull_$lambda$0$0(StyleOuterNode $this_run, GraphicsLayerScope graphicsLayerScope) {
        $this_run.updateLayer(graphicsLayerScope);
        return Unit.INSTANCE;
    }

    private final void updateLayer(GraphicsLayerScope $this$updateLayer) {
        ResolvedStyle resolved = currentLayerStyle();
        $this$updateLayer.setAlpha(resolved.getAlpha());
        $this$updateLayer.setScaleX(resolved.getScaleX());
        $this$updateLayer.setScaleY(resolved.getScaleY());
        $this$updateLayer.setTranslationX(resolved.getTranslationX());
        $this$updateLayer.setTranslationY(resolved.getTranslationY());
        $this$updateLayer.setRotationX(resolved.getRotationX());
        $this$updateLayer.setRotationY(resolved.getRotationY());
        $this$updateLayer.setRotationZ(resolved.getRotationZ());
        $this$updateLayer.mo5514setTransformOrigin__ExYCQ(resolved.getTransformOrigin());
        $this$updateLayer.setClip(resolved.getClip());
        $this$updateLayer.setShape(resolved.getShape());
    }

    /* JADX INFO: renamed from: getOutline-12SF9DM, reason: not valid java name */
    private final Outline m1469getOutline12SF9DM(ContentDrawScope $this$getOutline_u2d12SF9DM, long size, Shape shape) {
        Outline outline;
        if (Size.m5133equalsimpl0(this.lastSize, size) && this.lastLayoutDirection == $this$getOutline_u2d12SF9DM.getLayoutDirection() && Intrinsics.areEqual(this.lastShape, shape)) {
            outline = this.lastOutline;
            Intrinsics.checkNotNull(outline);
        } else {
            outline = shape.mo342createOutlinePq9zytI(size, $this$getOutline_u2d12SF9DM.getLayoutDirection(), $this$getOutline_u2d12SF9DM);
        }
        this.lastOutline = outline;
        this.lastSize = size;
        this.lastLayoutDirection = $this$getOutline_u2d12SF9DM.getLayoutDirection();
        return outline;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) throws Throwable {
        ResolvedStyle resolved = resolveAnimatedStyleFor$foundation$default(this, 2, null, 2, null);
        long bgColor = resolved.getBackgroundColor();
        Brush bgBrush = resolved.getBackgroundBrush();
        long foregroundColor = resolved.getForegroundColor();
        Brush foregroundBrush = resolved.getForegroundBrush();
        long borderColor = resolved.getBorderColor();
        Brush borderBrush = resolved.getBorderBrush();
        float borderWidth = resolved.getBorderWidth();
        float halfStrokeWidth = borderWidth / 2.0f;
        Shape shape = resolved.getShape();
        boolean hasForeground = true;
        boolean hasBorder = halfStrokeWidth > 0.0f;
        boolean hasBackground = ((bgColor > 16L ? 1 : (bgColor == 16L ? 0 : -1)) != 0) || bgBrush != null;
        if (!(foregroundColor != 16) && foregroundBrush == null) {
            hasForeground = false;
        }
        drawDropShadow($this$draw, resolved);
        m1471drawForShape9zt3ed4($this$draw, shape, hasBackground, hasBorder, hasForeground, bgColor, bgBrush, borderColor, borderBrush, foregroundColor, foregroundBrush, borderWidth);
        drawInnerShadow($this$draw, resolved);
        this.lastShape = shape;
    }

    private final void drawInnerShadow(ContentDrawScope $this$drawInnerShadow, int index, Shape shape, Shadow shadow) throws Throwable {
        Shadow[] shadowArr = this.lastInnerShadow;
        Shadow lastShadow = shadowArr != null ? (Shadow) ArraysKt.getOrNull(shadowArr, index) : null;
        InnerShadowPainter[] innerShadowPainterArr = this.cachedInnerShadowPainters;
        InnerShadowPainter lastPainter = innerShadowPainterArr != null ? (InnerShadowPainter) ArraysKt.getOrNull(innerShadowPainterArr, index) : null;
        InnerShadowPainter painter = (!Intrinsics.areEqual(lastShadow, shadow) || lastPainter == null) ? DelegatableNodeKt.requireGraphicsContext(this).getShadowContext().createInnerShadowPainter(shape, shadow) : lastPainter;
        Shadow[] it = this.lastInnerShadow;
        if (it != null) {
            it[index] = shadow;
        }
        InnerShadowPainter[] it2 = this.cachedInnerShadowPainters;
        if (it2 != null) {
            it2[index] = painter;
        }
        InnerShadowPainter $this$drawInnerShadow_u24lambda_u242 = painter;
        Painter.m6012drawx_KDEd0$default($this$drawInnerShadow_u24lambda_u242, $this$drawInnerShadow, $this$drawInnerShadow.mo5887getSizeNHjbRc(), 0.0f, null, 6, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void reconcileInnerShadowCache(java.lang.Object r8, androidx.compose.ui.graphics.Shape r9) {
        /*
            r7 = this;
            androidx.compose.ui.graphics.shadow.Shadow[] r0 = r7.lastInnerShadow
            androidx.compose.ui.graphics.shadow.InnerShadowPainter[] r1 = r7.cachedInnerShadowPainters
            boolean r2 = r8 instanceof java.lang.Object[]
            if (r2 == 0) goto Ld
            r2 = r8
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            int r2 = r2.length
            goto Le
        Ld:
            r2 = 1
        Le:
            r3 = 0
            r4 = 0
            if (r0 == 0) goto L45
            androidx.compose.ui.graphics.Shape r5 = r7.lastShape
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r9)
            if (r5 != 0) goto L1b
            goto L45
        L1b:
            int r5 = r0.length
            if (r5 == r2) goto L5c
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r0, r2)
            java.lang.String r6 = "copyOf(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            androidx.compose.ui.graphics.shadow.Shadow[] r5 = (androidx.compose.ui.graphics.shadow.Shadow[]) r5
            r7.lastInnerShadow = r5
            if (r1 == 0) goto L39
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r1, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            androidx.compose.ui.graphics.shadow.InnerShadowPainter[] r5 = (androidx.compose.ui.graphics.shadow.InnerShadowPainter[]) r5
            if (r5 != 0) goto L42
        L39:
            androidx.compose.ui.graphics.shadow.InnerShadowPainter[] r5 = new androidx.compose.ui.graphics.shadow.InnerShadowPainter[r2]
        L3b:
            if (r4 >= r2) goto L42
            r5[r4] = r3
            int r4 = r4 + 1
            goto L3b
        L42:
            r7.cachedInnerShadowPainters = r5
            goto L5c
        L45:
            androidx.compose.ui.graphics.shadow.Shadow[] r5 = new androidx.compose.ui.graphics.shadow.Shadow[r2]
            r6 = r4
        L48:
            if (r6 >= r2) goto L4f
            r5[r6] = r3
            int r6 = r6 + 1
            goto L48
        L4f:
            r7.lastInnerShadow = r5
            androidx.compose.ui.graphics.shadow.InnerShadowPainter[] r5 = new androidx.compose.ui.graphics.shadow.InnerShadowPainter[r2]
        L53:
            if (r4 >= r2) goto L5a
            r5[r4] = r3
            int r4 = r4 + 1
            goto L53
        L5a:
            r7.cachedInnerShadowPainters = r5
        L5c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.style.StyleOuterNode.reconcileInnerShadowCache(java.lang.Object, androidx.compose.ui.graphics.Shape):void");
    }

    public final void drawInnerShadow(ContentDrawScope $this$drawInnerShadow, ResolvedStyle resolved) throws Throwable {
        Object shadowOrArray = resolved.getInnerShadow();
        if (shadowOrArray == null) {
            return;
        }
        Shape shape = resolved.getShape();
        reconcileInnerShadowCache(shadowOrArray, shape);
        if (shadowOrArray instanceof Object[]) {
            int length = ((Object[]) shadowOrArray).length;
            for (int i = 0; i < length; i++) {
                Object shadow = ((Object[]) shadowOrArray)[i];
                if (shadow instanceof Shadow) {
                    drawInnerShadow($this$drawInnerShadow, i, shape, (Shadow) shadow);
                }
            }
            return;
        }
        if (shadowOrArray instanceof Shadow) {
            drawInnerShadow($this$drawInnerShadow, 0, shape, (Shadow) shadowOrArray);
        }
    }

    private final void drawDropShadow(ContentDrawScope $this$drawDropShadow, int index, Shape shape, Shadow shadow) throws Throwable {
        Shadow[] shadowArr = this.lastDropShadow;
        Shadow lastShadow = shadowArr != null ? (Shadow) ArraysKt.getOrNull(shadowArr, index) : null;
        DropShadowPainter[] dropShadowPainterArr = this.cachedDropShadowPainters;
        DropShadowPainter lastPainter = dropShadowPainterArr != null ? (DropShadowPainter) ArraysKt.getOrNull(dropShadowPainterArr, index) : null;
        DropShadowPainter painter = (!Intrinsics.areEqual(lastShadow, shadow) || lastPainter == null) ? DelegatableNodeKt.requireGraphicsContext(this).getShadowContext().createDropShadowPainter(shape, shadow) : lastPainter;
        Shadow[] it = this.lastDropShadow;
        if (it != null) {
            it[index] = shadow;
        }
        DropShadowPainter[] it2 = this.cachedDropShadowPainters;
        if (it2 != null) {
            it2[index] = painter;
        }
        DropShadowPainter $this$drawDropShadow_u24lambda_u242 = painter;
        Painter.m6012drawx_KDEd0$default($this$drawDropShadow_u24lambda_u242, $this$drawDropShadow, $this$drawDropShadow.mo5887getSizeNHjbRc(), 0.0f, null, 6, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void reconcileDropShadowCache(java.lang.Object r8, androidx.compose.ui.graphics.Shape r9) {
        /*
            r7 = this;
            androidx.compose.ui.graphics.shadow.Shadow[] r0 = r7.lastDropShadow
            androidx.compose.ui.graphics.shadow.DropShadowPainter[] r1 = r7.cachedDropShadowPainters
            boolean r2 = r8 instanceof java.lang.Object[]
            if (r2 == 0) goto Ld
            r2 = r8
            java.lang.Object[] r2 = (java.lang.Object[]) r2
            int r2 = r2.length
            goto Le
        Ld:
            r2 = 1
        Le:
            r3 = 0
            r4 = 0
            if (r0 == 0) goto L45
            androidx.compose.ui.graphics.Shape r5 = r7.lastShape
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r9)
            if (r5 != 0) goto L1b
            goto L45
        L1b:
            int r5 = r0.length
            if (r5 == r2) goto L5c
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r0, r2)
            java.lang.String r6 = "copyOf(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            androidx.compose.ui.graphics.shadow.Shadow[] r5 = (androidx.compose.ui.graphics.shadow.Shadow[]) r5
            r7.lastDropShadow = r5
            if (r1 == 0) goto L39
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r1, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            androidx.compose.ui.graphics.shadow.DropShadowPainter[] r5 = (androidx.compose.ui.graphics.shadow.DropShadowPainter[]) r5
            if (r5 != 0) goto L42
        L39:
            androidx.compose.ui.graphics.shadow.DropShadowPainter[] r5 = new androidx.compose.ui.graphics.shadow.DropShadowPainter[r2]
        L3b:
            if (r4 >= r2) goto L42
            r5[r4] = r3
            int r4 = r4 + 1
            goto L3b
        L42:
            r7.cachedDropShadowPainters = r5
            goto L5c
        L45:
            androidx.compose.ui.graphics.shadow.Shadow[] r5 = new androidx.compose.ui.graphics.shadow.Shadow[r2]
            r6 = r4
        L48:
            if (r6 >= r2) goto L4f
            r5[r6] = r3
            int r6 = r6 + 1
            goto L48
        L4f:
            r7.lastDropShadow = r5
            androidx.compose.ui.graphics.shadow.DropShadowPainter[] r5 = new androidx.compose.ui.graphics.shadow.DropShadowPainter[r2]
        L53:
            if (r4 >= r2) goto L5a
            r5[r4] = r3
            int r4 = r4 + 1
            goto L53
        L5a:
            r7.cachedDropShadowPainters = r5
        L5c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.style.StyleOuterNode.reconcileDropShadowCache(java.lang.Object, androidx.compose.ui.graphics.Shape):void");
    }

    public final void drawDropShadow(ContentDrawScope $this$drawDropShadow, ResolvedStyle resolved) throws Throwable {
        Object shadowOrArray = resolved.getDropShadow();
        if (shadowOrArray == null) {
            return;
        }
        Shape shape = resolved.getShape();
        reconcileDropShadowCache(shadowOrArray, shape);
        if (shadowOrArray instanceof Object[]) {
            int length = ((Object[]) shadowOrArray).length;
            for (int i = 0; i < length; i++) {
                Object shadow = ((Object[]) shadowOrArray)[i];
                if (shadow instanceof Shadow) {
                    drawDropShadow($this$drawDropShadow, i, shape, (Shadow) shadow);
                }
            }
            return;
        }
        if (shadowOrArray instanceof Shadow) {
            drawDropShadow($this$drawDropShadow, 0, shape, (Shadow) shadowOrArray);
        }
    }

    /* JADX INFO: renamed from: drawForShape-9zt3ed4, reason: not valid java name */
    public final void m1471drawForShape9zt3ed4(ContentDrawScope $this$drawForShape_u2d9zt3ed4, Shape shape, boolean hasBackground, boolean hasBorder, boolean hasForeground, long bgColor, Brush bgBrush, long borderColor, Brush borderBrush, long foregroundColor, Brush foregroundBrush, final float borderWidth) {
        Function0<GraphicsLayer> function0;
        Outline outline = m1469getOutline12SF9DM($this$drawForShape_u2d9zt3ed4, $this$drawForShape_u2d9zt3ed4.mo5887getSizeNHjbRc(), shape);
        if (hasBackground) {
            if (bgBrush == null) {
                OutlineKt.m5585drawOutlinewDX37Ww($this$drawForShape_u2d9zt3ed4, outline, bgColor, (60 & 4) != 0 ? 1.0f : 0.0f, (60 & 8) != 0 ? Fill.INSTANCE : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
            } else {
                OutlineKt.m5583drawOutlinehn5TExg($this$drawForShape_u2d9zt3ed4, outline, bgBrush, (60 & 4) != 0 ? 1.0f : 0.0f, (60 & 8) != 0 ? Fill.INSTANCE : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
            }
        }
        $this$drawForShape_u2d9zt3ed4.drawContent();
        if (hasForeground) {
            if (foregroundBrush == null) {
                OutlineKt.m5585drawOutlinewDX37Ww($this$drawForShape_u2d9zt3ed4, outline, foregroundColor, (60 & 4) != 0 ? 1.0f : 0.0f, (60 & 8) != 0 ? Fill.INSTANCE : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
            } else {
                OutlineKt.m5583drawOutlinehn5TExg($this$drawForShape_u2d9zt3ed4, outline, foregroundBrush, (60 & 4) != 0 ? 1.0f : 0.0f, (60 & 8) != 0 ? Fill.INSTANCE : null, (60 & 16) != 0 ? null : null, (60 & 32) != 0 ? DrawScope.INSTANCE.m5889getDefaultBlendMode0nO6VwU() : 0);
            }
        }
        if (hasBorder) {
            SolidColor brush = borderBrush == null ? new SolidColor(borderColor, null) : borderBrush;
            BorderLogic borderLogic = this.borderLogic;
            ContentDrawScope contentDrawScope = $this$drawForShape_u2d9zt3ed4;
            Function0 function02 = new Function0() { // from class: androidx.compose.foundation.style.StyleOuterNode$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(StyleOuterNode.drawForShape_9zt3ed4$lambda$0(borderWidth));
                }
            };
            Function0<GraphicsLayer> function03 = this.borderLayerProvider;
            if (function03 != null) {
                function0 = function03;
            } else {
                Function0<GraphicsLayer> function04 = new Function0() { // from class: androidx.compose.foundation.style.StyleOuterNode$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return StyleOuterNode.drawForShape_9zt3ed4$lambda$1(this.f$0);
                    }
                };
                this.borderLayerProvider = function04;
                Unit unit = Unit.INSTANCE;
                function0 = function04;
            }
            BorderLogic.m377drawBorder2gY9BTk$foundation$default(borderLogic, contentDrawScope, function02, brush, function0, outline, 0L, 32, null);
        }
    }

    static final float drawForShape_9zt3ed4$lambda$0(float $borderWidth) {
        return $borderWidth;
    }

    static final GraphicsLayer drawForShape_9zt3ed4$lambda$1(StyleOuterNode this$0) {
        GraphicsLayer graphicsLayer = this$0.borderLayer;
        if (graphicsLayer == null) {
            GraphicsLayer it = DelegatableNodeKt.requireGraphicsContext(this$0).createGraphicsLayer();
            this$0.borderLayer = it;
            return it;
        }
        return graphicsLayer;
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public Object getTraverseKey() {
        return StyleModifierKt.OuterNodeKey;
    }

    public static /* synthetic */ void resolveStyleAndInvalidate$default(StyleOuterNode styleOuterNode, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        styleOuterNode.resolveStyleAndInvalidate(z);
    }

    public final void resolveStyleAndInvalidate(final boolean initial) {
        if (getIsAttached()) {
            final ResolvedStyle prev = initial ? null : this._resolved;
            final ResolvedStyle next = initial ? this._resolved : getBufferNonNull();
            final Density density = DelegatableNodeKt.requireDensity(this);
            next.clear$foundation();
            StyleAnimations styleAnimations = this.animations;
            if (styleAnimations != null) {
                styleAnimations.preResolve();
            }
            final Ref.IntRef animChanges = new Ref.IntRef();
            ObserverModifierNodeKt.observeReads(this, new Function0() { // from class: androidx.compose.foundation.style.StyleOuterNode$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return StyleOuterNode.resolveStyleAndInvalidate$lambda$0(next, this, density, prev, animChanges, initial);
                }
            });
            int changes = animChanges.element | (prev != null ? ResolvedStyle.diff$foundation$default(prev, next, 0, 2, null) : next.flags);
            if (!Intrinsics.areEqual(this._state.getInteractionSource(), this.currentInteractionSource)) {
                updateInteractionSources();
            }
            if (initial) {
                return;
            }
            if ((changes & 1) != 0) {
                LayoutModifierNodeKt.invalidateMeasurement(getInnerNode$foundation());
            }
            if ((changes & 8) != 0) {
                LayoutModifierNodeKt.invalidateMeasurement(this);
            }
            if ((changes & 2) != 0) {
                LayoutModifierNodeKt.invalidateLayer(getInnerNode$foundation());
            }
            if ((changes & 4) != 0) {
                LayoutModifierNodeKt.updateLayerBlock(this, getLayerBlockNonNull$foundation());
            }
            if ((changes & 32) != 0) {
                invalidateTextLayout();
            }
            if ((changes & 64) != 0) {
                invalidateTextDraw();
            }
        }
    }

    static final Unit resolveStyleAndInvalidate$lambda$0(ResolvedStyle $next, StyleOuterNode this$0, Density $density, ResolvedStyle $prev, Ref.IntRef $animChanges, boolean $initial) {
        $next.resolve$foundation(this$0.style, this$0, $density, false);
        this$0._resolved = $next;
        this$0._bufferOrNull = $prev;
        StyleAnimations styleAnimations = this$0.animations;
        $animChanges.element = styleAnimations != null ? styleAnimations.postResolve(this$0, $density, !$initial) : 0;
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        resolveStyleAndInvalidate$default(this, false, 1, null);
    }

    public final Job getSourceJob() {
        return this.sourceJob;
    }

    public final void setSourceJob(Job job) {
        this.sourceJob = job;
    }

    public final void updateInteractionSources() {
        Job job = this.sourceJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        InteractionSource source = this._state.getInteractionSource();
        this.currentInteractionSource = source;
        if (source != null) {
            this.sourceJob = BuildersKt__Builders_commonKt.launch$default(getCoroutineScope(), null, null, new AnonymousClass1(source, null), 3, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.style.StyleOuterNode$updateInteractionSources$1, reason: invalid class name */
    /* JADX INFO: compiled from: StyleModifier.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.style.StyleOuterNode$updateInteractionSources$1", f = "StyleModifier.kt", i = {}, l = {715}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ InteractionSource $source;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(InteractionSource interactionSource, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$source = interactionSource;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return StyleOuterNode.this.new AnonymousClass1(this.$source, continuation);
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
                    this.label = 1;
                    if (StyleOuterNode.this._state.processInteractions$foundation(this.$source, this) == coroutine_suspended) {
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

    @Override // androidx.compose.runtime.CompositionLocalAccessorScope
    public <T> T getCurrentValue(CompositionLocal<T> compositionLocal) {
        return (T) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, compositionLocal);
    }

    @Override // androidx.compose.foundation.text.modifiers.TextStyleProviderNode
    /* JADX INFO: renamed from: computeInheritedTextStyle-B-LjeIk, reason: not valid java name */
    public TextStyle mo1470computeInheritedTextStyleBLjeIk(int phase, TextStyle fallback) {
        TextStyle textStyle$foundation;
        ResolvedStyle resolvedStyleResolveInheritedStyle$foundation = resolveInheritedStyle$foundation(StyleModifierKt.m1468toFlagsuwmK9pY(phase));
        return (resolvedStyleResolveInheritedStyle$foundation == null || (textStyle$foundation = resolvedStyleResolveInheritedStyle$foundation.toTextStyle$foundation(fallback)) == null) ? fallback : textStyle$foundation;
    }

    public final MutableObjectList<StyleOuterNode> getAncestorNodes$foundation() {
        return this.ancestorNodes;
    }

    public final void setAncestorNodes$foundation(MutableObjectList<StyleOuterNode> mutableObjectList) {
        this.ancestorNodes = mutableObjectList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00b0  */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, androidx.collection.MutableObjectList<androidx.compose.foundation.style.StyleOuterNode>] */
    /* JADX WARN: Type inference failed for: r2v8, types: [T, androidx.collection.MutableObjectList<androidx.compose.foundation.style.StyleOuterNode>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final androidx.compose.foundation.style.ResolvedStyle resolveInheritedStyle$foundation(int r22) {
        /*
            Method dump skipped, instruction units count: 350
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.style.StyleOuterNode.resolveInheritedStyle$foundation(int):androidx.compose.foundation.style.ResolvedStyle");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0025  */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, androidx.collection.MutableObjectList<androidx.compose.foundation.style.StyleOuterNode>] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final boolean resolveInheritedStyle$lambda$1(kotlin.jvm.internal.Ref.ObjectRef r5, androidx.compose.foundation.style.StyleOuterNode r6, androidx.compose.ui.node.TraversableNode r7) {
        /*
            boolean r0 = r7 instanceof androidx.compose.foundation.style.StyleOuterNode
            r1 = 1
            if (r0 != 0) goto L6
            return r1
        L6:
            r0 = r7
            androidx.compose.foundation.style.StyleOuterNode r0 = (androidx.compose.foundation.style.StyleOuterNode) r0
            androidx.compose.foundation.style.ResolvedStyle r0 = r0._resolved
            int r0 = r0.flags
            r0 = r0 & 96
            r2 = 0
            if (r0 != 0) goto L25
            r0 = r7
            androidx.compose.foundation.style.StyleOuterNode r0 = (androidx.compose.foundation.style.StyleOuterNode) r0
            androidx.compose.foundation.style.StyleAnimations r0 = r0.animations
            if (r0 == 0) goto L22
            boolean r0 = r0.isNotEmpty()
            if (r0 != r1) goto L22
            r0 = r1
            goto L23
        L22:
            r0 = r2
        L23:
            if (r0 == 0) goto L3d
        L25:
            T r0 = r5.element
            androidx.collection.MutableObjectList r0 = (androidx.collection.MutableObjectList) r0
            if (r0 != 0) goto L3a
            r0 = 0
            androidx.collection.MutableObjectList r3 = new androidx.collection.MutableObjectList
            r4 = 0
            r3.<init>(r2, r1, r4)
            r0 = r3
            r2 = 0
            r5.element = r0
            r6.ancestorNodes = r0
        L3a:
            r0.add(r7)
        L3d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.style.StyleOuterNode.resolveInheritedStyle$lambda$1(kotlin.jvm.internal.Ref$ObjectRef, androidx.compose.foundation.style.StyleOuterNode, androidx.compose.ui.node.TraversableNode):boolean");
    }

    public final ResolvedStyle getCachedInheritedStyle$foundation() {
        if (this.inheritedStyleDirty) {
            return null;
        }
        return this.cachedInheritedStyle;
    }

    public final void saveInheritedStyles$foundation(ResolvedStyle style) {
        this.inheritedStyleDirty = false;
        this.cachedInheritedStyle = style;
    }

    private final void invalidateTextLayout() {
        this.inheritedStyleDirty = true;
        DelegatableNodeKt.invalidateMeasurementForSubtree(this);
    }

    private final void invalidateTextDraw() {
        this.inheritedStyleDirty = true;
        DelegatableNodeKt.invalidateDrawForSubtree(this);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        super.onDetach();
        GraphicsLayer it = this.borderLayer;
        if (it != null) {
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(it);
            this.borderLayer = null;
        }
        this.borderLayerProvider = null;
    }
}
