package androidx.compose.ui.platform;

import android.os.Looper;
import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionServiceKey;
import androidx.compose.runtime.CompositionServices;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.R;
import androidx.compose.ui.platform.WrappedComposition;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Wrapper.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\bJ \u0010\u0016\u001a\u00020\u00132\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014H\u0017¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0013H\u0016J\u0018\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J#\u0010#\u001a\u0004\u0018\u0001H$\"\u0004\b\u0000\u0010$2\f\u0010%\u001a\b\u0012\u0004\u0012\u0002H$0&H\u0016¢\u0006\u0002\u0010'R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001d\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001c¨\u0006("}, d2 = {"Landroidx/compose/ui/platform/WrappedComposition;", "Landroidx/compose/runtime/Composition;", "Landroidx/lifecycle/LifecycleEventObserver;", "Landroidx/compose/runtime/CompositionServices;", "owner", "Landroidx/compose/ui/platform/AndroidComposeView;", "original", "<init>", "(Landroidx/compose/ui/platform/AndroidComposeView;Landroidx/compose/runtime/Composition;)V", "getOwner", "()Landroidx/compose/ui/platform/AndroidComposeView;", "getOriginal", "()Landroidx/compose/runtime/Composition;", "disposed", "", "addedToLifecycle", "Landroidx/lifecycle/Lifecycle;", "lastContent", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "setContent", "content", "(Lkotlin/jvm/functions/Function2;)V", "dispose", "hasInvalidations", "getHasInvalidations", "()Z", "isDisposed", "onStateChanged", "source", "Landroidx/lifecycle/LifecycleOwner;", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", "getCompositionService", "T", "key", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class WrappedComposition implements Composition, LifecycleEventObserver, CompositionServices {
    private Lifecycle addedToLifecycle;
    private boolean disposed;
    private Function2<? super Composer, ? super Integer, Unit> lastContent = ComposableSingletons$Wrapper_androidKt.INSTANCE.m7248getLambda$1759434350$ui();
    private final Composition original;
    private final AndroidComposeView owner;

    public WrappedComposition(AndroidComposeView owner, Composition original) {
        this.owner = owner;
        this.original = original;
    }

    public final Composition getOriginal() {
        return this.original;
    }

    public final AndroidComposeView getOwner() {
        return this.owner;
    }

    /* JADX INFO: renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1 */
    /* JADX INFO: compiled from: Wrapper.android.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "composeViewContext", "Landroidx/compose/ui/platform/ComposeViewContext;", "invoke"}, k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function1<ComposeViewContext, Unit> {
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(Function2<? super Composer, ? super Integer, Unit> function2) {
            super(1);
            this.$content = function2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(ComposeViewContext composeViewContext) {
            invoke2(composeViewContext);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke */
        public final void invoke2(ComposeViewContext composeViewContext) {
            if (!WrappedComposition.this.disposed) {
                final Lifecycle lifecycle = composeViewContext.getLifecycleOwner().getLifecycleRegistry();
                WrappedComposition.this.lastContent = this.$content;
                if (WrappedComposition.this.addedToLifecycle == null) {
                    if (Intrinsics.areEqual(Looper.myLooper(), composeViewContext.getView().getHandler().getLooper())) {
                        WrappedComposition.this.addedToLifecycle = lifecycle;
                        lifecycle.addObserver(WrappedComposition.this);
                        return;
                    } else {
                        View view = composeViewContext.getView();
                        final WrappedComposition wrappedComposition = WrappedComposition.this;
                        view.post(new Runnable() { // from class: androidx.compose.ui.platform.WrappedComposition$setContent$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                WrappedComposition.AnonymousClass1.invoke$lambda$0(wrappedComposition, lifecycle);
                            }
                        });
                        return;
                    }
                }
                if (lifecycle.getState().isAtLeast(Lifecycle.State.CREATED)) {
                    WrappedComposition.this.getOriginal().setContent(ComposableLambdaKt.composableLambdaInstance(-1723985096, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.platform.WrappedComposition.setContent.1.2
                        final /* synthetic */ ComposeViewContext $composeViewContext;
                        final /* synthetic */ Function2<Composer, Integer, Unit> $content;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        AnonymousClass2(ComposeViewContext composeViewContext2, Function2<? super Composer, ? super Integer, Unit> function2) {
                            super(2);
                            composeViewContext = composeViewContext2;
                            function2 = function2;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer, int $changed) {
                            ComposerKt.sourceInformation($composer, "C126@5432L47,126@5410L69,127@5526L48,127@5504L70,129@5619L40:Wrapper.android.kt#itgzvw");
                            if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                                $composer.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1723985096, $changed, -1, "androidx.compose.ui.platform.WrappedComposition.setContent.<anonymous>.<anonymous> (Wrapper.android.kt:126)");
                            }
                            AndroidComposeView owner = wrappedComposition.getOwner();
                            ComposerKt.sourceInformationMarkerStart($composer, 1107703815, "CC(remember):Wrapper.android.kt#9igjgp");
                            boolean invalid$iv = $composer.changedInstance(wrappedComposition);
                            WrappedComposition wrappedComposition2 = wrappedComposition;
                            Object it$iv = $composer.rememberedValue();
                            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                Object value$iv = (Function2) new WrappedComposition$setContent$1$2$1$1(wrappedComposition2, null);
                                $composer.updateRememberedValue(value$iv);
                                it$iv = value$iv;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            EffectsKt.LaunchedEffect(owner, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv, $composer, 0);
                            AndroidComposeView owner2 = wrappedComposition.getOwner();
                            ComposerKt.sourceInformationMarkerStart($composer, 1107706824, "CC(remember):Wrapper.android.kt#9igjgp");
                            boolean invalid$iv2 = $composer.changedInstance(wrappedComposition);
                            WrappedComposition wrappedComposition3 = wrappedComposition;
                            Object it$iv2 = $composer.rememberedValue();
                            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                Object value$iv2 = (Function2) new WrappedComposition$setContent$1$2$2$1(wrappedComposition3, null);
                                $composer.updateRememberedValue(value$iv2);
                                it$iv2 = value$iv2;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            EffectsKt.LaunchedEffect(owner2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer, 0);
                            composeViewContext.ProvideCompositionLocals$ui(wrappedComposition.getOwner(), function2, $composer, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }));
                }
            }
        }

        static final void invoke$lambda$0(WrappedComposition this$0, Lifecycle $lifecycle) {
            if (!this$0.disposed) {
                this$0.addedToLifecycle = $lifecycle;
                $lifecycle.addObserver(this$0);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$2 */
        /* JADX INFO: compiled from: Wrapper.android.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {2, 1, 0}, xi = 48)
        static final class AnonymousClass2 extends Lambda implements Function2<Composer, Integer, Unit> {
            final /* synthetic */ ComposeViewContext $composeViewContext;
            final /* synthetic */ Function2<Composer, Integer, Unit> $content;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(ComposeViewContext composeViewContext2, Function2<? super Composer, ? super Integer, Unit> function2) {
                super(2);
                composeViewContext = composeViewContext2;
                function2 = function2;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C126@5432L47,126@5410L69,127@5526L48,127@5504L70,129@5619L40:Wrapper.android.kt#itgzvw");
                if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
                    $composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1723985096, $changed, -1, "androidx.compose.ui.platform.WrappedComposition.setContent.<anonymous>.<anonymous> (Wrapper.android.kt:126)");
                }
                AndroidComposeView owner = wrappedComposition.getOwner();
                ComposerKt.sourceInformationMarkerStart($composer, 1107703815, "CC(remember):Wrapper.android.kt#9igjgp");
                boolean invalid$iv = $composer.changedInstance(wrappedComposition);
                WrappedComposition wrappedComposition2 = wrappedComposition;
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    Object value$iv = (Function2) new WrappedComposition$setContent$1$2$1$1(wrappedComposition2, null);
                    $composer.updateRememberedValue(value$iv);
                    it$iv = value$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                EffectsKt.LaunchedEffect(owner, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv, $composer, 0);
                AndroidComposeView owner2 = wrappedComposition.getOwner();
                ComposerKt.sourceInformationMarkerStart($composer, 1107706824, "CC(remember):Wrapper.android.kt#9igjgp");
                boolean invalid$iv2 = $composer.changedInstance(wrappedComposition);
                WrappedComposition wrappedComposition3 = wrappedComposition;
                Object it$iv2 = $composer.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    Object value$iv2 = (Function2) new WrappedComposition$setContent$1$2$2$1(wrappedComposition3, null);
                    $composer.updateRememberedValue(value$iv2);
                    it$iv2 = value$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                EffectsKt.LaunchedEffect(owner2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) it$iv2, $composer, 0);
                composeViewContext.ProvideCompositionLocals$ui(wrappedComposition.getOwner(), function2, $composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        this.owner.setOnReadyForComposition(new AnonymousClass1(content));
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.owner.getView().setTag(R.id.wrapped_composition_tag, null);
            Lifecycle lifecycle = this.addedToLifecycle;
            if (lifecycle != null) {
                lifecycle.removeObserver(this);
            }
            this.addedToLifecycle = null;
        }
        this.original.dispose();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        return this.original.getHasInvalidations();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean isDisposed() {
        return this.original.isDisposed();
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            dispose();
        } else if (event == Lifecycle.Event.ON_CREATE && !this.disposed) {
            setContent(this.lastContent);
        }
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        Composition composition = this.original;
        CompositionServices compositionServices = composition instanceof CompositionServices ? (CompositionServices) composition : null;
        if (compositionServices != null) {
            return (T) compositionServices.getCompositionService(key);
        }
        return null;
    }
}
