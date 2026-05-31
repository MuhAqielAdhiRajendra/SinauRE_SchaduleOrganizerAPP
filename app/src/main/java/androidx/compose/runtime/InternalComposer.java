package androidx.compose.runtime;

import androidx.collection.MutableScatterMap;
import androidx.compose.runtime.tooling.ComposeStackTrace;
import androidx.compose.runtime.tooling.ComposeStackTraceFrame;
import androidx.compose.runtime.tooling.CompositionErrorContextImpl;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Composer.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\r\u0010\u001a\u001a\u00020\u001bH ¢\u0006\u0002\b\u001cJ\r\u0010\u001d\u001a\u00020\u001bH ¢\u0006\u0002\b\u001eJ\r\u0010\u001f\u001a\u00020\u001bH ¢\u0006\u0002\b J\r\u0010!\u001a\u00020\u0005H ¢\u0006\u0002\b\"J\r\u0010#\u001a\u00020\u001bH ¢\u0006\u0002\b$J\r\u0010%\u001a\u00020\u001bH ¢\u0006\u0002\b&J\r\u0010'\u001a\u00020\u001bH ¢\u0006\u0002\b(J\r\u0010)\u001a\u00020*H ¢\u0006\u0002\b+J\u0017\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/H ¢\u0006\u0002\b0J\u0013\u00101\u001a\b\u0012\u0004\u0012\u00020302H ¢\u0006\u0002\b4J\u001b\u00105\u001a\u00020\u001b2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001b07H ¢\u0006\u0002\b8J@\u00109\u001a\u00020\u001b2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020/0;2\u0011\u0010<\u001a\r\u0012\u0004\u0012\u00020\u001b07¢\u0006\u0002\b=2\b\u0010>\u001a\u0004\u0018\u00010?H ¢\u0006\u0004\b@\u0010AJ-\u0010B\u001a\u00020\u00052\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020/0;2\b\u0010>\u001a\u0004\u0018\u00010?H ¢\u0006\u0004\bC\u0010DJ\u001f\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\r2\b\u0010G\u001a\u0004\u0018\u00010/H ¢\u0006\u0002\bHJ#\u0010I\u001a\u00020\u001b2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020/0;H ¢\u0006\u0004\bJ\u0010KJ\r\u0010L\u001a\u00020*H!¢\u0006\u0002\bMR\u0012\u0010\u0004\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0012\u0010\n\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0007R\u0014\u0010\f\u001a\u0004\u0018\u00010\rX \u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0011X \u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u0004\u0018\u00010\u0015X \u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0012\u0010\u0018\u001a\u00020\u0005X \u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0007¨\u0006N"}, d2 = {"Landroidx/compose/runtime/InternalComposer;", "Landroidx/compose/runtime/Composer;", "<init>", "()V", "areChildrenComposing", "", "getAreChildrenComposing$runtime", "()Z", "isComposing", "isComposing$runtime", "hasPendingChanges", "getHasPendingChanges$runtime", "currentRecomposeScope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getCurrentRecomposeScope$runtime", "()Landroidx/compose/runtime/RecomposeScopeImpl;", "errorContext", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "getErrorContext$runtime", "()Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "deferredChanges", "Landroidx/compose/runtime/Changes;", "getDeferredChanges$runtime", "()Landroidx/compose/runtime/Changes;", "sourceMarkersEnabled", "getSourceMarkersEnabled$runtime", "startReuseFromRoot", "", "startReuseFromRoot$runtime", "endReuseFromRoot", "endReuseFromRoot$runtime", "changesApplied", "changesApplied$runtime", "forceRecomposeScopes", "forceRecomposeScopes$runtime", "dispose", "dispose$runtime", "deactivate", "deactivate$runtime", "verifyConsistent", "verifyConsistent$runtime", "stacksSize", "", "stacksSize$runtime", "stackTraceForValue", "Landroidx/compose/runtime/tooling/ComposeStackTrace;", "value", "", "stackTraceForValue$runtime", "parentStackTrace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "parentStackTrace$runtime", "prepareCompose", "block", "Lkotlin/Function0;", "prepareCompose$runtime", "composeContent", "invalidationsRequested", "Landroidx/compose/runtime/collection/ScopeMap;", "content", "Landroidx/compose/runtime/Composable;", "shouldPause", "Landroidx/compose/runtime/ShouldPauseCallback;", "composeContent--ZbOJvo$runtime", "(Landroidx/collection/MutableScatterMap;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/ShouldPauseCallback;)V", "recompose", "recompose-aFTiNEg$runtime", "(Landroidx/collection/MutableScatterMap;Landroidx/compose/runtime/ShouldPauseCallback;)Z", "tryImminentInvalidation", "scope", "instance", "tryImminentInvalidation$runtime", "updateComposerInvalidations", "updateComposerInvalidations-RY85e9Y$runtime", "(Landroidx/collection/MutableScatterMap;)V", "parentKey", "parentKey$runtime", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class InternalComposer implements Composer {
    public static final int $stable = 0;

    public abstract void changesApplied$runtime();

    /* JADX INFO: renamed from: composeContent--ZbOJvo$runtime */
    public abstract void mo4394composeContentZbOJvo$runtime(MutableScatterMap<Object, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content, ShouldPauseCallback shouldPause);

    public abstract void deactivate$runtime();

    public abstract void dispose$runtime();

    public abstract void endReuseFromRoot$runtime();

    public abstract boolean forceRecomposeScopes$runtime();

    public abstract boolean getAreChildrenComposing$runtime();

    public abstract RecomposeScopeImpl getCurrentRecomposeScope$runtime();

    public abstract Changes getDeferredChanges$runtime();

    public abstract CompositionErrorContextImpl getErrorContext$runtime();

    public abstract boolean getHasPendingChanges$runtime();

    public abstract boolean getSourceMarkersEnabled$runtime();

    public abstract boolean isComposing$runtime();

    public abstract int parentKey$runtime();

    public abstract List<ComposeStackTraceFrame> parentStackTrace$runtime();

    public abstract void prepareCompose$runtime(Function0<Unit> block);

    /* JADX INFO: renamed from: recompose-aFTiNEg$runtime */
    public abstract boolean mo4395recomposeaFTiNEg$runtime(MutableScatterMap<Object, Object> invalidationsRequested, ShouldPauseCallback shouldPause);

    public abstract ComposeStackTrace stackTraceForValue$runtime(Object value);

    public abstract int stacksSize$runtime();

    public abstract void startReuseFromRoot$runtime();

    public abstract boolean tryImminentInvalidation$runtime(RecomposeScopeImpl scope, Object instance);

    /* JADX INFO: renamed from: updateComposerInvalidations-RY85e9Y$runtime */
    public abstract void mo4396updateComposerInvalidationsRY85e9Y$runtime(MutableScatterMap<Object, Object> invalidationsRequested);

    public abstract void verifyConsistent$runtime();
}
