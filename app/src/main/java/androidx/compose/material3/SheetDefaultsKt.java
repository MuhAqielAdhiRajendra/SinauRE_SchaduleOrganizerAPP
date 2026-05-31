package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.Velocity;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SheetDefaults.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0011\u0010\u0003\u001a\r\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0002\u0010\u0006\u001a;\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2!\u0010\r\u001a\u001d\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00010\u000eH\u0000\u001aW\u0010\u0013\u001a\u00020\n2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0014\b\u0002\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00150\u000e2\b\b\u0002\u0010\u0018\u001a\u00020\u00172\b\b\u0002\u0010\u0019\u001a\u00020\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0001¢\u0006\u0004\b\u001d\u0010\u001e\"\u0010\u0010\u001f\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 \"\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"DragHandleWithTooltip", "", "Landroidx/compose/foundation/layout/ColumnScope;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/layout/ColumnScope;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "sheetState", "Landroidx/compose/material3/SheetState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "onFling", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "velocity", "rememberSheetState", "skipPartiallyExpanded", "", "confirmValueChange", "Landroidx/compose/material3/SheetValue;", "initialValue", "skipHiddenState", "positionalThreshold", "Landroidx/compose/ui/unit/Dp;", "velocityThreshold", "rememberSheetState-AGcomas", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/material3/SheetValue;ZFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "DragHandleVerticalPadding", "F", "BottomSheetAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SheetDefaultsKt {
    private static final float DragHandleVerticalPadding = Dp.m8150constructorimpl(22);
    private static final AnimationSpec<Float> BottomSheetAnimationSpec = AnimationSpecKt.tween$default(300, 0, EasingKt.getFastOutSlowInEasing(), 2, null);

    static final Unit DragHandleWithTooltip$lambda$1(ColumnScope columnScope, Function2 function2, int i, Composer composer, int i2) {
        DragHandleWithTooltip(columnScope, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void DragHandleWithTooltip(final ColumnScope $this$DragHandleWithTooltip, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1033612924);
        ComposerKt.sourceInformation($composer2, "C(DragHandleWithTooltip)N(content)433@17991L51,435@18147L349:SheetDefaults.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed($this$DragHandleWithTooltip) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1033612924, $dirty2, -1, "androidx.compose.material3.DragHandleWithTooltip (SheetDefaults.kt:432)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String dragHandleDescription = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_bottom_sheet_drag_handle_description), $composer2, 0);
            Modifier modifier$iv = $this$DragHandleWithTooltip.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally());
            ComposerKt.sourceInformationMarkerStart($composer2, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i2 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 772858620, "C438@18272L60,439@18356L48,440@18426L22,436@18197L293:SheetDefaults.kt#uh7d8r");
            TooltipKt.TooltipBox(TooltipDefaults.INSTANCE.m3336rememberTooltipPositionProviderHu5FAss(TooltipAnchorPosition.INSTANCE.m3323getAbovelOKsHw4(), 0.0f, $composer2, 390, 2), ComposableLambdaKt.rememberComposableLambda(2059851063, true, new Function3<TooltipScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.SheetDefaultsKt$DragHandleWithTooltip$1$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(TooltipScope tooltipScope, Composer composer, Integer num) {
                    invoke(tooltipScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(TooltipScope $this$TooltipBox, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C439@18371L31,439@18358L44:SheetDefaults.kt#uh7d8r");
                    int $dirty3 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty3 |= ($changed2 & 8) == 0 ? $composer3.changed($this$TooltipBox) : $composer3.changedInstance($this$TooltipBox) ? 4 : 2;
                    }
                    int $dirty4 = $dirty3;
                    if (!$composer3.shouldExecute(($dirty4 & 19) != 18, $dirty4 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2059851063, $dirty4, -1, "androidx.compose.material3.DragHandleWithTooltip.<anonymous>.<anonymous> (SheetDefaults.kt:439)");
                    }
                    final String str = dragHandleDescription;
                    TooltipKt.m3339PlainTooltipgv3ox5I($this$TooltipBox, null, null, 0.0f, null, 0L, 0L, 0.0f, 0.0f, ComposableLambdaKt.rememberComposableLambda(-999924215, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SheetDefaultsKt$DragHandleWithTooltip$1$1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            ComposerKt.sourceInformation($composer4, "C439@18373L27:SheetDefaults.kt#uh7d8r");
                            if (!$composer4.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer4.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-999924215, $changed3, -1, "androidx.compose.material3.DragHandleWithTooltip.<anonymous>.<anonymous>.<anonymous> (SheetDefaults.kt:439)");
                            }
                            TextKt.m3157TextNvy7gAk(str, null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer4, 0, 0, 262142);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54), $composer3, ($dirty4 & 14) | 805306368, 255);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), TooltipKt.rememberTooltipState(false, false, null, $composer2, 0, 7), null, null, false, false, false, function2, $composer2, (($dirty2 << 21) & 234881024) | 48, 248);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.SheetDefaultsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SheetDefaultsKt.DragHandleWithTooltip$lambda$1($this$DragHandleWithTooltip, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SheetDefaultsKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1 */
    /* JADX INFO: compiled from: SheetDefaults.kt */
    @Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bJ'\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u000f\u0010\u0010J \u0010\u0011\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u000eH\u0096@¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0003*\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u000eH\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0013\u0010\u0017\u001a\u00020\u0015*\u00020\u0003H\u0003¢\u0006\u0004\b\u001a\u0010\u0019¨\u0006\u001b"}, d2 = {"androidx/compose/material3/SheetDefaultsKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "onPreScroll", "Landroidx/compose/ui/geometry/Offset;", "available", "source", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "onPreScroll-OzD1aCk", "(JI)J", "onPostScroll", "consumed", "onPostScroll-DzOQY0M", "(JJI)J", "onPreFling", "Landroidx/compose/ui/unit/Velocity;", "onPreFling-QWom1Mo", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onPostFling", "onPostFling-RZ2iAVY", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toOffset", "", "(F)J", "toFloat", "velocityToFloat", "(J)F", "offsetToFloat", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements NestedScrollConnection {
        final /* synthetic */ Function1<Float, Unit> $onFling;
        final /* synthetic */ Orientation $orientation;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super Float, Unit> function1, Orientation $orientation) {
            function1 = function1;
            $orientation = $orientation;
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
        public long mo1148onPreScrollOzD1aCk(long available, int source) {
            float delta = offsetToFloat(available);
            if (delta < 0.0f && NestedScrollSource.m6507equalsimpl0(source, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI())) {
                return toOffset($sheetState.getAnchoredDraggableState$material3().dispatchRawDelta(delta));
            }
            return Offset.INSTANCE.m5084getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
        public long mo601onPostScrollDzOQY0M(long consumed, long available, int source) {
            if (NestedScrollSource.m6507equalsimpl0(source, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI())) {
                return toOffset($sheetState.getAnchoredDraggableState$material3().dispatchRawDelta(offsetToFloat(available)));
            }
            return Offset.INSTANCE.m5084getZeroF1C5BW0();
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPreFling-QWom1Mo */
        public Object mo1147onPreFlingQWom1Mo(long available, Continuation<? super Velocity> continuation) {
            long jM8399getZero9UxMQ8M;
            float toFling = velocityToFloat(available);
            float currentOffset = $sheetState.requireOffset();
            float minAnchor = $sheetState.getAnchoredDraggableState$material3().getAnchors().minAnchor();
            if (toFling < 0.0f && currentOffset > minAnchor) {
                function1.invoke(Boxing.boxFloat(toFling));
                jM8399getZero9UxMQ8M = available;
            } else {
                jM8399getZero9UxMQ8M = Velocity.INSTANCE.m8399getZero9UxMQ8M();
            }
            return Velocity.m8379boximpl(jM8399getZero9UxMQ8M);
        }

        @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
        /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
        public Object mo600onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
            function1.invoke(Boxing.boxFloat(velocityToFloat(available)));
            return Velocity.m8379boximpl(available);
        }

        private final long toOffset(float $this$toOffset) {
            float x$iv = $orientation == Orientation.Horizontal ? $this$toOffset : 0.0f;
            float y$iv = $orientation == Orientation.Vertical ? $this$toOffset : 0.0f;
            long v1$iv$iv = Float.floatToRawIntBits(x$iv);
            long v2$iv$iv = Float.floatToRawIntBits(y$iv);
            return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
        }

        private final float velocityToFloat(long $this$toFloat) {
            return $orientation == Orientation.Horizontal ? Velocity.m8388getXimpl($this$toFloat) : Velocity.m8389getYimpl($this$toFloat);
        }

        private final float offsetToFloat(long $this$toFloat) {
            if ($orientation == Orientation.Horizontal) {
                int bits$iv$iv$iv = (int) ($this$toFloat >> 32);
                return Float.intBitsToFloat(bits$iv$iv$iv);
            }
            int bits$iv$iv$iv2 = (int) (4294967295L & $this$toFloat);
            return Float.intBitsToFloat(bits$iv$iv$iv2);
        }
    }

    public static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(SheetState sheetState, Orientation orientation, Function1<? super Float, Unit> function1) {
        return new NestedScrollConnection() { // from class: androidx.compose.material3.SheetDefaultsKt.ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection.1
            final /* synthetic */ Function1<Float, Unit> $onFling;
            final /* synthetic */ Orientation $orientation;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Function1<? super Float, Unit> function12, Orientation orientation2) {
                function1 = function12;
                $orientation = orientation2;
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPreScroll-OzD1aCk */
            public long mo1148onPreScrollOzD1aCk(long available, int source) {
                float delta = offsetToFloat(available);
                if (delta < 0.0f && NestedScrollSource.m6507equalsimpl0(source, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI())) {
                    return toOffset($sheetState.getAnchoredDraggableState$material3().dispatchRawDelta(delta));
                }
                return Offset.INSTANCE.m5084getZeroF1C5BW0();
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPostScroll-DzOQY0M */
            public long mo601onPostScrollDzOQY0M(long consumed, long available, int source) {
                if (NestedScrollSource.m6507equalsimpl0(source, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI())) {
                    return toOffset($sheetState.getAnchoredDraggableState$material3().dispatchRawDelta(offsetToFloat(available)));
                }
                return Offset.INSTANCE.m5084getZeroF1C5BW0();
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPreFling-QWom1Mo */
            public Object mo1147onPreFlingQWom1Mo(long available, Continuation<? super Velocity> continuation) {
                long jM8399getZero9UxMQ8M;
                float toFling = velocityToFloat(available);
                float currentOffset = $sheetState.requireOffset();
                float minAnchor = $sheetState.getAnchoredDraggableState$material3().getAnchors().minAnchor();
                if (toFling < 0.0f && currentOffset > minAnchor) {
                    function1.invoke(Boxing.boxFloat(toFling));
                    jM8399getZero9UxMQ8M = available;
                } else {
                    jM8399getZero9UxMQ8M = Velocity.INSTANCE.m8399getZero9UxMQ8M();
                }
                return Velocity.m8379boximpl(jM8399getZero9UxMQ8M);
            }

            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* JADX INFO: renamed from: onPostFling-RZ2iAVY */
            public Object mo600onPostFlingRZ2iAVY(long consumed, long available, Continuation<? super Velocity> continuation) {
                function1.invoke(Boxing.boxFloat(velocityToFloat(available)));
                return Velocity.m8379boximpl(available);
            }

            private final long toOffset(float $this$toOffset) {
                float x$iv = $orientation == Orientation.Horizontal ? $this$toOffset : 0.0f;
                float y$iv = $orientation == Orientation.Vertical ? $this$toOffset : 0.0f;
                long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                return Offset.m5060constructorimpl((v1$iv$iv << 32) | (4294967295L & v2$iv$iv));
            }

            private final float velocityToFloat(long $this$toFloat) {
                return $orientation == Orientation.Horizontal ? Velocity.m8388getXimpl($this$toFloat) : Velocity.m8389getYimpl($this$toFloat);
            }

            private final float offsetToFloat(long $this$toFloat) {
                if ($orientation == Orientation.Horizontal) {
                    int bits$iv$iv$iv = (int) ($this$toFloat >> 32);
                    return Float.intBitsToFloat(bits$iv$iv$iv);
                }
                int bits$iv$iv$iv2 = (int) (4294967295L & $this$toFloat);
                return Float.intBitsToFloat(bits$iv$iv$iv2);
            }
        };
    }

    static final boolean rememberSheetState_AGcomas$lambda$3$lambda$2(SheetValue it) {
        return true;
    }

    /* JADX INFO: renamed from: rememberSheetState-AGcomas */
    public static final SheetState m2923rememberSheetStateAGcomas(boolean skipPartiallyExpanded, Function1<? super SheetValue, Boolean> function1, SheetValue initialValue, boolean skipHiddenState, float positionalThreshold, float velocityThreshold, Composer $composer, int $changed, int i) {
        final Function1<? super SheetValue, Boolean> function12;
        ComposerKt.sourceInformationMarkerStart($composer, -20307384, "C(rememberSheetState)N(skipPartiallyExpanded,confirmValueChange,initialValue,skipHiddenState,positionalThreshold:c#ui.unit.Dp,velocityThreshold:c#ui.unit.Dp)509@20940L8,515@21212L7,516@21254L48,517@21335L46,530@21855L231,518@21393L693:SheetDefaults.kt#uh7d8r");
        final boolean skipPartiallyExpanded2 = (i & 1) != 0 ? false : skipPartiallyExpanded;
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, 1959445744, "CC(remember):SheetDefaults.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.SheetDefaultsKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(SheetDefaultsKt.rememberSheetState_AGcomas$lambda$3$lambda$2((SheetValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function12 = (Function1) it$iv;
        } else {
            function12 = function1;
        }
        final SheetValue initialValue2 = (i & 4) != 0 ? SheetValue.Hidden : initialValue;
        final boolean skipHiddenState2 = (i & 8) != 0 ? false : skipHiddenState;
        final float positionalThreshold2 = (i & 16) != 0 ? BottomSheetDefaults.INSTANCE.m2193getPositionalThresholdD9Ej5fM$material3() : positionalThreshold;
        final float velocityThreshold2 = (i & 32) != 0 ? BottomSheetDefaults.INSTANCE.m2196getVelocityThresholdD9Ej5fM$material3() : velocityThreshold;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-20307384, $changed, -1, "androidx.compose.material3.rememberSheetState (SheetDefaults.kt:514)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) objConsume;
        ComposerKt.sourceInformationMarkerStart($composer, 1959455832, "CC(remember):SheetDefaults.kt#9igjgp");
        boolean invalid$iv = $composer.changed(density) | ((((57344 & $changed) ^ 24576) > 16384 && $composer.changed(positionalThreshold2)) || ($changed & 24576) == 16384);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new Function0() { // from class: androidx.compose.material3.SheetDefaultsKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(density.mo432toPx0680j_4(positionalThreshold2));
                }
            };
            $composer.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        final Function0<Float> function0 = (Function0) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 1959458422, "CC(remember):SheetDefaults.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(density) | ((((458752 & $changed) ^ ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) > 131072 && $composer.changed(velocityThreshold2)) || ($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 131072);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            Object value$iv3 = new Function0() { // from class: androidx.compose.material3.SheetDefaultsKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return Float.valueOf(density.mo432toPx0680j_4(velocityThreshold2));
                }
            };
            $composer.updateRememberedValue(value$iv3);
            it$iv3 = value$iv3;
        }
        final Function0<Float> function02 = (Function0) it$iv3;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object[] objArr = {Boolean.valueOf(skipPartiallyExpanded2), function12, Boolean.valueOf(skipHiddenState2)};
        Saver<SheetState, SheetValue> Saver = SheetState.INSTANCE.Saver(skipPartiallyExpanded2, function0, function02, function12, skipHiddenState2);
        ComposerKt.sourceInformationMarkerStart($composer, 1959475247, "CC(remember):SheetDefaults.kt#9igjgp");
        boolean invalid$iv3 = (((($changed & 14) ^ 6) > 4 && $composer.changed(skipPartiallyExpanded2)) || ($changed & 6) == 4) | $composer.changed(function0) | $composer.changed(function02) | (((($changed & 896) ^ 384) > 256 && $composer.changed(initialValue2.ordinal())) || ($changed & 384) == 256) | (((($changed & 112) ^ 48) > 32 && $composer.changed(function12)) || ($changed & 48) == 32) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(skipHiddenState2)) || ($changed & 3072) == 2048);
        Object value$iv4 = $composer.rememberedValue();
        if (invalid$iv3 || value$iv4 == Composer.INSTANCE.getEmpty()) {
            value$iv4 = new Function0() { // from class: androidx.compose.material3.SheetDefaultsKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return SheetDefaultsKt.rememberSheetState_AGcomas$lambda$11$lambda$10(skipPartiallyExpanded2, function0, function02, initialValue2, function12, skipHiddenState2);
                }
            };
            $composer.updateRememberedValue(value$iv4);
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SheetState sheetState = (SheetState) RememberSaveableKt.m4704rememberSaveable(objArr, (Saver) Saver, (Function0) value$iv4, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return sheetState;
    }

    static final SheetState rememberSheetState_AGcomas$lambda$11$lambda$10(boolean $skipPartiallyExpanded, Function0 $positionalThresholdToPx, Function0 $velocityThresholdToPx, SheetValue $initialValue, Function1 $confirmValueChange, boolean $skipHiddenState) {
        return new SheetState($skipPartiallyExpanded, $positionalThresholdToPx, $velocityThresholdToPx, $initialValue, $confirmValueChange, $skipHiddenState);
    }
}
