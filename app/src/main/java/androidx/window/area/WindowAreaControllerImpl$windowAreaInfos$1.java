package androidx.window.area;

import androidx.window.extensions.area.ExtensionWindowAreaStatus;
import androidx.window.reflection.Consumer2;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;

/* JADX INFO: compiled from: WindowAreaControllerImpl.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", "Landroidx/window/area/WindowAreaInfo;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1", f = "WindowAreaControllerImpl.kt", i = {}, l = {92}, m = "invokeSuspend", n = {}, s = {})
final class WindowAreaControllerImpl$windowAreaInfos$1 extends SuspendLambda implements Function2<ProducerScope<? super List<? extends WindowAreaInfo>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WindowAreaControllerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WindowAreaControllerImpl$windowAreaInfos$1(WindowAreaControllerImpl windowAreaControllerImpl, Continuation<? super WindowAreaControllerImpl$windowAreaInfos$1> continuation) {
        super(2, continuation);
        this.this$0 = windowAreaControllerImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        WindowAreaControllerImpl$windowAreaInfos$1 windowAreaControllerImpl$windowAreaInfos$1 = new WindowAreaControllerImpl$windowAreaInfos$1(this.this$0, continuation);
        windowAreaControllerImpl$windowAreaInfos$1.L$0 = obj;
        return windowAreaControllerImpl$windowAreaInfos$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super List<? extends WindowAreaInfo>> producerScope, Continuation<? super Unit> continuation) {
        return invoke2((ProducerScope<? super List<WindowAreaInfo>>) producerScope, continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(ProducerScope<? super List<WindowAreaInfo>> producerScope, Continuation<? super Unit> continuation) {
        return ((WindowAreaControllerImpl$windowAreaInfos$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                final ProducerScope $this$callbackFlow = (ProducerScope) this.L$0;
                final WindowAreaControllerImpl windowAreaControllerImpl = this.this$0;
                final Consumer2 rearDisplayListener = new Consumer2() { // from class: androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1$$ExternalSyntheticLambda0
                    @Override // androidx.window.reflection.Consumer2
                    public final void accept(Object obj) {
                        WindowAreaControllerImpl$windowAreaInfos$1.invokeSuspend$lambda$0(windowAreaControllerImpl, $this$callbackFlow, ((Integer) obj).intValue());
                    }
                };
                final WindowAreaControllerImpl windowAreaControllerImpl2 = this.this$0;
                final Consumer2 rearDisplayPresentationListener = new Consumer2() { // from class: androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1$$ExternalSyntheticLambda1
                    @Override // androidx.window.reflection.Consumer2
                    public final void accept(Object obj) {
                        WindowAreaControllerImpl$windowAreaInfos$1.invokeSuspend$lambda$1(windowAreaControllerImpl2, $this$callbackFlow, (ExtensionWindowAreaStatus) obj);
                    }
                };
                this.this$0.windowAreaComponent.addRearDisplayStatusListener(rearDisplayListener);
                this.this$0.windowAreaComponent.addRearDisplayPresentationStatusListener(rearDisplayPresentationListener);
                final WindowAreaControllerImpl windowAreaControllerImpl3 = this.this$0;
                this.label = 1;
                if (ProduceKt.awaitClose($this$callbackFlow, new Function0() { // from class: androidx.window.area.WindowAreaControllerImpl$windowAreaInfos$1$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return WindowAreaControllerImpl$windowAreaInfos$1.invokeSuspend$lambda$2(windowAreaControllerImpl3, rearDisplayListener, rearDisplayPresentationListener);
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

    static final void invokeSuspend$lambda$0(WindowAreaControllerImpl this$0, ProducerScope $$this$callbackFlow, int status) {
        this$0.updateRearDisplayAvailability(status);
        SendChannel channel = $$this$callbackFlow.getChannel();
        Collection collectionValues = this$0.currentWindowAreaInfoMap.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        channel.mo10436trySendJP2dKIU(CollectionsKt.toList(collectionValues));
    }

    static final void invokeSuspend$lambda$1(WindowAreaControllerImpl this$0, ProducerScope $$this$callbackFlow, ExtensionWindowAreaStatus extensionWindowAreaStatus) {
        this$0.updateRearDisplayPresentationAvailability(extensionWindowAreaStatus);
        SendChannel channel = $$this$callbackFlow.getChannel();
        Collection collectionValues = this$0.currentWindowAreaInfoMap.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "<get-values>(...)");
        channel.mo10436trySendJP2dKIU(CollectionsKt.toList(collectionValues));
    }

    static final Unit invokeSuspend$lambda$2(WindowAreaControllerImpl this$0, Consumer2 $rearDisplayListener, Consumer2 $rearDisplayPresentationListener) {
        this$0.windowAreaComponent.removeRearDisplayStatusListener($rearDisplayListener);
        this$0.windowAreaComponent.removeRearDisplayPresentationStatusListener($rearDisplayPresentationListener);
        return Unit.INSTANCE;
    }
}
