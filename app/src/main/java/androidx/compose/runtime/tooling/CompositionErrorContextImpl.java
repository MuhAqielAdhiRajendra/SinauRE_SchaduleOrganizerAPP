package androidx.compose.runtime.tooling;

import androidx.compose.runtime.InternalComposer;
import androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: CompositionErrorContext.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 \u001a2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\u001aB\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u001d\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016¢\u0006\u0002\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001b"}, d2 = {"Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "Landroidx/compose/runtime/tooling/CompositionErrorContext;", "Landroidx/compose/runtime/composer/gapbuffer/changelist/OperationErrorContext;", "Lkotlin/coroutines/CoroutineContext$Element;", "composer", "Landroidx/compose/runtime/InternalComposer;", "<init>", "(Landroidx/compose/runtime/InternalComposer;)V", "attachComposeStackTrace", "", "", "composeNode", "", "buildStackTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "currentOffset", "", "(Ljava/lang/Integer;)Ljava/util/List;", "sourceInformationEnabled", "getSourceInformationEnabled", "()Z", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "Key", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CompositionErrorContextImpl implements CompositionErrorContext, OperationErrorContext, CoroutineContext.Element {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final InternalComposer composer;

    public CompositionErrorContextImpl(InternalComposer composer) {
        this.composer = composer;
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public /* bridge */ <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return (R) CoroutineContext.Element.DefaultImpls.fold(this, r, function2);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public /* bridge */ <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key) {
        return (E) CoroutineContext.Element.DefaultImpls.get(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public /* bridge */ CoroutineContext minusKey(CoroutineContext.Key<?> key) {
        return CoroutineContext.Element.DefaultImpls.minusKey(this, key);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public /* bridge */ CoroutineContext plus(CoroutineContext context) {
        return CoroutineContext.Element.DefaultImpls.plus(this, context);
    }

    @Override // androidx.compose.runtime.tooling.CompositionErrorContext
    public boolean attachComposeStackTrace(Throwable $this$attachComposeStackTrace, final Object composeNode) {
        return ComposeStackTraceKt.tryAttachComposeStackTrace($this$attachComposeStackTrace, new Function0() { // from class: androidx.compose.runtime.tooling.CompositionErrorContextImpl$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.composer.stackTraceForValue$runtime(composeNode);
            }
        });
    }

    @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
    public List<ComposeStackTraceFrame> buildStackTrace(Integer currentOffset) {
        return this.composer.parentStackTrace$runtime();
    }

    @Override // androidx.compose.runtime.composer.gapbuffer.changelist.OperationErrorContext
    public boolean getSourceInformationEnabled() {
        return this.composer.getSourceMarkersEnabled$runtime();
    }

    /* JADX INFO: renamed from: androidx.compose.runtime.tooling.CompositionErrorContextImpl$Key, reason: from kotlin metadata */
    /* JADX INFO: compiled from: CompositionErrorContext.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/compose/runtime/tooling/CompositionErrorContextImpl$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "<init>", "()V", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion implements CoroutineContext.Key<CompositionErrorContextImpl> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public String toString() {
            return "CompositionErrorContext";
        }
    }

    @Override // kotlin.coroutines.CoroutineContext.Element
    public CoroutineContext.Key<?> getKey() {
        return INSTANCE;
    }
}
