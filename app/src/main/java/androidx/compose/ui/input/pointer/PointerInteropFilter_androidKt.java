package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: PointerInteropFilter.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0000\u001a-\u0010\n\u001a\u00020\u0001*\u00020\u00012!\u0010\u000b\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\u0005¨\u0006\u0010"}, d2 = {"pointerInteropFilter", "Landroidx/compose/ui/Modifier;", "requestDisallowInterceptTouchEvent", "Landroidx/compose/ui/input/pointer/RequestDisallowInterceptTouchEvent;", "onTouchEvent", "Lkotlin/Function1;", "Landroid/view/MotionEvent;", "", "view", "Landroidx/compose/ui/viewinterop/AndroidViewHolder;", "motionEventSpy", "watcher", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "motionEvent", "", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PointerInteropFilter_androidKt {
    public static /* synthetic */ Modifier pointerInteropFilter$default(Modifier modifier, RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            requestDisallowInterceptTouchEvent = null;
        }
        return pointerInteropFilter(modifier, requestDisallowInterceptTouchEvent, function1);
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$2 */
    /* JADX INFO: compiled from: PointerInteropFilter.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass2 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Function1<MotionEvent, Boolean> $onTouchEvent;
        final /* synthetic */ RequestDisallowInterceptTouchEvent $requestDisallowInterceptTouchEvent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass2(Function1<? super MotionEvent, Boolean> function1, RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent) {
            super(3);
            function1 = function1;
            requestDisallowInterceptTouchEvent = requestDisallowInterceptTouchEvent;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
            $composer.startReplaceGroup(374375707);
            ComposerKt.sourceInformation($composer, "C78@3473L35:PointerInteropFilter.android.kt#a556rk");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(374375707, $changed, -1, "androidx.compose.ui.input.pointer.pointerInteropFilter.<anonymous> (PointerInteropFilter.android.kt:78)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1002197666, "CC(remember):PointerInteropFilter.android.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new PointerInteropFilter();
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            PointerInteropFilter filter = (PointerInteropFilter) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            filter.setOnTouchEvent(function1);
            filter.setRequestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return filter;
        }
    }

    public static final Modifier pointerInteropFilter(Modifier $this$pointerInteropFilter, final RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent, final Function1<? super MotionEvent, Boolean> function1) {
        return ComposedModifierKt.composed($this$pointerInteropFilter, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("pointerInteropFilter");
                inspectorInfo.getProperties().set("requestDisallowInterceptTouchEvent", requestDisallowInterceptTouchEvent);
                inspectorInfo.getProperties().set("onTouchEvent", function1);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.pointerInteropFilter.2
            final /* synthetic */ Function1<MotionEvent, Boolean> $onTouchEvent;
            final /* synthetic */ RequestDisallowInterceptTouchEvent $requestDisallowInterceptTouchEvent;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(final Function1<? super MotionEvent, Boolean> function12, final RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent2) {
                super(3);
                function1 = function12;
                requestDisallowInterceptTouchEvent = requestDisallowInterceptTouchEvent2;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                $composer.startReplaceGroup(374375707);
                ComposerKt.sourceInformation($composer, "C78@3473L35:PointerInteropFilter.android.kt#a556rk");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(374375707, $changed, -1, "androidx.compose.ui.input.pointer.pointerInteropFilter.<anonymous> (PointerInteropFilter.android.kt:78)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, -1002197666, "CC(remember):PointerInteropFilter.android.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = new PointerInteropFilter();
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                PointerInteropFilter filter = (PointerInteropFilter) it$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                filter.setOnTouchEvent(function1);
                filter.setRequestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return filter;
            }
        });
    }

    public static final Modifier pointerInteropFilter(Modifier $this$pointerInteropFilter, AndroidViewHolder view) {
        PointerInteropFilter filter = new PointerInteropFilter();
        filter.setOnTouchEvent(new Function1<MotionEvent, Boolean>() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.pointerInteropFilter.3
            AnonymousClass3() {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(MotionEvent motionEvent) {
                boolean zDispatchTouchEvent;
                int actionMasked = motionEvent.getActionMasked();
                AndroidViewHolder androidViewHolder = androidViewHolder;
                switch (actionMasked) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        zDispatchTouchEvent = androidViewHolder.dispatchTouchEvent(motionEvent);
                        break;
                    default:
                        zDispatchTouchEvent = androidViewHolder.dispatchGenericMotionEvent(motionEvent);
                        break;
                }
                return Boolean.valueOf(zDispatchTouchEvent);
            }
        });
        RequestDisallowInterceptTouchEvent requestDisallowInterceptTouchEvent = new RequestDisallowInterceptTouchEvent();
        filter.setRequestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
        view.setOnRequestDisallowInterceptTouchEvent$ui(requestDisallowInterceptTouchEvent);
        return $this$pointerInteropFilter.then(filter);
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$pointerInteropFilter$3 */
    /* JADX INFO: compiled from: PointerInteropFilter.android.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "motionEvent", "Landroid/view/MotionEvent;", "invoke", "(Landroid/view/MotionEvent;)Ljava/lang/Boolean;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass3 extends Lambda implements Function1<MotionEvent, Boolean> {
        AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(MotionEvent motionEvent) {
            boolean zDispatchTouchEvent;
            int actionMasked = motionEvent.getActionMasked();
            AndroidViewHolder androidViewHolder = androidViewHolder;
            switch (actionMasked) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    zDispatchTouchEvent = androidViewHolder.dispatchTouchEvent(motionEvent);
                    break;
                default:
                    zDispatchTouchEvent = androidViewHolder.dispatchGenericMotionEvent(motionEvent);
                    break;
            }
            return Boolean.valueOf(zDispatchTouchEvent);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1 */
    /* JADX INFO: compiled from: PointerInteropFilter.android.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "invoke", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 implements PointerInputEventHandler {
        final /* synthetic */ Function1<MotionEvent, Unit> $watcher;

        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function1<? super MotionEvent, Unit> function1) {
            function1 = function1;
        }

        /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1 */
        /* JADX INFO: compiled from: PointerInteropFilter.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1", f = "PointerInteropFilter.android.kt", i = {0}, l = {389}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"}, v = 1)
        static final class C00661 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<MotionEvent, Unit> $watcher;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00661(Function1<? super MotionEvent, Unit> function1, Continuation<? super C00661> continuation) {
                super(2, continuation);
                this.$watcher = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00661 c00661 = new C00661(this.$watcher, continuation);
                c00661.L$0 = obj;
                return c00661;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00661) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:26:0x0036 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:27:0x0037  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x0045  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0037 -> B:28:0x003d). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                /*
                    r7 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r7.label
                    switch(r1) {
                        case 0: goto L1d;
                        case 1: goto L11;
                        default: goto L9;
                    }
                L9:
                    java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r8.<init>(r0)
                    throw r8
                L11:
                    java.lang.Object r1 = r7.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r8)
                    r3 = r7
                    r2 = r1
                    r1 = r0
                    r0 = r8
                    goto L3d
                L1d:
                    kotlin.ResultKt.throwOnFailure(r8)
                    java.lang.Object r1 = r7.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    r2 = r7
                L25:
                    androidx.compose.ui.input.pointer.PointerEventPass r3 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r4 = r2
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r2.L$0 = r1
                    r5 = 1
                    r2.label = r5
                    java.lang.Object r3 = r1.awaitPointerEvent(r3, r4)
                    if (r3 != r0) goto L37
                    return r0
                L37:
                    r6 = r0
                    r0 = r8
                    r8 = r3
                    r3 = r2
                    r2 = r1
                    r1 = r6
                L3d:
                    androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
                    android.view.MotionEvent r8 = r8.getMotionEvent()
                    if (r8 == 0) goto L4a
                    kotlin.jvm.functions.Function1<android.view.MotionEvent, kotlin.Unit> r4 = r3.$watcher
                    r4.invoke(r8)
                L4a:
                    r8 = r0
                    r0 = r1
                    r1 = r2
                    r2 = r3
                    goto L25
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.AnonymousClass1.C00661.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
        public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
            $this$pointerInput.setInterceptOutOfBoundsChildEvents(true);
            Object objAwaitPointerEventScope = $this$pointerInput.awaitPointerEventScope(new C00661(function1, null), continuation);
            return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
        }
    }

    public static final Modifier motionEventSpy(Modifier $this$motionEventSpy, Function1<? super MotionEvent, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput($this$motionEventSpy, function1, new PointerInputEventHandler() { // from class: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.motionEventSpy.1
            final /* synthetic */ Function1<MotionEvent, Unit> $watcher;

            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(Function1<? super MotionEvent, Unit> function12) {
                function1 = function12;
            }

            /* JADX INFO: renamed from: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1 */
            /* JADX INFO: compiled from: PointerInteropFilter.android.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt$motionEventSpy$1$1", f = "PointerInteropFilter.android.kt", i = {0}, l = {389}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"}, v = 1)
            static final class C00661 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function1<MotionEvent, Unit> $watcher;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                C00661(Function1<? super MotionEvent, Unit> function1, Continuation<? super C00661> continuation) {
                    super(2, continuation);
                    this.$watcher = function1;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00661 c00661 = new C00661(this.$watcher, continuation);
                    c00661.L$0 = obj;
                    return c00661;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00661) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object v) {
                    /*
                        this = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r7.label
                        switch(r1) {
                            case 0: goto L1d;
                            case 1: goto L11;
                            default: goto L9;
                        }
                    L9:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r0)
                        throw r8
                    L11:
                        java.lang.Object r1 = r7.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r8)
                        r3 = r7
                        r2 = r1
                        r1 = r0
                        r0 = r8
                        goto L3d
                    L1d:
                        kotlin.ResultKt.throwOnFailure(r8)
                        java.lang.Object r1 = r7.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        r2 = r7
                    L25:
                        androidx.compose.ui.input.pointer.PointerEventPass r3 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r4 = r2
                        kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                        r2.L$0 = r1
                        r5 = 1
                        r2.label = r5
                        java.lang.Object r3 = r1.awaitPointerEvent(r3, r4)
                        if (r3 != r0) goto L37
                        return r0
                    L37:
                        r6 = r0
                        r0 = r8
                        r8 = r3
                        r3 = r2
                        r2 = r1
                        r1 = r6
                    L3d:
                        androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
                        android.view.MotionEvent r8 = r8.getMotionEvent()
                        if (r8 == 0) goto L4a
                        kotlin.jvm.functions.Function1<android.view.MotionEvent, kotlin.Unit> r4 = r3.$watcher
                        r4.invoke(r8)
                    L4a:
                        r8 = r0
                        r0 = r1
                        r1 = r2
                        r2 = r3
                        goto L25
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.PointerInteropFilter_androidKt.AnonymousClass1.C00661.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
            public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
                $this$pointerInput.setInterceptOutOfBoundsChildEvents(true);
                Object objAwaitPointerEventScope = $this$pointerInput.awaitPointerEventScope(new C00661(function1, null), continuation);
                return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
            }
        });
    }
}
