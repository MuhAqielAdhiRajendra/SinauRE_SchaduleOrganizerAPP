package androidx.compose.foundation.gestures;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import androidx.compose.ui.input.pointer.HistoricalChange;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Velocity;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ChannelResult;

/* JADX INFO: compiled from: TrackpadScrollingLogic.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001:\u00010BJ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00121\u0010\u0004\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0005\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J'\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0014\u0010$\u001a\u0004\u0018\u00010\u001c*\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u001b\u0010%\u001a\u00020#*\u00020\u00032\u0006\u0010&\u001a\u00020'H\u0002¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u001cH\u0002J\u001a\u0010+\u001a\u00020\u000b*\u00020\u00032\u0006\u0010&\u001a\u00020\u001cH\u0082@¢\u0006\u0002\u0010,J\u0014\u0010+\u001a\u00020-*\u00020.2\u0006\u0010/\u001a\u00020-H\u0002R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Landroidx/compose/foundation/gestures/TrackpadScrollingLogic;", "Landroidx/compose/foundation/gestures/NonTouchScrollingLogic;", "scrollingLogic", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "onScrollStopped", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/Velocity;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "velocity", "Lkotlin/coroutines/Continuation;", "", "", "density", "Landroidx/compose/ui/unit/Density;", "<init>", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/unit/Density;)V", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/TrackpadScrollingLogic$TrackpadScrollDelta;", "receivingPanEventsJob", "Lkotlinx/coroutines/Job;", "startReceivingEvents", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "onPan", "", "sumOrNull", "canConsumeDelta", "scrollDelta", "Landroidx/compose/ui/geometry/Offset;", "canConsumeDelta-Uv8p0NA", "(Landroidx/compose/foundation/gestures/ScrollingLogic;J)Z", "trackVelocity", "dispatchTrackpadScroll", "(Landroidx/compose/foundation/gestures/ScrollingLogic;Landroidx/compose/foundation/gestures/TrackpadScrollingLogic$TrackpadScrollDelta;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;", "delta", "TrackpadScrollDelta", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class TrackpadScrollingLogic extends NonTouchScrollingLogic {
    public static final int $stable = 8;
    private final Channel<TrackpadScrollDelta> channel;
    private Job receivingPanEventsJob;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$1 */
    /* JADX INFO: compiled from: TrackpadScrollingLogic.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TrackpadScrollingLogic", f = "TrackpadScrollingLogic.kt", i = {}, l = {173, 190}, m = "dispatchTrackpadScroll", n = {}, s = {}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TrackpadScrollingLogic.this.dispatchTrackpadScroll(null, null, this);
        }
    }

    public TrackpadScrollingLogic(ScrollingLogic scrollingLogic, Function2<? super Velocity, ? super Continuation<? super Unit>, ? extends Object> function2, Density density) {
        super(scrollingLogic, function2, density);
        this.channel = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
    }

    @Override // androidx.compose.foundation.gestures.NonTouchScrollingLogic
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo553onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        boolean z;
        if (ComposeFoundationFlags.isTrackpadGestureHandlingEnabled) {
            if (!PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6599getPanStart7fucELk()) && !PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6598getPanMove7fucELk()) && !PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6597getPanEnd7fucELk())) {
                return;
            }
            List<PointerInputChange> changes = pointerEvent.getChanges();
            int index$iv$iv$iv = 0;
            int size = changes.size();
            while (true) {
                if (index$iv$iv$iv < size) {
                    Object item$iv$iv$iv = changes.get(index$iv$iv$iv);
                    PointerInputChange it$iv = (PointerInputChange) item$iv$iv$iv;
                    if (it$iv.isConsumed()) {
                        z = true;
                        break;
                    }
                    index$iv$iv$iv++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                return;
            }
            if (pass == PointerEventPass.Initial && getIsScrolling()) {
                onPan(pointerEvent);
                consume$foundation(pointerEvent);
            }
            if (pass == PointerEventPass.Main && !getIsScrolling()) {
                boolean consumed = onPan(pointerEvent);
                if (consumed) {
                    consume$foundation(pointerEvent);
                }
            }
        }
    }

    /* JADX INFO: compiled from: TrackpadScrollingLogic.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0000H\u0086\u0002R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u0011"}, d2 = {"Landroidx/compose/foundation/gestures/TrackpadScrollingLogic$TrackpadScrollDelta;", "", "value", "Landroidx/compose/ui/geometry/Offset;", "timeMillis", "", "isEnd", "", "<init>", "(JJZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getValue-F1C5BW0", "()J", "J", "getTimeMillis", "()Z", "plus", "other", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
    static final class TrackpadScrollDelta {
        private final boolean isEnd;
        private final long timeMillis;
        private final long value;

        public /* synthetic */ TrackpadScrollDelta(long j, long j2, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
            this(j, j2, z);
        }

        private TrackpadScrollDelta(long value, long timeMillis, boolean isEnd) {
            this.value = value;
            this.timeMillis = timeMillis;
            this.isEnd = isEnd;
        }

        public final long getTimeMillis() {
            return this.timeMillis;
        }

        /* JADX INFO: renamed from: getValue-F1C5BW0, reason: from getter */
        public final long getValue() {
            return this.value;
        }

        /* JADX INFO: renamed from: isEnd, reason: from getter */
        public final boolean getIsEnd() {
            return this.isEnd;
        }

        public final TrackpadScrollDelta plus(TrackpadScrollDelta other) {
            return new TrackpadScrollDelta(Offset.m5073plusMKHz9U(this.value, other.value), Math.max(this.timeMillis, other.timeMillis), this.isEnd || other.isEnd, null);
        }
    }

    @Override // androidx.compose.foundation.gestures.NonTouchScrollingLogic
    public void startReceivingEvents(CoroutineScope coroutineScope) {
        if (this.receivingPanEventsJob == null) {
            this.receivingPanEventsJob = BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new C01771(null), 3, null);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TrackpadScrollingLogic$startReceivingEvents$1 */
    /* JADX INFO: compiled from: TrackpadScrollingLogic.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TrackpadScrollingLogic$startReceivingEvents$1", f = "TrackpadScrollingLogic.kt", i = {0, 1}, l = {99, 99}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"}, v = 1)
    static final class C01771 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        int label;

        C01771(Continuation<? super C01771> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01771 c01771 = TrackpadScrollingLogic.this.new C01771(continuation);
            c01771.L$0 = obj;
            return c01771;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C01771) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:55:0x0047 A[Catch: all -> 0x0098, TRY_LEAVE, TryCatch #1 {all -> 0x0098, blocks: (B:53:0x003d, B:55:0x0047), top: B:72:0x003d }] */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0083 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:62:0x0084  */
        /* JADX WARN: Removed duplicated region for block: B:66:0x008f  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:62:0x0084 -> B:72:0x003d). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 0
                switch(r1) {
                    case 0: goto L35;
                    case 1: goto L1d;
                    case 2: goto L13;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L13:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L31
                r3 = r10
                goto L88
            L1d:
                java.lang.Object r1 = r10.L$2
                androidx.compose.foundation.gestures.ScrollingLogic r1 = (androidx.compose.foundation.gestures.ScrollingLogic) r1
                java.lang.Object r3 = r10.L$1
                androidx.compose.foundation.gestures.TrackpadScrollingLogic r3 = (androidx.compose.foundation.gestures.TrackpadScrollingLogic) r3
                java.lang.Object r4 = r10.L$0
                kotlinx.coroutines.CoroutineScope r4 = (kotlinx.coroutines.CoroutineScope) r4
                kotlin.ResultKt.throwOnFailure(r11)     // Catch: java.lang.Throwable -> L31
                r6 = r10
                r5 = r1
                r1 = r0
                r0 = r11
                goto L6f
            L31:
                r0 = move-exception
                r3 = r10
                goto L99
            L35:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
                r3 = r10
            L3d:
                kotlin.coroutines.CoroutineContext r4 = r1.getCoroutineContext()     // Catch: java.lang.Throwable -> L98
                boolean r4 = kotlinx.coroutines.JobKt.isActive(r4)     // Catch: java.lang.Throwable -> L98
                if (r4 == 0) goto L8f
                androidx.compose.foundation.gestures.TrackpadScrollingLogic r4 = androidx.compose.foundation.gestures.TrackpadScrollingLogic.this     // Catch: java.lang.Throwable -> L98
                androidx.compose.foundation.gestures.TrackpadScrollingLogic r5 = androidx.compose.foundation.gestures.TrackpadScrollingLogic.this     // Catch: java.lang.Throwable -> L98
                androidx.compose.foundation.gestures.ScrollingLogic r5 = r5.getScrollingLogic()     // Catch: java.lang.Throwable -> L98
                androidx.compose.foundation.gestures.TrackpadScrollingLogic r6 = androidx.compose.foundation.gestures.TrackpadScrollingLogic.this     // Catch: java.lang.Throwable -> L98
                kotlinx.coroutines.channels.Channel r6 = androidx.compose.foundation.gestures.TrackpadScrollingLogic.access$getChannel$p(r6)     // Catch: java.lang.Throwable -> L98
                r7 = r3
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Throwable -> L98
                r3.L$0 = r1     // Catch: java.lang.Throwable -> L98
                r3.L$1 = r4     // Catch: java.lang.Throwable -> L98
                r3.L$2 = r5     // Catch: java.lang.Throwable -> L98
                r8 = 1
                r3.label = r8     // Catch: java.lang.Throwable -> L98
                java.lang.Object r6 = r6.receive(r7)     // Catch: java.lang.Throwable -> L98
                if (r6 != r0) goto L68
                return r0
            L68:
                r9 = r0
                r0 = r11
                r11 = r6
                r6 = r3
                r3 = r4
                r4 = r1
                r1 = r9
            L6f:
                androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta r11 = (androidx.compose.foundation.gestures.TrackpadScrollingLogic.TrackpadScrollDelta) r11     // Catch: java.lang.Throwable -> L89
                r7 = r6
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7     // Catch: java.lang.Throwable -> L89
                r6.L$0 = r4     // Catch: java.lang.Throwable -> L89
                r6.L$1 = r2     // Catch: java.lang.Throwable -> L89
                r6.L$2 = r2     // Catch: java.lang.Throwable -> L89
                r8 = 2
                r6.label = r8     // Catch: java.lang.Throwable -> L89
                java.lang.Object r11 = androidx.compose.foundation.gestures.TrackpadScrollingLogic.access$dispatchTrackpadScroll(r3, r5, r11, r7)     // Catch: java.lang.Throwable -> L89
                if (r11 != r1) goto L84
                return r1
            L84:
                r11 = r0
                r0 = r1
                r1 = r4
                r3 = r6
            L88:
                goto L3d
            L89:
                r11 = move-exception
                r3 = r0
                r0 = r11
                r11 = r3
                r3 = r6
                goto L99
            L8f:
                androidx.compose.foundation.gestures.TrackpadScrollingLogic r0 = androidx.compose.foundation.gestures.TrackpadScrollingLogic.this
                androidx.compose.foundation.gestures.TrackpadScrollingLogic.access$setReceivingPanEventsJob$p(r0, r2)
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            L98:
                r0 = move-exception
            L99:
                androidx.compose.foundation.gestures.TrackpadScrollingLogic r1 = androidx.compose.foundation.gestures.TrackpadScrollingLogic.this
                androidx.compose.foundation.gestures.TrackpadScrollingLogic.access$setReceivingPanEventsJob$p(r1, r2)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TrackpadScrollingLogic.C01771.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final boolean onPan(PointerEvent pointerEvent) {
        if (!ComposeFoundationFlags.isTrackpadGestureHandlingEnabled) {
            return false;
        }
        boolean sent = false;
        PointerInputChange it = (PointerInputChange) CollectionsKt.firstOrNull((List) pointerEvent.getChanges());
        if (it != null) {
            List<HistoricalChange> historical = it.getHistorical();
            int size = historical.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = historical.get(index$iv);
                HistoricalChange historicalChange = (HistoricalChange) item$iv;
                long delta = Offset.m5060constructorimpl((-9223372034707292160L) ^ historicalChange.getPanOffset());
                if (m640canConsumeDeltaUv8p0NA(getScrollingLogic(), delta)) {
                    sent = ChannelResult.m10460isSuccessimpl(this.channel.mo10436trySendJP2dKIU(new TrackpadScrollDelta(delta, historicalChange.getUptimeMillis(), false, null))) || sent;
                }
            }
            long arg0$iv = Offset.m5060constructorimpl(it.getPanOffset() ^ (-9223372034707292160L));
            boolean isPanEnd = PointerEventType.m6590equalsimpl0(pointerEvent.getType(), PointerEventType.INSTANCE.m6597getPanEnd7fucELk());
            if (m640canConsumeDeltaUv8p0NA(getScrollingLogic(), arg0$iv) || isPanEnd) {
                sent = ChannelResult.m10460isSuccessimpl(this.channel.mo10436trySendJP2dKIU(new TrackpadScrollDelta(arg0$iv, it.getUptimeMillis(), isPanEnd, null))) || sent;
            }
        }
        return sent || getIsScrolling();
    }

    public final TrackpadScrollDelta sumOrNull(final Channel<TrackpadScrollDelta> channel) {
        TrackpadScrollDelta sum = null;
        for (TrackpadScrollDelta i : NonTouchScrollingLogicKt.untilNull(new Function0() { // from class: androidx.compose.foundation.gestures.TrackpadScrollingLogic$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TrackpadScrollingLogic.sumOrNull$lambda$0(channel);
            }
        })) {
            sum = sum == null ? i : sum.plus(i);
        }
        return sum;
    }

    static final TrackpadScrollDelta sumOrNull$lambda$0(Channel $this_sumOrNull) {
        return (TrackpadScrollDelta) ChannelResult.m10455getOrNullimpl($this_sumOrNull.mo10443tryReceivePtdJZtk());
    }

    /* JADX INFO: renamed from: canConsumeDelta-Uv8p0NA */
    private final boolean m640canConsumeDeltaUv8p0NA(ScrollingLogic $this$canConsumeDelta_u2dUv8p0NA, long scrollDelta) {
        return !($this$canConsumeDelta_u2dUv8p0NA.m621toSingleAxisDeltaFromAnglek4lQ0M($this$canConsumeDelta_u2dUv8p0NA.m617reverseIfNeededMKHz9U(scrollDelta)) == 0.0f);
    }

    public final void trackVelocity(TrackpadScrollDelta scrollDelta) {
        getVelocityTracker().m477addDeltaUv8p0NA(scrollDelta.getTimeMillis(), scrollDelta.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0014  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0088 A[RETURN] */
    /* JADX WARN: Type inference failed for: r6v2, types: [T, androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object dispatchTrackpadScroll(androidx.compose.foundation.gestures.ScrollingLogic r8, androidx.compose.foundation.gestures.TrackpadScrollingLogic.TrackpadScrollDelta r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            r7 = this;
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.TrackpadScrollingLogic.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$1 r0 = (androidx.compose.foundation.gestures.TrackpadScrollingLogic.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r1 = r0.label
            int r1 = r1 - r2
            r0.label = r1
            goto L19
        L14:
            androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$1 r0 = new androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$1
            r0.<init>(r10)
        L19:
            java.lang.Object r1 = r0.result
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r0.label
            switch(r3) {
                case 0: goto L36;
                case 1: goto L31;
                case 2: goto L2d;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2d:
            kotlin.ResultKt.throwOnFailure(r1)
            goto L89
        L31:
            r8 = r7
            kotlin.ResultKt.throwOnFailure(r1)
            goto L6f
        L36:
            kotlin.ResultKt.throwOnFailure(r1)
            r3 = r7
            kotlin.jvm.internal.Ref$ObjectRef r4 = new kotlin.jvm.internal.Ref$ObjectRef
            r4.<init>()
            r4.element = r9
            r3.trackVelocity(r9)
            kotlinx.coroutines.channels.Channel<androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta> r9 = r3.channel
            androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta r9 = r3.sumOrNull(r9)
            if (r9 == 0) goto L5c
            r5 = 0
            r3.trackVelocity(r9)
            T r6 = r4.element
            androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta r6 = (androidx.compose.foundation.gestures.TrackpadScrollingLogic.TrackpadScrollDelta) r6
            androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta r6 = r6.plus(r9)
            r4.element = r6
        L5c:
            androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$3 r9 = new androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$3
            r5 = 0
            r9.<init>(r8, r4, r5)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r5 = 1
            r0.label = r5
            java.lang.Object r8 = r3.userScroll$foundation(r9, r0)
            if (r8 != r2) goto L6e
            return r2
        L6e:
            r8 = r3
        L6f:
            kotlin.jvm.functions.Function2 r9 = r8.getOnScrollStopped()
            androidx.compose.foundation.gestures.DifferentialVelocityTracker r3 = r8.getVelocityTracker()
            long r3 = r3.m478calculateVelocity9UxMQ8M()
            androidx.compose.ui.unit.Velocity r3 = androidx.compose.ui.unit.Velocity.m8379boximpl(r3)
            r4 = 2
            r0.label = r4
            java.lang.Object r8 = r9.invoke(r3, r0)
            if (r8 != r2) goto L89
            return r2
        L89:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TrackpadScrollingLogic.dispatchTrackpadScroll(androidx.compose.foundation.gestures.ScrollingLogic, androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$3 */
    /* JADX INFO: compiled from: TrackpadScrollingLogic.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TrackpadScrollingLogic$dispatchTrackpadScroll$3", f = "TrackpadScrollingLogic.kt", i = {0}, l = {178}, m = "invokeSuspend", n = {"$this$userScroll"}, s = {"L$0"}, v = 1)
    static final class AnonymousClass3 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef<TrackpadScrollDelta> $targetScrollDelta;
        final /* synthetic */ ScrollingLogic $this_dispatchTrackpadScroll;
        private /* synthetic */ Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(ScrollingLogic scrollingLogic, Ref.ObjectRef<TrackpadScrollDelta> objectRef, Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
            this.$this_dispatchTrackpadScroll = scrollingLogic;
            this.$targetScrollDelta = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = TrackpadScrollingLogic.this.new AnonymousClass3(this.$this_dispatchTrackpadScroll, this.$targetScrollDelta, continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(nestedScrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0053  */
        /* JADX WARN: Removed duplicated region for block: B:36:0x008d  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00c1  */
        /* JADX WARN: Type inference failed for: r3v18, types: [T, androidx.compose.foundation.gestures.TrackpadScrollingLogic$TrackpadScrollDelta] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x006c -> B:34:0x0072). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                Method dump skipped, instruction units count: 204
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TrackpadScrollingLogic.AnonymousClass3.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final float dispatchTrackpadScroll(NestedScrollScope $this$dispatchTrackpadScroll, float delta) {
        ScrollingLogic $this$dispatchTrackpadScroll_u24lambda_u241 = getScrollingLogic();
        long offset = $this$dispatchTrackpadScroll_u24lambda_u241.m620toOffsettuRUvjQ($this$dispatchTrackpadScroll_u24lambda_u241.reverseIfNeeded(delta));
        long consumed = $this$dispatchTrackpadScroll.mo559scrollByWithOverscrollOzD1aCk(offset, NestedScrollSource.INSTANCE.m6519getUserInputWNlRxjI());
        return $this$dispatchTrackpadScroll_u24lambda_u241.m619toFloatk4lQ0M($this$dispatchTrackpadScroll_u24lambda_u241.m617reverseIfNeededMKHz9U(consumed));
    }
}
