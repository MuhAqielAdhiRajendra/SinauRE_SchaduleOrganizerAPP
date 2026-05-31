package androidx.compose.material3;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: SearchBar.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.material3.EnterAlwaysSearchBarScrollBehavior", f = "SearchBar.kt", i = {0, 0, 1}, l = {972, 986}, m = "settleSearchBar-OhffZ5M", n = {"remainingVelocity", "scrollFraction", "remainingVelocity"}, s = {"L$0", "F$0", "L$0"})
final class EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1 extends ContinuationImpl {
    float F$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ EnterAlwaysSearchBarScrollBehavior this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1(EnterAlwaysSearchBarScrollBehavior enterAlwaysSearchBarScrollBehavior, Continuation<? super EnterAlwaysSearchBarScrollBehavior$settleSearchBar$1> continuation) {
        super(continuation);
        this.this$0 = enterAlwaysSearchBarScrollBehavior;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.m2498settleSearchBarOhffZ5M(0.0f, this);
    }
}
