package androidx.window.embedding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.core.util.Consumer;
import androidx.window.embedding.ActivityEmbeddingController;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: ActivityEmbeddingController.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\b\u001a\u00020\tJ\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000bH\u0001¢\u0006\u0002\b\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\b\u0010\u0018\u001a\u00020\u0012H\u0007J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/window/embedding/ActivityEmbeddingController;", "", "backend", "Landroidx/window/embedding/EmbeddingBackend;", "<init>", "(Landroidx/window/embedding/EmbeddingBackend;)V", "isActivityEmbedded", "", "activity", "Landroid/app/Activity;", "getActivityStack", "Landroidx/window/embedding/ActivityStack;", "setLaunchingActivityStack", "Landroid/os/Bundle;", "options", "activityStack", "setLaunchingActivityStack$window_release", "finishActivityStacks", "", "activityStacks", "", "setEmbeddingConfiguration", "embeddingConfiguration", "Landroidx/window/embedding/EmbeddingConfiguration;", "invalidateVisibleActivityStacks", "embeddedActivityWindowInfo", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ActivityEmbeddingController {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final EmbeddingBackend backend;

    public ActivityEmbeddingController(EmbeddingBackend backend) {
        Intrinsics.checkNotNullParameter(backend, "backend");
        this.backend = backend;
    }

    public final boolean isActivityEmbedded(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.backend.isActivityEmbedded(activity);
    }

    public final ActivityStack getActivityStack(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return this.backend.getActivityStack(activity);
    }

    public final Bundle setLaunchingActivityStack$window_release(Bundle options, ActivityStack activityStack) {
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(activityStack, "activityStack");
        return this.backend.setLaunchingActivityStack(options, activityStack);
    }

    public final void finishActivityStacks(Set<ActivityStack> activityStacks) {
        Intrinsics.checkNotNullParameter(activityStacks, "activityStacks");
        this.backend.finishActivityStacks(activityStacks);
    }

    public final void setEmbeddingConfiguration(EmbeddingConfiguration embeddingConfiguration) {
        Intrinsics.checkNotNullParameter(embeddingConfiguration, "embeddingConfiguration");
        this.backend.setEmbeddingConfiguration(embeddingConfiguration);
    }

    public final void invalidateVisibleActivityStacks() {
        this.backend.invalidateVisibleActivityStacks();
    }

    /* JADX INFO: renamed from: androidx.window.embedding.ActivityEmbeddingController$embeddedActivityWindowInfo$1, reason: invalid class name */
    /* JADX INFO: compiled from: ActivityEmbeddingController.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/window/embedding/EmbeddedActivityWindowInfo;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.window.embedding.ActivityEmbeddingController$embeddedActivityWindowInfo$1", f = "ActivityEmbeddingController.kt", i = {}, l = {174}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ProducerScope<? super EmbeddedActivityWindowInfo>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Activity $activity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Activity activity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$activity = activity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = ActivityEmbeddingController.this.new AnonymousClass1(this.$activity, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProducerScope<? super EmbeddedActivityWindowInfo> producerScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    final ProducerScope $this$callbackFlow = (ProducerScope) this.L$0;
                    final Consumer<EmbeddedActivityWindowInfo> consumer = new Consumer() { // from class: androidx.window.embedding.ActivityEmbeddingController$embeddedActivityWindowInfo$1$$ExternalSyntheticLambda0
                        @Override // androidx.core.util.Consumer
                        public final void accept(Object obj) {
                            $this$callbackFlow.mo10436trySendJP2dKIU((EmbeddedActivityWindowInfo) obj);
                        }
                    };
                    ActivityEmbeddingController.this.backend.addEmbeddedActivityWindowInfoCallbackForActivity(this.$activity, consumer);
                    final ActivityEmbeddingController activityEmbeddingController = ActivityEmbeddingController.this;
                    this.label = 1;
                    if (ProduceKt.awaitClose($this$callbackFlow, new Function0() { // from class: androidx.window.embedding.ActivityEmbeddingController$embeddedActivityWindowInfo$1$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ActivityEmbeddingController.AnonymousClass1.invokeSuspend$lambda$1(activityEmbeddingController, consumer);
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

        static final Unit invokeSuspend$lambda$1(ActivityEmbeddingController this$0, Consumer $callback) {
            this$0.backend.removeEmbeddedActivityWindowInfoCallbackForActivity($callback);
            return Unit.INSTANCE;
        }
    }

    public final Flow<EmbeddedActivityWindowInfo> embeddedActivityWindowInfo(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        return FlowKt.callbackFlow(new AnonymousClass1(activity, null));
    }

    /* JADX INFO: compiled from: ActivityEmbeddingController.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/window/embedding/ActivityEmbeddingController$Companion;", "", "<init>", "()V", "getInstance", "Landroidx/window/embedding/ActivityEmbeddingController;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final ActivityEmbeddingController getInstance(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            EmbeddingBackend backend = EmbeddingBackend.INSTANCE.getInstance(context);
            return new ActivityEmbeddingController(backend);
        }
    }

    @JvmStatic
    public static final ActivityEmbeddingController getInstance(Context context) {
        return INSTANCE.getInstance(context);
    }
}
