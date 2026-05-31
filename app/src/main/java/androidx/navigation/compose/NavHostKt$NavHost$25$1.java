package androidx.navigation.compose;

import androidx.activity.BackEventCompat;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.navigation.NavBackStackEntry;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: compiled from: NavHost.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003H\n"}, d2 = {"<anonymous>", "", "backEvent", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/activity/BackEventCompat;", "Landroidx/navigation/compose/internal/BackEventCompat;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.navigation.compose.NavHostKt$NavHost$25$1", f = "NavHost.kt", i = {1}, l = {524, 534}, m = "invokeSuspend", n = {"currentBackStackEntry"}, s = {"L$0"})
final class NavHostKt$NavHost$25$1 extends SuspendLambda implements Function2<Flow<? extends BackEventCompat>, Continuation<? super Unit>, Object> {
    final /* synthetic */ ComposeNavigator $composeNavigator;
    final /* synthetic */ State<List<NavBackStackEntry>> $currentBackStack$delegate;
    final /* synthetic */ MutableState<Boolean> $inPredictiveBack$delegate;
    final /* synthetic */ MutableFloatState $progress$delegate;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    NavHostKt$NavHost$25$1(ComposeNavigator composeNavigator, State<? extends List<NavBackStackEntry>> state, MutableFloatState mutableFloatState, MutableState<Boolean> mutableState, Continuation<? super NavHostKt$NavHost$25$1> continuation) {
        super(2, continuation);
        this.$composeNavigator = composeNavigator;
        this.$currentBackStack$delegate = state;
        this.$progress$delegate = mutableFloatState;
        this.$inPredictiveBack$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NavHostKt$NavHost$25$1 navHostKt$NavHost$25$1 = new NavHostKt$NavHost$25$1(this.$composeNavigator, this.$currentBackStack$delegate, this.$progress$delegate, this.$inPredictiveBack$delegate, continuation);
        navHostKt$NavHost$25$1.L$0 = obj;
        return navHostKt$NavHost$25$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(Flow<? extends BackEventCompat> flow, Continuation<? super Unit> continuation) {
        return invoke2((Flow<BackEventCompat>) flow, continuation);
    }

    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Flow<BackEventCompat> flow, Continuation<? super Unit> continuation) {
        return ((NavHostKt$NavHost$25$1) create(flow, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        NavBackStackEntry currentBackStackEntry;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    Flow backEvent = (Flow) this.L$0;
                    if (NavHostKt.NavHost$lambda$38(this.$currentBackStack$delegate).size() < 2) {
                        this.label = 1;
                        if (backEvent.collect(new FlowCollector() { // from class: androidx.navigation.compose.NavHostKt$NavHost$25$1.1
                            public final Object emit(BackEventCompat it, Continuation<? super Unit> continuation) {
                                return Unit.INSTANCE;
                            }

                            @Override // kotlinx.coroutines.flow.FlowCollector
                            public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                                return emit((BackEventCompat) value, (Continuation<? super Unit>) $completion);
                            }
                        }, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        return Unit.INSTANCE;
                    }
                    this.$progress$delegate.setFloatValue(0.0f);
                    NavBackStackEntry currentBackStackEntry2 = (NavBackStackEntry) CollectionsKt.last(NavHostKt.NavHost$lambda$38(this.$currentBackStack$delegate));
                    this.$composeNavigator.prepareForTransition(currentBackStackEntry2);
                    NavBackStackEntry previousEntry = (NavBackStackEntry) NavHostKt.NavHost$lambda$38(this.$currentBackStack$delegate).get(NavHostKt.NavHost$lambda$38(this.$currentBackStack$delegate).size() - 2);
                    this.$composeNavigator.prepareForTransition(previousEntry);
                    final MutableState<Boolean> mutableState = this.$inPredictiveBack$delegate;
                    final MutableFloatState mutableFloatState = this.$progress$delegate;
                    this.L$0 = currentBackStackEntry2;
                    this.label = 2;
                    if (backEvent.collect(new FlowCollector() { // from class: androidx.navigation.compose.NavHostKt$NavHost$25$1.2
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((BackEventCompat) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(BackEventCompat it, Continuation<? super Unit> continuation) {
                            NavHostKt.NavHost$lambda$44(mutableState, true);
                            mutableFloatState.setFloatValue(it.getProgress());
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    currentBackStackEntry = currentBackStackEntry2;
                    this.$composeNavigator.popBackStack(currentBackStackEntry, false);
                    NavHostKt.NavHost$lambda$44(this.$inPredictiveBack$delegate, false);
                    return Unit.INSTANCE;
                case 1:
                    ResultKt.throwOnFailure($result);
                    return Unit.INSTANCE;
                case 2:
                    currentBackStackEntry = (NavBackStackEntry) this.L$0;
                    ResultKt.throwOnFailure($result);
                    this.$composeNavigator.popBackStack(currentBackStackEntry, false);
                    NavHostKt.NavHost$lambda$44(this.$inPredictiveBack$delegate, false);
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } catch (Throwable th) {
            NavHostKt.NavHost$lambda$44(this.$inPredictiveBack$delegate, false);
            throw th;
        }
    }
}
