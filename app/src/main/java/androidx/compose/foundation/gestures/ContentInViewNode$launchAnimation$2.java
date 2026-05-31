package androidx.compose.foundation.gestures;

import androidx.compose.foundation.MutatePriority;
import androidx.compose.foundation.gestures.ContentInViewNode;
import androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.unit.IntOffset;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobKt__JobKt;

/* JADX INFO: compiled from: ContentInViewNode.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2", f = "ContentInViewNode.kt", i = {}, l = {212}, m = "invokeSuspend", n = {}, s = {}, v = 1)
final class ContentInViewNode$launchAnimation$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ UpdatableAnimationState $animationState;
    final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
    final /* synthetic */ long $viewportAdjustmentForReverseScroll;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ ContentInViewNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ContentInViewNode$launchAnimation$2(ContentInViewNode contentInViewNode, UpdatableAnimationState updatableAnimationState, BringIntoViewSpec bringIntoViewSpec, long j, Continuation<? super ContentInViewNode$launchAnimation$2> continuation) {
        super(2, continuation);
        this.this$0 = contentInViewNode;
        this.$animationState = updatableAnimationState;
        this.$bringIntoViewSpec = bringIntoViewSpec;
        this.$viewportAdjustmentForReverseScroll = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ContentInViewNode$launchAnimation$2 contentInViewNode$launchAnimation$2 = new ContentInViewNode$launchAnimation$2(this.this$0, this.$animationState, this.$bringIntoViewSpec, this.$viewportAdjustmentForReverseScroll, continuation);
        contentInViewNode$launchAnimation$2.L$0 = obj;
        return contentInViewNode$launchAnimation$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ContentInViewNode$launchAnimation$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r4v0 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        CancellationException cancellationException;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
            switch (r1) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    CoroutineScope $this$launch = (CoroutineScope) this.L$0;
                    Job animationJob = JobKt.getJob($this$launch.getCoroutineContext());
                    try {
                        this.this$0.isAnimationRunning = true;
                        this.label = 1;
                        if (this.this$0.scrollingLogic.scroll(MutatePriority.Default, new AnonymousClass1(this.$animationState, this.this$0, this.$bringIntoViewSpec, this.$viewportAdjustmentForReverseScroll, animationJob, null), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        cancellationException = null;
                        this.this$0.bringIntoViewRequests.resumeAndRemoveAll();
                        this.this$0.isAnimationRunning = false;
                        this.this$0.bringIntoViewRequests.cancelAndRemoveAll(cancellationException);
                        this.this$0.trackingFocusedChild = false;
                        return Unit.INSTANCE;
                    } catch (CancellationException e) {
                        e = e;
                        throw e;
                    } catch (Throwable th) {
                        e = th;
                        r1 = 0;
                        this.this$0.isAnimationRunning = false;
                        this.this$0.bringIntoViewRequests.cancelAndRemoveAll((Throwable) r1);
                        this.this$0.trackingFocusedChild = false;
                        throw e;
                    }
                case 1:
                    cancellationException = null;
                    try {
                        ResultKt.throwOnFailure($result);
                        this.this$0.bringIntoViewRequests.resumeAndRemoveAll();
                        this.this$0.isAnimationRunning = false;
                        this.this$0.bringIntoViewRequests.cancelAndRemoveAll(cancellationException);
                        this.this$0.trackingFocusedChild = false;
                        return Unit.INSTANCE;
                    } catch (CancellationException e2) {
                        e = e2;
                        throw e;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th2) {
            e = th2;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1, reason: invalid class name */
    /* JADX INFO: compiled from: ContentInViewNode.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1", f = "ContentInViewNode.kt", i = {}, l = {219}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Job $animationJob;
        final /* synthetic */ UpdatableAnimationState $animationState;
        final /* synthetic */ BringIntoViewSpec $bringIntoViewSpec;
        final /* synthetic */ long $viewportAdjustmentForReverseScroll;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ ContentInViewNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(UpdatableAnimationState updatableAnimationState, ContentInViewNode contentInViewNode, BringIntoViewSpec bringIntoViewSpec, long j, Job job, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$animationState = updatableAnimationState;
            this.this$0 = contentInViewNode;
            this.$bringIntoViewSpec = bringIntoViewSpec;
            this.$viewportAdjustmentForReverseScroll = j;
            this.$animationJob = job;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$animationState, this.this$0, this.$bringIntoViewSpec, this.$viewportAdjustmentForReverseScroll, this.$animationJob, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final NestedScrollScope $this$scroll = (NestedScrollScope) this.L$0;
                    this.$animationState.setValue(this.this$0.m459calculateScrollDeltaI_oMVgE(this.$bringIntoViewSpec, this.$viewportAdjustmentForReverseScroll));
                    UpdatableAnimationState updatableAnimationState = this.$animationState;
                    final ContentInViewNode contentInViewNode = this.this$0;
                    final UpdatableAnimationState updatableAnimationState2 = this.$animationState;
                    final Job job = this.$animationJob;
                    Function1<? super Float, Unit> function1 = new Function1() { // from class: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return ContentInViewNode$launchAnimation$2.AnonymousClass1.invokeSuspend$lambda$0(contentInViewNode, updatableAnimationState2, job, $this$scroll, ((Float) obj).floatValue());
                        }
                    };
                    final ContentInViewNode contentInViewNode2 = this.this$0;
                    final UpdatableAnimationState updatableAnimationState3 = this.$animationState;
                    final BringIntoViewSpec bringIntoViewSpec = this.$bringIntoViewSpec;
                    this.label = 1;
                    if (updatableAnimationState.animateToZero(function1, new Function0() { // from class: androidx.compose.foundation.gestures.ContentInViewNode$launchAnimation$2$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ContentInViewNode$launchAnimation$2.AnonymousClass1.invokeSuspend$lambda$1(contentInViewNode2, updatableAnimationState3, bringIntoViewSpec);
                        }
                    }, this) == coroutine_suspended) {
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

        static final Unit invokeSuspend$lambda$0(ContentInViewNode this$0, UpdatableAnimationState $animationState, Job $animationJob, NestedScrollScope $$this$scroll, float delta) {
            float scrollMultiplier = this$0.reverseDirection ? 1.0f : -1.0f;
            float adjustedDelta = scrollMultiplier * delta;
            ScrollingLogic $this$invokeSuspend_u24lambda_u240_u240 = this$0.scrollingLogic;
            float consumedScroll = $this$invokeSuspend_u24lambda_u240_u240.m619toFloatk4lQ0M($this$invokeSuspend_u24lambda_u240_u240.m617reverseIfNeededMKHz9U($$this$scroll.mo558scrollByOzD1aCk($this$invokeSuspend_u24lambda_u240_u240.m617reverseIfNeededMKHz9U($this$invokeSuspend_u24lambda_u240_u240.m620toOffsettuRUvjQ(adjustedDelta)), NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI()))) * scrollMultiplier;
            if (Math.abs(consumedScroll) < Math.abs(delta)) {
                JobKt__JobKt.cancel$default($animationJob, "Scroll animation cancelled because scroll was not consumed (" + consumedScroll + " < " + delta + ')', null, 2, null);
            }
            return Unit.INSTANCE;
        }

        static final Unit invokeSuspend$lambda$1(ContentInViewNode this$0, UpdatableAnimationState $animationState, BringIntoViewSpec $bringIntoViewSpec) {
            BringIntoViewRequestPriorityQueue this_$iv = this$0.bringIntoViewRequests;
            while (true) {
                MutableVector this_$iv$iv = this_$iv.requests;
                if (!(this_$iv$iv.getSize() != 0)) {
                    break;
                }
                Rect bounds = ((ContentInViewNode.Request) this_$iv.requests.last()).getCurrentBounds().invoke();
                if (!(bounds == null ? true : ContentInViewNode.m463isMaxVisibleEQwtKw$default(this$0, bounds, 0L, 0L, 3, null))) {
                    break;
                }
                MutableVector mutableVector = this_$iv.requests;
                MutableVector this_$iv$iv2 = this_$iv.requests;
                CancellableContinuation<Unit> continuation = ((ContentInViewNode.Request) mutableVector.removeAt(this_$iv$iv2.getSize() - 1)).getContinuation();
                Unit unit = Unit.INSTANCE;
                Result.Companion companion = Result.INSTANCE;
                continuation.resumeWith(Result.m8929constructorimpl(unit));
            }
            if (this$0.trackingFocusedChild) {
                Rect rect = (Rect) this$0.getFocusedRect.invoke();
                if (rect != null && ContentInViewNode.m463isMaxVisibleEQwtKw$default(this$0, rect, 0L, 0L, 3, null)) {
                    this$0.trackingFocusedChild = false;
                }
            }
            $animationState.setValue(this$0.m459calculateScrollDeltaI_oMVgE($bringIntoViewSpec, IntOffset.INSTANCE.m8289getZeronOccac()));
            return Unit.INSTANCE;
        }
    }
}
