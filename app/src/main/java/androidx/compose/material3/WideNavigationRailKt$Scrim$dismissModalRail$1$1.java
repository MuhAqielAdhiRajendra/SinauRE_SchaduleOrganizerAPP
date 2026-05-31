package androidx.compose.material3;

import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: WideNavigationRail.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class WideNavigationRailKt$Scrim$dismissModalRail$1$1 implements PointerInputEventHandler {
    final /* synthetic */ MutableState<Boolean> $dismiss$delegate;

    WideNavigationRailKt$Scrim$dismissModalRail$1$1(MutableState<Boolean> mutableState) {
        this.$dismiss$delegate = mutableState;
    }

    static final Unit invoke$lambda$0(MutableState $dismiss$delegate, Offset it) {
        WideNavigationRailKt.Scrim_3J_VO9M$lambda$29($dismiss$delegate, true);
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
        final MutableState<Boolean> mutableState = this.$dismiss$delegate;
        Object objDetectTapGestures$default = TapGestureDetectorKt.detectTapGestures$default($this$pointerInput, null, null, null, new Function1() { // from class: androidx.compose.material3.WideNavigationRailKt$Scrim$dismissModalRail$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WideNavigationRailKt$Scrim$dismissModalRail$1$1.invoke$lambda$0(mutableState, (Offset) obj);
            }
        }, continuation, 7, null);
        return objDetectTapGestures$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures$default : Unit.INSTANCE;
    }
}
