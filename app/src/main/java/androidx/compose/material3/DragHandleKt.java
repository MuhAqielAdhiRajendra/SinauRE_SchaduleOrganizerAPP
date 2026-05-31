package androidx.compose.material3;

import androidx.compose.foundation.HoverableKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.interaction.DragInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: DragHandle.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001aA\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0007¢\u0006\u0002\u0010\f\u001a0\u0010\r\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u000fH\u0002¨\u0006\u0011²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0013X\u008a\u008e\u0002"}, d2 = {"VerticalDragHandle", "", "modifier", "Landroidx/compose/ui/Modifier;", "sizes", "Landroidx/compose/material3/DragHandleSizes;", "colors", "Landroidx/compose/material3/DragHandleColors;", "shapes", "Landroidx/compose/material3/DragHandleShapes;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DragHandleSizes;Landroidx/compose/material3/DragHandleColors;Landroidx/compose/material3/DragHandleShapes;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "pressable", "onPressed", "Lkotlin/Function0;", "onReleasedOrCancelled", "material3", "isDragged", "", "isPressed"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class DragHandleKt {
    static final Unit VerticalDragHandle$lambda$16(Modifier modifier, DragHandleSizes dragHandleSizes, DragHandleColors dragHandleColors, DragHandleShapes dragHandleShapes, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        VerticalDragHandle(modifier, dragHandleSizes, dragHandleColors, dragHandleShapes, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void VerticalDragHandle(Modifier modifier, DragHandleSizes sizes, DragHandleColors colors, DragHandleShapes shapes, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        DragHandleSizes dragHandleSizes;
        final DragHandleColors colors2;
        final DragHandleShapes shapes2;
        MutableInteractionSource interactionSource2;
        final DragHandleSizes sizes2;
        final DragHandleColors colors3;
        final Modifier modifier3;
        final MutableInteractionSource interactionSource3;
        final DragHandleShapes shapes3;
        Modifier.Companion modifier4;
        final DragHandleSizes sizes3;
        MutableInteractionSource interactionSource4;
        Composer $composer2 = $composer.startRestartGroup(1693656835);
        ComposerKt.sourceInformation($composer2, "C(VerticalDragHandle)N(modifier,sizes,colors,shapes,interactionSource)81@3988L25,82@4035L34,88@4262L20,88@4284L21,89@4338L307,98@4670L796,115@5495L299,83@4074L1783:DragHandle.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                dragHandleSizes = sizes;
                int i3 = $composer2.changed(dragHandleSizes) ? 32 : 16;
                $dirty |= i3;
            } else {
                dragHandleSizes = sizes;
            }
            $dirty |= i3;
        } else {
            dragHandleSizes = sizes;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                colors2 = colors;
                int i4 = $composer2.changed(colors2) ? 256 : 128;
                $dirty |= i4;
            } else {
                colors2 = colors;
            }
            $dirty |= i4;
        } else {
            colors2 = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                shapes2 = shapes;
                int i5 = $composer2.changed(shapes2) ? 2048 : 1024;
                $dirty |= i5;
            } else {
                shapes2 = shapes;
            }
            $dirty |= i5;
        } else {
            shapes2 = shapes;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty |= $composer2.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        boolean z = true;
        if ($composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "75@3689L8,76@3757L8");
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    sizes3 = VerticalDragHandleDefaults.INSTANCE.sizes();
                    $dirty &= -113;
                } else {
                    sizes3 = dragHandleSizes;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    colors2 = VerticalDragHandleDefaults.INSTANCE.colors($composer2, 6);
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    shapes2 = VerticalDragHandleDefaults.INSTANCE.shapes($composer2, 6);
                }
                if (i6 != 0) {
                    interactionSource2 = null;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                modifier4 = modifier2;
                sizes3 = dragHandleSizes;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1693656835, $dirty, -1, "androidx.compose.material3.VerticalDragHandle (DragHandle.kt:78)");
            }
            if (interactionSource2 == null) {
                $composer2.startReplaceGroup(-1544610024);
                ComposerKt.sourceInformation($composer2, "80@3909L39");
                ComposerKt.sourceInformationMarkerStart($composer2, -188373462, "CC(remember):DragHandle.kt#9igjgp");
                Object it$iv = $composer2.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer2.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                interactionSource4 = (MutableInteractionSource) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-188374113);
                $composer2.endReplaceGroup();
                interactionSource4 = interactionSource2;
            }
            final State<Boolean> stateCollectIsDraggedAsState = DragInteractionKt.collectIsDraggedAsState(interactionSource4, $composer2, 0);
            ComposerKt.sourceInformationMarkerStart($composer2, -188369435, "CC(remember):DragHandle.kt#9igjgp");
            Object it$iv2 = $composer2.rememberedValue();
            Modifier modifier5 = modifier4;
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                Object value$iv2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer2.updateRememberedValue(value$iv2);
                it$iv2 = value$iv2;
            }
            final MutableState isPressed$delegate = (MutableState) it$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierHoverable$default = HoverableKt.hoverable$default(InteractiveComponentSizeKt.minimumInteractiveComponentSize(modifier5), interactionSource4, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, -188362185, "CC(remember):DragHandle.kt#9igjgp");
            Object it$iv3 = $composer2.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                Object value$iv3 = new Function0() { // from class: androidx.compose.material3.DragHandleKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DragHandleKt.VerticalDragHandle$lambda$6$lambda$5(isPressed$delegate);
                    }
                };
                $composer2.updateRememberedValue(value$iv3);
                it$iv3 = value$iv3;
            }
            Function0 function0 = (Function0) it$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, -188361480, "CC(remember):DragHandle.kt#9igjgp");
            Object it$iv4 = $composer2.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                Object value$iv4 = new Function0() { // from class: androidx.compose.material3.DragHandleKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DragHandleKt.VerticalDragHandle$lambda$8$lambda$7(isPressed$delegate);
                    }
                };
                $composer2.updateRememberedValue(value$iv4);
                it$iv4 = value$iv4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierPressable = pressable(modifierHoverable$default, interactionSource4, function0, (Function0) it$iv4);
            ComposerKt.sourceInformationMarkerStart($composer2, -188359466, "CC(remember):DragHandle.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(stateCollectIsDraggedAsState) | (((($dirty & 7168) ^ 3072) > 2048 && $composer2.changed(shapes2)) || ($dirty & 3072) == 2048);
            Object it$iv5 = $composer2.rememberedValue();
            if (invalid$iv || it$iv5 == Composer.INSTANCE.getEmpty()) {
                Object value$iv5 = new Function1() { // from class: androidx.compose.material3.DragHandleKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DragHandleKt.VerticalDragHandle$lambda$10$lambda$9(shapes2, stateCollectIsDraggedAsState, isPressed$delegate, (GraphicsLayerScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv5);
                it$iv5 = value$iv5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierPressable, (Function1) it$iv5);
            ComposerKt.sourceInformationMarkerStart($composer2, -188348353, "CC(remember):DragHandle.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changed(stateCollectIsDraggedAsState) | (((($dirty & 112) ^ 48) > 32 && $composer2.changed(sizes3)) || ($dirty & 48) == 32);
            Object it$iv6 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                Object value$iv6 = new Function3() { // from class: androidx.compose.material3.DragHandleKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        return DragHandleKt.VerticalDragHandle$lambda$13$lambda$12(sizes3, stateCollectIsDraggedAsState, isPressed$delegate, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
                    }
                };
                $composer2.updateRememberedValue(value$iv6);
                it$iv6 = value$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifierLayout = LayoutModifierKt.layout(modifierGraphicsLayer, (Function3) it$iv6);
            ComposerKt.sourceInformationMarkerStart($composer2, -188322450, "CC(remember):DragHandle.kt#9igjgp");
            boolean zChanged = $composer2.changed(stateCollectIsDraggedAsState);
            if (((($dirty & 896) ^ 384) <= 256 || !$composer2.changed(colors2)) && ($dirty & 384) != 256) {
                z = false;
            }
            boolean invalid$iv3 = zChanged | z;
            Object it$iv7 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv7 == Composer.INSTANCE.getEmpty()) {
                Object value$iv7 = new Function1() { // from class: androidx.compose.material3.DragHandleKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DragHandleKt.VerticalDragHandle$lambda$15$lambda$14(colors2, stateCollectIsDraggedAsState, isPressed$delegate, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv7);
                it$iv7 = value$iv7;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BoxKt.Box(IndicationKt.indication(DrawModifierKt.drawBehind(modifierLayout, (Function1) it$iv7), interactionSource4, RippleKt.m2847rippleH2RKhps$default(false, 0.0f, 0L, 7, null)), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sizes2 = sizes3;
            colors3 = colors2;
            modifier3 = modifier5;
            shapes3 = shapes2;
            interactionSource3 = interactionSource2;
        } else {
            $composer2.skipToGroupEnd();
            sizes2 = dragHandleSizes;
            colors3 = colors2;
            modifier3 = modifier2;
            interactionSource3 = interactionSource2;
            shapes3 = shapes2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DragHandleKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DragHandleKt.VerticalDragHandle$lambda$16(modifier3, sizes2, colors3, shapes3, interactionSource3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean VerticalDragHandle$lambda$1(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final boolean VerticalDragHandle$lambda$3(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    private static final void VerticalDragHandle$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit VerticalDragHandle$lambda$6$lambda$5(MutableState $isPressed$delegate) {
        VerticalDragHandle$lambda$4($isPressed$delegate, true);
        return Unit.INSTANCE;
    }

    static final Unit VerticalDragHandle$lambda$8$lambda$7(MutableState $isPressed$delegate) {
        VerticalDragHandle$lambda$4($isPressed$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit VerticalDragHandle$lambda$10$lambda$9(DragHandleShapes $shapes, State $isDragged$delegate, MutableState $isPressed$delegate, GraphicsLayerScope $this$graphicsLayer) {
        Shape pressedShape;
        if (VerticalDragHandle$lambda$1($isDragged$delegate)) {
            pressedShape = $shapes.getDraggedShape();
        } else {
            pressedShape = VerticalDragHandle$lambda$3($isPressed$delegate) ? $shapes.getPressedShape() : $shapes.getShape();
        }
        $this$graphicsLayer.setShape(pressedShape);
        $this$graphicsLayer.setClip(true);
        return Unit.INSTANCE;
    }

    static final MeasureResult VerticalDragHandle$lambda$13$lambda$12(DragHandleSizes $sizes, State $isDragged$delegate, MutableState $isPressed$delegate, MeasureScope $this$layout, Measurable measurable, Constraints constraints) {
        long pressedSize;
        if (VerticalDragHandle$lambda$1($isDragged$delegate)) {
            pressedSize = $sizes.getDraggedSize();
        } else {
            pressedSize = VerticalDragHandle$lambda$3($isPressed$delegate) ? $sizes.getPressedSize() : $sizes.getSize();
        }
        long dragHandleSize = $this$layout.mo433toSizeXkaWNTQ(pressedSize);
        Constraints.Companion companion = Constraints.INSTANCE;
        int bits$iv$iv$iv = (int) (dragHandleSize >> 32);
        float $this$fastRoundToInt$iv = Float.intBitsToFloat(bits$iv$iv$iv);
        int $i$f$fastRoundToInt = Math.round($this$fastRoundToInt$iv);
        int bits$iv$iv$iv2 = (int) (4294967295L & dragHandleSize);
        float $this$fastRoundToInt$iv2 = Float.intBitsToFloat(bits$iv$iv$iv2);
        int $i$f$fastRoundToInt2 = Math.round($this$fastRoundToInt$iv2);
        final Placeable placeable = measurable.mo6783measureBRTryo0(companion.m8113fixedJhjzzOo($i$f$fastRoundToInt, $i$f$fastRoundToInt2));
        return MeasureScope.layout$default($this$layout, placeable.getWidth(), placeable.getHeight(), null, new Function1() { // from class: androidx.compose.material3.DragHandleKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DragHandleKt.VerticalDragHandle$lambda$13$lambda$12$lambda$11(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit VerticalDragHandle$lambda$13$lambda$12$lambda$11(Placeable $placeable, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.placeRelative$default($this$layout, $placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    static final Unit VerticalDragHandle$lambda$15$lambda$14(DragHandleColors $colors, State $isDragged$delegate, MutableState $isPressed$delegate, DrawScope $this$drawBehind) {
        long pressedColor;
        if (VerticalDragHandle$lambda$1($isDragged$delegate)) {
            pressedColor = $colors.getDraggedColor();
        } else {
            pressedColor = VerticalDragHandle$lambda$3($isPressed$delegate) ? $colors.getPressedColor() : $colors.getColor();
        }
        DrawScope.m5881drawRectnJ9OG0$default($this$drawBehind, pressedColor, 0L, 0L, 0.0f, null, null, 0, 126, null);
        return Unit.INSTANCE;
    }

    private static final Modifier pressable(Modifier $this$pressable, MutableInteractionSource interactionSource, final Function0<Unit> function0, final Function0<Unit> function02) {
        return SuspendingPointerInputFilterKt.pointerInput($this$pressable, interactionSource, new PointerInputEventHandler() { // from class: androidx.compose.material3.DragHandleKt.pressable.1

            /* JADX INFO: renamed from: androidx.compose.material3.DragHandleKt$pressable$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: DragHandle.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.material3.DragHandleKt$pressable$1$1", f = "DragHandle.kt", i = {0}, l = {341, 343}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
            static final class C00441 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function0<Unit> $onPressed;
                final /* synthetic */ Function0<Unit> $onReleasedOrCancelled;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00441(Function0<Unit> function0, Function0<Unit> function02, Continuation<? super C00441> continuation) {
                    super(2, continuation);
                    this.$onPressed = function0;
                    this.$onReleasedOrCancelled = function02;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00441 c00441 = new C00441(this.$onPressed, this.$onReleasedOrCancelled, continuation);
                    c00441.L$0 = obj;
                    return c00441;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00441) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:14:0x0050 A[RETURN] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r9) {
                    /*
                        r8 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r8.label
                        switch(r1) {
                            case 0: goto L1d;
                            case 1: goto L15;
                            case 2: goto L11;
                            default: goto L9;
                        }
                    L9:
                        java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r9.<init>(r0)
                        throw r9
                    L11:
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L51
                    L15:
                        java.lang.Object r1 = r8.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L3a
                    L1d:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.lang.Object r1 = r8.L$0
                        r2 = r1
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r2 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r2
                        androidx.compose.ui.input.pointer.PointerEventPass r4 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r5 = r8
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        r8.L$0 = r2
                        r1 = 1
                        r8.label = r1
                        r3 = 0
                        r6 = 1
                        r7 = 0
                        java.lang.Object r1 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r2, r3, r4, r5, r6, r7)
                        if (r1 != r0) goto L39
                        return r0
                    L39:
                        r1 = r2
                    L3a:
                        kotlin.jvm.functions.Function0<kotlin.Unit> r2 = r8.$onPressed
                        r2.invoke()
                        androidx.compose.ui.input.pointer.PointerEventPass r2 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r3 = r8
                        kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                        r4 = 0
                        r8.L$0 = r4
                        r4 = 2
                        r8.label = r4
                        java.lang.Object r1 = androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(r1, r2, r3)
                        if (r1 != r0) goto L51
                        return r0
                    L51:
                        kotlin.jvm.functions.Function0<kotlin.Unit> r0 = r8.$onReleasedOrCancelled
                        r0.invoke()
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.DragHandleKt.AnonymousClass1.C00441.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture($this$pointerInput, new C00441(function0, function02, null), continuation);
                return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
            }
        });
    }
}
