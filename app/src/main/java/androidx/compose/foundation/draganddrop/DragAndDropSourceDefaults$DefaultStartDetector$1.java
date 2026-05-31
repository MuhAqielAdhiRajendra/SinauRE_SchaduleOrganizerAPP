package androidx.compose.foundation.draganddrop;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.PressGestureScopeImpl;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.spatial.RectListKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: AndroidDragAndDropSource.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/draganddrop/DragAndDropStartDetectorScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1", f = "AndroidDragAndDropSource.android.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class DragAndDropSourceDefaults$DefaultStartDetector$1 extends SuspendLambda implements Function2<DragAndDropStartDetectorScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    DragAndDropSourceDefaults$DefaultStartDetector$1(Continuation<? super DragAndDropSourceDefaults$DefaultStartDetector$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DragAndDropSourceDefaults$DefaultStartDetector$1 dragAndDropSourceDefaults$DefaultStartDetector$1 = new DragAndDropSourceDefaults$DefaultStartDetector$1(continuation);
        dragAndDropSourceDefaults$DefaultStartDetector$1.L$0 = obj;
        return dragAndDropSourceDefaults$DefaultStartDetector$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(DragAndDropStartDetectorScope dragAndDropStartDetectorScope, Continuation<? super Unit> continuation) {
        return ((DragAndDropSourceDefaults$DefaultStartDetector$1) create(dragAndDropStartDetectorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                DragAndDropStartDetectorScope dragAndDropStartDetectorScope = (DragAndDropStartDetectorScope) this.L$0;
                PressGestureScopeImpl pressScope = new PressGestureScopeImpl(dragAndDropStartDetectorScope);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(dragAndDropStartDetectorScope, pressScope, null), this) == coroutine_suspended) {
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

    /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: AndroidDragAndDropSource.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1", f = "AndroidDragAndDropSource.android.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PressGestureScopeImpl $pressScope;
        final /* synthetic */ DragAndDropStartDetectorScope $this;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DragAndDropStartDetectorScope dragAndDropStartDetectorScope, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$this = dragAndDropStartDetectorScope;
            this.$pressScope = pressGestureScopeImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$this, this.$pressScope, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: AndroidDragAndDropSource.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1", f = "AndroidDragAndDropSource.android.kt", i = {0}, l = {48, 50, RectListKt.BitOffsetForGesturable}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"}, v = 1)
        static final class C00091 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            final /* synthetic */ DragAndDropStartDetectorScope $this;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00091(CoroutineScope coroutineScope, PressGestureScopeImpl pressGestureScopeImpl, DragAndDropStartDetectorScope dragAndDropStartDetectorScope, Continuation<? super C00091> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.$pressScope = pressGestureScopeImpl;
                this.$this = dragAndDropStartDetectorScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00091 c00091 = new C00091(this.$$this$coroutineScope, this.$pressScope, this.$this, continuation);
                c00091.L$0 = obj;
                return c00091;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00091) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:15:0x0059  */
            /* JADX WARN: Removed duplicated region for block: B:20:0x0088  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r13) {
                /*
                    r12 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r12.label
                    switch(r1) {
                        case 0: goto L26;
                        case 1: goto L1c;
                        case 2: goto L17;
                        case 3: goto L12;
                        default: goto L9;
                    }
                L9:
                    java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r13.<init>(r0)
                    throw r13
                L12:
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto Lb0
                L17:
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto L87
                L1c:
                    java.lang.Object r1 = r12.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r13)
                    r2 = r1
                    r1 = r13
                    goto L43
                L26:
                    kotlin.ResultKt.throwOnFailure(r13)
                    java.lang.Object r1 = r12.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    androidx.compose.ui.input.pointer.PointerEventPass r2 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r3 = r12
                    kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                    r12.L$0 = r1
                    r4 = 1
                    r12.label = r4
                    r4 = 0
                    java.lang.Object r2 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(r1, r4, r2, r3)
                    if (r2 != r0) goto L3f
                    return r0
                L3f:
                    r11 = r1
                    r1 = r13
                    r13 = r2
                    r2 = r11
                L43:
                    r3 = r13
                    androidx.compose.ui.input.pointer.PointerInputChange r3 = (androidx.compose.ui.input.pointer.PointerInputChange) r3
                    int r13 = r3.getType()
                    androidx.compose.ui.input.pointer.PointerType$Companion r4 = androidx.compose.ui.input.pointer.PointerType.INSTANCE
                    int r4 = r4.m6728getMouseT8wyACA()
                    boolean r13 = androidx.compose.ui.input.pointer.PointerType.m6723equalsimpl0(r13, r4)
                    r4 = 0
                    if (r13 == 0) goto L88
                L59:
                    r13 = r4
                    androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda0 r4 = new androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda0
                    r4.<init>()
                    androidx.compose.foundation.draganddrop.DragAndDropStartDetectorScope r5 = r12.$this
                    androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda1 r6 = new androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda1
                    r6.<init>()
                    androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda2 r7 = new androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda2
                    r7.<init>()
                    androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda3 r8 = new androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda3
                    r8.<init>()
                    androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda4 r9 = new androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda4
                    r9.<init>()
                    r10 = r12
                    kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
                    r12.L$0 = r13
                    r13 = 2
                    r12.label = r13
                    r5 = 0
                    java.lang.Object r13 = androidx.compose.foundation.gestures.DragGestureDetectorKt.processDragGesture(r2, r3, r4, r5, r6, r7, r8, r9, r10)
                    if (r13 != r0) goto L86
                    return r0
                L86:
                    r13 = r1
                L87:
                    goto Lb1
                L88:
                    r13 = r4
                    kotlinx.coroutines.CoroutineScope r3 = r12.$$this$coroutineScope
                    androidx.compose.foundation.gestures.PressGestureScopeImpl r4 = r12.$pressScope
                    androidx.compose.foundation.draganddrop.DragAndDropStartDetectorScope r5 = r12.$this
                    androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda5 r6 = new androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$$ExternalSyntheticLambda5
                    r6.<init>()
                    androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$7 r5 = new androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$7
                    r5.<init>(r13)
                    r7 = r5
                    kotlin.jvm.functions.Function3 r7 = (kotlin.jvm.functions.Function3) r7
                    r9 = r12
                    kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
                    r12.L$0 = r13
                    r13 = 3
                    r12.label = r13
                    r5 = 0
                    r8 = 0
                    java.lang.Object r13 = androidx.compose.foundation.gestures.TapGestureDetectorKt.processTapGesture(r2, r3, r4, r5, r6, r7, r8, r9)
                    if (r13 != r0) goto Laf
                    return r0
                Laf:
                    r13 = r1
                Lb0:
                Lb1:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1.AnonymousClass1.C00091.invokeSuspend(java.lang.Object):java.lang.Object");
            }

            static final boolean invokeSuspend$lambda$0() {
                return true;
            }

            static final Unit invokeSuspend$lambda$1(DragAndDropStartDetectorScope $this, PointerInputChange down, PointerInputChange pointerInputChange, Offset offset) {
                $this.mo424requestDragAndDropTransferk4lQ0M(down.getPosition());
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$7, reason: invalid class name */
            /* JADX INFO: compiled from: AndroidDragAndDropSource.android.kt */
            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "it", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 1, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.draganddrop.DragAndDropSourceDefaults$DefaultStartDetector$1$1$1$7", f = "AndroidDragAndDropSource.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            static final class AnonymousClass7 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
                int label;

                AnonymousClass7(Continuation<? super AnonymousClass7> continuation) {
                    super(3, continuation);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Object invoke(PressGestureScope pressGestureScope, Offset offset, Continuation<? super Unit> continuation) {
                    return m420invoked4ec7I(pressGestureScope, offset.m5078unboximpl(), continuation);
                }

                /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
                public final Object m420invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                    return new AnonymousClass7(continuation).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch (this.label) {
                        case 0:
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                }
            }

            static final Unit invokeSuspend$lambda$5(DragAndDropStartDetectorScope $this, Offset offset) {
                $this.mo424requestDragAndDropTransferk4lQ0M(offset.m5078unboximpl());
                return Unit.INSTANCE;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$coroutineScope = (CoroutineScope) this.L$0;
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture(this.$this, new C00091($this$coroutineScope, this.$pressScope, this.$this, null), this) == coroutine_suspended) {
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
}
