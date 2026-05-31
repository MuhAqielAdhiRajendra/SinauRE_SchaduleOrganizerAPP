package androidx.compose.foundation.gestures;

import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.HistoricalChange;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.constraintlayout.motion.widget.Key;
import androidx.core.app.NotificationCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.Channel;

/* JADX INFO: compiled from: Transformable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a(\u0010\f\u001a\u00020\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0014\u001a\u001e\u0010\u0015\u001a\u0004\u0018\u00010\t*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a\u0016\u0010\u0019\u001a\u0004\u0018\u00010\t*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a\u001b\u0010\u001a\u001a\u0004\u0018\u00010\u000b*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002¢\u0006\u0002\u0010\u001b\u001a<\u0010\u001c\u001a\u00020\r*\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\bH\u0082@¢\u0006\u0002\u0010\u001e\"\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"transformable", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/TransformableState;", "lockRotationOnZoomPan", "", "enabled", "canPan", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "SCROLL_FACTOR", "", "detectNonTouchGestures", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/TransformEvent;", "scrollConfig", "Landroidx/compose/foundation/gestures/ScrollConfig;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlinx/coroutines/channels/Channel;Landroidx/compose/foundation/gestures/ScrollConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumePointerEventAsCtrlScrollOrNull", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "pointer", "Landroidx/compose/ui/input/pointer/PointerEvent;", "consumePointerEventAsPanOrNull", "consumePointerEventAsScaleOrNull", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerEvent;)Ljava/lang/Float;", "detectZoom", "panZoomLock", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLkotlinx/coroutines/channels/Channel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TransformableKt {
    public static final float SCROLL_FACTOR = 545.0f;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$detectZoom$1, reason: invalid class name */
    /* JADX INFO: compiled from: Transformable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt", f = "Transformable.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {461, 463, 521}, m = "detectZoom", n = {"$this$detectZoom", "channel", "canPan", "panZoomLock", Key.ROTATION, "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom", "$this$detectZoom", "channel", "canPan", "panZoomLock", Key.ROTATION, "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom", "$this$detectZoom", "channel", "canPan", NotificationCompat.CATEGORY_EVENT, "panZoomLock", Key.ROTATION, "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom", "canceled"}, s = {"L$0", "L$1", "L$2", "Z$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1", "L$0", "L$1", "L$2", "Z$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1", "L$0", "L$1", "L$2", "L$3", "Z$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1", "I$2"}, v = 1)
    static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformableKt.detectZoom(null, false, null, null, this);
        }
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return transformable(modifier, transformableState, z, z2);
    }

    public static final Modifier transformable(Modifier $this$transformable, TransformableState state, boolean lockRotationOnZoomPan, boolean enabled) {
        return transformable($this$transformable, state, new Function1() { // from class: androidx.compose.foundation.gestures.TransformableKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TransformableKt.transformable$lambda$0((Offset) obj));
            }
        }, lockRotationOnZoomPan, enabled);
    }

    static final boolean transformable$lambda$0(Offset it) {
        return true;
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, Function1 function1, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        return transformable(modifier, transformableState, function1, z, z2);
    }

    public static final Modifier transformable(Modifier $this$transformable, TransformableState state, Function1<? super Offset, Boolean> function1, boolean lockRotationOnZoomPan, boolean enabled) {
        return $this$transformable.then(new TransformableElement(state, function1, lockRotationOnZoomPan, enabled));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$detectNonTouchGestures$2, reason: invalid class name */
    /* JADX INFO: compiled from: Transformable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$detectNonTouchGestures$2", f = "Transformable.kt", i = {0, 1, 2, 3}, l = {288, 315, 331, 349}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "$this$awaitPointerEventScope", "$this$awaitPointerEventScope", "$this$awaitPointerEventScope"}, s = {"L$0", "L$0", "L$0", "L$0"}, v = 1)
    static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Channel<TransformEvent> $channel;
        final /* synthetic */ CoroutineContext $currentContext;
        final /* synthetic */ ScrollConfig $scrollConfig;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(CoroutineContext coroutineContext, ScrollConfig scrollConfig, Channel<TransformEvent> channel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$currentContext = coroutineContext;
            this.$scrollConfig = scrollConfig;
            this.$channel = channel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$currentContext, this.$scrollConfig, this.$channel, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0078 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0079  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x009d A[Catch: all -> 0x01e1, TryCatch #1 {all -> 0x01e1, blocks: (B:27:0x0080, B:34:0x009d, B:35:0x00a8, B:38:0x00fd, B:40:0x0107, B:43:0x0118, B:57:0x0171, B:70:0x01d5, B:71:0x01e0), top: B:80:0x0080 }] */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00fc A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0107 A[Catch: all -> 0x01e1, TryCatch #1 {all -> 0x01e1, blocks: (B:27:0x0080, B:34:0x009d, B:35:0x00a8, B:38:0x00fd, B:40:0x0107, B:43:0x0118, B:57:0x0171, B:70:0x01d5, B:71:0x01e0), top: B:80:0x0080 }] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x010e  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x0116  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0153 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:51:0x015c A[Catch: all -> 0x016a, TRY_LEAVE, TryCatch #3 {all -> 0x016a, blocks: (B:63:0x01b9, B:65:0x01c1, B:60:0x0184, B:46:0x0127, B:49:0x0154, B:51:0x015c), top: B:84:0x01b9 }] */
        /* JADX WARN: Removed duplicated region for block: B:53:0x0162  */
        /* JADX WARN: Removed duplicated region for block: B:62:0x01b8 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x01c1 A[Catch: all -> 0x016a, TRY_LEAVE, TryCatch #3 {all -> 0x016a, blocks: (B:63:0x01b9, B:65:0x01c1, B:60:0x0184, B:46:0x0127, B:49:0x0154, B:51:0x015c), top: B:84:0x01b9 }] */
        /* JADX WARN: Removed duplicated region for block: B:67:0x01c7  */
        /* JADX WARN: Removed duplicated region for block: B:77:0x01ee  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x0097 -> B:82:0x006b). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x0107 -> B:35:0x00a8). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x010e -> B:68:0x01ca). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:52:0x0160 -> B:46:0x0127). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:53:0x0162 -> B:68:0x01ca). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x01b6 -> B:84:0x01b9). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r21) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 512
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        static final boolean invokeSuspend$lambda$0(PointerInputChange it) {
            return true;
        }

        static final boolean invokeSuspend$lambda$1(PointerInputChange it) {
            return true;
        }

        static final boolean invokeSuspend$lambda$3(PointerInputChange it) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectNonTouchGestures(PointerInputScope $this$detectNonTouchGestures, Channel<TransformEvent> channel, ScrollConfig scrollConfig, Continuation<? super Unit> continuation) {
        CoroutineContext currentContext = continuation.getContext();
        Object objAwaitPointerEventScope = $this$detectNonTouchGestures.awaitPointerEventScope(new AnonymousClass2(currentContext, scrollConfig, channel, null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset consumePointerEventAsCtrlScrollOrNull(AwaitPointerEventScope $this$consumePointerEventAsCtrlScrollOrNull, PointerEvent pointer, ScrollConfig scrollConfig) {
        long j;
        Offset offset;
        long jM5084getZeroF1C5BW0;
        PointerInputChange it;
        if (!PointerEvent_androidKt.m6614isCtrlPressed5xRPYO0(pointer.getKeyboardModifiers())) {
            return null;
        }
        if (!PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6605getScroll7fucELk()) && !PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6599getPanStart7fucELk()) && !PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6598getPanMove7fucELk()) && !PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6597getPanEnd7fucELk())) {
            return null;
        }
        long jMo453calculateMouseWheelScroll8xgXZGE = scrollConfig.mo453calculateMouseWheelScroll8xgXZGE($this$consumePointerEventAsCtrlScrollOrNull, pointer, $this$consumePointerEventAsCtrlScrollOrNull.mo6535getSizeYbymL2g());
        if (ComposeFoundationFlags.isTrackpadGestureHandlingEnabled && (it = (PointerInputChange) CollectionsKt.firstOrNull((List) pointer.getChanges())) != null) {
            int i = 0;
            long arg0$iv = it.getPanOffset();
            long arg0$iv2 = Offset.m5060constructorimpl((-9223372034707292160L) ^ arg0$iv);
            List<HistoricalChange> historical = it.getHistorical();
            Offset offsetM5057boximpl = Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0());
            int index$iv$iv = 0;
            int size = historical.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = historical.get(index$iv$iv);
                HistoricalChange historicalChange = (HistoricalChange) item$iv$iv;
                long acc = offsetM5057boximpl.m5078unboximpl();
                offsetM5057boximpl = Offset.m5057boximpl(Offset.m5072minusMKHz9U(acc, historicalChange.getPanOffset()));
                index$iv$iv++;
                jMo453calculateMouseWheelScroll8xgXZGE = jMo453calculateMouseWheelScroll8xgXZGE;
                i = i;
            }
            j = jMo453calculateMouseWheelScroll8xgXZGE;
            offset = null;
            jM5084getZeroF1C5BW0 = Offset.m5073plusMKHz9U(arg0$iv2, offsetM5057boximpl.m5078unboximpl());
        } else {
            j = jMo453calculateMouseWheelScroll8xgXZGE;
            offset = null;
            jM5084getZeroF1C5BW0 = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        long scrollDelta = Offset.m5073plusMKHz9U(j, jM5084getZeroF1C5BW0);
        if (Offset.m5065equalsimpl0(scrollDelta, Offset.INSTANCE.m5084getZeroF1C5BW0())) {
            return offset;
        }
        List<PointerInputChange> changes = pointer.getChanges();
        int size2 = changes.size();
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = changes.get(index$iv);
            ((PointerInputChange) item$iv).consume();
        }
        return Offset.m5057boximpl(scrollDelta);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Offset consumePointerEventAsPanOrNull(AwaitPointerEventScope $this$consumePointerEventAsPanOrNull, PointerEvent pointer) {
        Offset offset;
        long scrollDelta;
        if (!ComposeFoundationFlags.isTrackpadGestureHandlingEnabled) {
            return null;
        }
        if (!PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6599getPanStart7fucELk()) && !PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6598getPanMove7fucELk()) && !PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6597getPanEnd7fucELk())) {
            return null;
        }
        PointerInputChange it = (PointerInputChange) CollectionsKt.firstOrNull((List) pointer.getChanges());
        if (it != null) {
            int i = 0;
            long arg0$iv = it.getPanOffset();
            long arg0$iv2 = Offset.m5060constructorimpl((-9223372034707292160L) ^ arg0$iv);
            List<HistoricalChange> historical = it.getHistorical();
            Offset offsetM5057boximpl = Offset.m5057boximpl(Offset.INSTANCE.m5084getZeroF1C5BW0());
            Offset offsetM5057boximpl2 = offsetM5057boximpl;
            int index$iv$iv = 0;
            int size = historical.size();
            while (index$iv$iv < size) {
                Object item$iv$iv = historical.get(index$iv$iv);
                HistoricalChange historicalChange = (HistoricalChange) item$iv$iv;
                long acc = offsetM5057boximpl2.m5078unboximpl();
                offsetM5057boximpl2 = Offset.m5057boximpl(Offset.m5072minusMKHz9U(acc, historicalChange.getPanOffset()));
                index$iv$iv++;
                i = i;
                historical = historical;
                offsetM5057boximpl = offsetM5057boximpl;
            }
            offset = null;
            scrollDelta = Offset.m5073plusMKHz9U(arg0$iv2, offsetM5057boximpl2.m5078unboximpl());
        } else {
            offset = null;
            scrollDelta = Offset.INSTANCE.m5084getZeroF1C5BW0();
        }
        if (Offset.m5065equalsimpl0(scrollDelta, Offset.INSTANCE.m5084getZeroF1C5BW0())) {
            return offset;
        }
        List<PointerInputChange> changes = pointer.getChanges();
        int size2 = changes.size();
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = changes.get(index$iv);
            ((PointerInputChange) item$iv).consume();
        }
        return Offset.m5057boximpl(scrollDelta);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Float consumePointerEventAsScaleOrNull(AwaitPointerEventScope $this$consumePointerEventAsScaleOrNull, PointerEvent pointer) {
        if (!ComposeFoundationFlags.isTrackpadGestureHandlingEnabled || (!PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6604getScaleStart7fucELk()) && !PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6602getScaleChange7fucELk()) && !PointerEventType.m6590equalsimpl0(pointer.getType(), PointerEventType.INSTANCE.m6603getScaleEnd7fucELk()))) {
            return null;
        }
        float scaleDelta = 1.0f;
        List<PointerInputChange> changes = pointer.getChanges();
        int size = changes.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = changes.get(index$iv);
            PointerInputChange it = (PointerInputChange) item$iv;
            scaleDelta *= it.getScaleFactor();
            List<HistoricalChange> historical = it.getHistorical();
            int size2 = historical.size();
            for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
                Object item$iv2 = historical.get(index$iv2);
                scaleDelta *= ((HistoricalChange) item$iv2).getScaleFactor();
            }
        }
        if (scaleDelta == 1.0f) {
            return null;
        }
        List<PointerInputChange> changes2 = pointer.getChanges();
        int size3 = changes2.size();
        for (int index$iv3 = 0; index$iv3 < size3; index$iv3++) {
            Object item$iv3 = changes2.get(index$iv3);
            ((PointerInputChange) item$iv3).consume();
        }
        return Float.valueOf(scaleDelta);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Path cross not found for [B:116:0x0397, B:118:0x039b], limit reached: 137 */
    /* JADX WARN: Path cross not found for [B:32:0x0188, B:48:0x01ef], limit reached: 137 */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0341 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0342  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0378  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0392 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03d4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0182 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0133 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01f3  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0016  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02e3  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:107:0x0342 -> B:108:0x035b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope r31, boolean r32, kotlinx.coroutines.channels.Channel<androidx.compose.foundation.gestures.TransformEvent> r33, kotlin.jvm.functions.Function1<? super androidx.compose.ui.geometry.Offset, java.lang.Boolean> r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) {
        /*
            Method dump skipped, instruction units count: 1018
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt.detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, kotlinx.coroutines.channels.Channel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
