package androidx.compose.runtime.internal;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.ComposeRuntimeFlags;
import androidx.compose.runtime.GapRememberObserverHolder;
import androidx.compose.runtime.LinkRememberObserverHolder;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.RememberObserverHolder;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.composer.linkbuffer.LinkAnchorKt;
import androidx.compose.runtime.tooling.CompositionErrorContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: RememberEventDispatcher.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u001f\u001a\u00020\u00132\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ:\u0010 \u001a\u00020\u00132\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0017\u0010!\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00130\"¢\u0006\u0002\b#H\u0086\bJ\u0006\u0010$\u001a\u00020\u0013J\u0010\u0010\t\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u000bH\u0016J\u0010\u0010&\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u000bH\u0016J\u0016\u0010'\u001a\u00020\u00132\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016J\u0010\u0010)\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0015H\u0016J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0015H\u0016J\u0010\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u0018H\u0016J\u0010\u0010,\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u0018H\u0016J\u0010\u0010-\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u0018H\u0016J\u0006\u0010.\u001a\u00020\u0013J\u000e\u0010/\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0015J\u0014\u00100\u001a\u00020\u00132\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001eJ\u000e\u00102\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001eJ\u0016\u00103\u001a\u00020\u00132\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002J\u0006\u00105\u001a\u00020\u0013J\u0006\u00106\u001a\u00020\u0013J\u0010\u00107\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0010H\u0002J*\u00108\u001a\u0002H9\"\u0004\b\u0000\u001092\u0006\u0010%\u001a\u00020\u00102\f\u0010!\u001a\b\u0012\u0004\u0012\u0002H90\u0012H\u0082\b¢\u0006\u0002\u0010:R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001a\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001cR\u0016\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006;"}, d2 = {"Landroidx/compose/runtime/internal/RememberEventDispatcher;", "Landroidx/compose/runtime/composer/RememberManager;", "<init>", "()V", "abandoning", "", "Landroidx/compose/runtime/RememberObserver;", "traceContext", "Landroidx/compose/runtime/tooling/CompositionErrorContext;", "remembering", "Landroidx/compose/runtime/collection/MutableVector;", "Landroidx/compose/runtime/RememberObserverHolder;", "rememberSet", "Landroidx/collection/MutableScatterSet;", "currentRememberingList", "leaving", "", "sideEffects", "Lkotlin/Function0;", "", "releasing", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "pausedPlaceholders", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/internal/PausedCompositionRemembers;", "nestedRemembersLists", "Landroidx/compose/runtime/Stack;", "Ljava/util/ArrayList;", "ignoreLeavingSet", "Landroidx/collection/ScatterSet;", "prepare", "use", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "clear", "instance", "forgetting", "sideEffect", "effect", "deactivating", "rememberPausingScope", "scope", "startResumingScope", "endResumingScope", "dispatchRememberObservers", "dispatchOnDeactivateIfNecessary", "ignoreForgotten", "ignoreSet", "extractRememberSet", "dispatchRememberList", "list", "dispatchSideEffects", "dispatchAbandons", "recordLeaving", "withComposeStackTrace", "T", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RememberEventDispatcher implements RememberManager {
    public static final int $stable = 8;
    private Set<RememberObserver> abandoning;
    private ScatterSet<RememberObserverHolder> ignoreLeavingSet;
    private ArrayList<MutableVector<RememberObserverHolder>> nestedRemembersLists;
    private MutableScatterMap<RecomposeScopeImpl, PausedCompositionRemembers> pausedPlaceholders;
    private MutableScatterSet<ComposeNodeLifecycleCallback> releasing;
    private CompositionErrorContext traceContext;
    private final MutableVector<RememberObserverHolder> remembering = new MutableVector<>(new RememberObserverHolder[16], 0);
    private MutableScatterSet<RememberObserverHolder> rememberSet = ScatterSetKt.mutableScatterSetOf();
    private MutableVector<RememberObserverHolder> currentRememberingList = this.remembering;
    private final MutableVector<Object> leaving = new MutableVector<>(new Object[16], 0);
    private final MutableVector<Function0<Unit>> sideEffects = new MutableVector<>(new Function0[16], 0);

    public final void prepare(Set<RememberObserver> abandoning, CompositionErrorContext traceContext) {
        clear();
        this.abandoning = abandoning;
        this.traceContext = traceContext;
    }

    public final void use(Set<RememberObserver> abandoning, CompositionErrorContext traceContext, Function1<? super RememberEventDispatcher, Unit> block) {
        try {
            prepare(abandoning, traceContext);
            block.invoke(this);
        } finally {
            clear();
        }
    }

    public final void clear() {
        this.abandoning = null;
        this.traceContext = null;
        this.remembering.clear();
        this.rememberSet.clear();
        this.currentRememberingList = this.remembering;
        this.leaving.clear();
        this.sideEffects.clear();
        this.releasing = null;
        this.pausedPlaceholders = null;
        this.nestedRemembersLists = null;
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void remembering(RememberObserverHolder instance) {
        this.currentRememberingList.add(instance);
        this.rememberSet.add(instance);
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void forgetting(RememberObserverHolder instance) {
        if (this.rememberSet.contains(instance)) {
            this.rememberSet.remove(instance);
            boolean removed = this.currentRememberingList.remove(instance) || this.remembering.remove(instance);
            if (!removed) {
                forgetting$removeFrom(instance, this.remembering);
            }
            Set<RememberObserver> set = this.abandoning;
            if (set == null) {
                return;
            }
            set.add(instance.getWrapped());
            return;
        }
        ScatterSet<RememberObserverHolder> scatterSet = this.ignoreLeavingSet;
        if (scatterSet == null || !scatterSet.contains(instance)) {
            recordLeaving(instance);
        }
    }

    private static final boolean forgetting$removeFrom(RememberObserverHolder $instance, MutableVector<RememberObserverHolder> mutableVector) {
        Object[] content$iv = mutableVector.content;
        int size$iv = mutableVector.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            RememberObserverHolder holder = (RememberObserverHolder) content$iv[i$iv];
            RememberObserver nested = holder.getWrapped();
            if (nested instanceof PausedCompositionRemembers) {
                MutableVector<RememberObserverHolder> pausedRemembers = ((PausedCompositionRemembers) nested).getPausedRemembers();
                if (pausedRemembers.remove($instance) || forgetting$removeFrom($instance, pausedRemembers)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void sideEffect(Function0<Unit> effect) {
        this.sideEffects.add(effect);
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void deactivating(ComposeNodeLifecycleCallback instance) {
        recordLeaving(instance);
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void releasing(ComposeNodeLifecycleCallback instance) {
        MutableScatterSet<ComposeNodeLifecycleCallback> mutableScatterSetMutableScatterSetOf = this.releasing;
        if (mutableScatterSetMutableScatterSetOf == null) {
            mutableScatterSetMutableScatterSetOf = ScatterSetKt.mutableScatterSetOf();
            this.releasing = mutableScatterSetMutableScatterSetOf;
        }
        mutableScatterSetMutableScatterSetOf.plusAssign(instance);
        recordLeaving(instance);
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void rememberPausingScope(RecomposeScopeImpl scope) {
        GapRememberObserverHolder gapRememberObserverHolder;
        Set<RememberObserver> set = this.abandoning;
        if (set == null) {
            return;
        }
        PausedCompositionRemembers pausedPlaceholder = new PausedCompositionRemembers(set);
        MutableScatterMap<RecomposeScopeImpl, PausedCompositionRemembers> mutableScatterMapMutableScatterMapOf = this.pausedPlaceholders;
        if (mutableScatterMapMutableScatterMapOf == null) {
            mutableScatterMapMutableScatterMapOf = ScatterMapKt.mutableScatterMapOf();
            this.pausedPlaceholders = mutableScatterMapMutableScatterMapOf;
        }
        mutableScatterMapMutableScatterMapOf.set(scope, pausedPlaceholder);
        MutableVector<RememberObserverHolder> mutableVector = this.currentRememberingList;
        if (ComposeRuntimeFlags.isLinkBufferComposerEnabled) {
            gapRememberObserverHolder = new LinkRememberObserverHolder(pausedPlaceholder, LinkAnchorKt.getNullAnchor());
        } else {
            gapRememberObserverHolder = new GapRememberObserverHolder(pausedPlaceholder, -1);
        }
        mutableVector.add(gapRememberObserverHolder);
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void startResumingScope(RecomposeScopeImpl scope) {
        MutableScatterMap<RecomposeScopeImpl, PausedCompositionRemembers> mutableScatterMap = this.pausedPlaceholders;
        PausedCompositionRemembers placeholder = mutableScatterMap != null ? mutableScatterMap.get(scope) : null;
        if (placeholder != null) {
            ArrayList<MutableVector<RememberObserverHolder>> arrayListM4418constructorimpl$default = this.nestedRemembersLists;
            if (arrayListM4418constructorimpl$default == null) {
                arrayListM4418constructorimpl$default = Stack.m4418constructorimpl$default(null, 1, null);
                this.nestedRemembersLists = arrayListM4418constructorimpl$default;
            }
            Stack.m4428pushimpl(arrayListM4418constructorimpl$default, this.currentRememberingList);
            this.currentRememberingList = placeholder.getPausedRemembers();
        }
    }

    @Override // androidx.compose.runtime.composer.RememberManager
    public void endResumingScope(RecomposeScopeImpl scope) {
        MutableVector<RememberObserverHolder> mutableVector;
        MutableScatterMap<RecomposeScopeImpl, PausedCompositionRemembers> mutableScatterMap = this.pausedPlaceholders;
        if (mutableScatterMap != null) {
            PausedCompositionRemembers placeholder = mutableScatterMap.get(scope);
            if (placeholder != null) {
                ArrayList<MutableVector<RememberObserverHolder>> arrayList = this.nestedRemembersLists;
                if (arrayList != null && (mutableVector = (MutableVector) Stack.m4427popimpl(arrayList)) != null) {
                    this.currentRememberingList = mutableVector;
                }
                mutableScatterMap.remove(scope);
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    public final void dispatchRememberObservers() {
        Object token$iv;
        Set<RememberObserver> set = this.abandoning;
        if (set == null) {
            return;
        }
        this.ignoreLeavingSet = null;
        if (this.leaving.getSize() != 0) {
            token$iv = Trace.INSTANCE.beginSection("Compose:onForgotten");
            try {
                MutableScatterSet releasing = this.releasing;
                for (int i = this.leaving.getSize() - 1; -1 < i; i--) {
                    int index$iv = i;
                    Object instance = this.leaving.content[index$iv];
                    try {
                        if (instance instanceof RememberObserverHolder) {
                            RememberObserver wrapped = ((RememberObserverHolder) instance).getWrapped();
                            set.remove(wrapped);
                            wrapped.onForgotten();
                        }
                        if (instance instanceof ComposeNodeLifecycleCallback) {
                            if (releasing != null && releasing.contains(instance)) {
                                ((ComposeNodeLifecycleCallback) instance).onRelease();
                            } else {
                                ((ComposeNodeLifecycleCallback) instance).onDeactivate();
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                    } catch (Throwable e$iv) {
                        CompositionErrorContext $this$withComposeStackTrace_u24lambda_u240_u240$iv = this.traceContext;
                        if ($this$withComposeStackTrace_u24lambda_u240_u240$iv != null) {
                            $this$withComposeStackTrace_u24lambda_u240_u240$iv.attachComposeStackTrace(e$iv, instance);
                        }
                        throw e$iv;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
        if (this.remembering.getSize() != 0) {
            token$iv = Trace.INSTANCE.beginSection("Compose:onRemembered");
            try {
                dispatchRememberList(this.remembering);
                Unit unit3 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    public final void dispatchOnDeactivateIfNecessary(ComposeNodeLifecycleCallback instance) {
        boolean removed = this.leaving.remove(instance);
        if (removed) {
            instance.onDeactivate();
        }
    }

    public final void ignoreForgotten(ScatterSet<RememberObserverHolder> ignoreSet) {
        this.ignoreLeavingSet = ignoreSet;
    }

    public final ScatterSet<RememberObserverHolder> extractRememberSet() {
        if (this.rememberSet.isNotEmpty()) {
            MutableScatterSet<RememberObserverHolder> mutableScatterSet = this.rememberSet;
            this.rememberSet = ScatterSetKt.mutableScatterSetOf();
            this.remembering.clear();
            return mutableScatterSet;
        }
        return null;
    }

    private final void dispatchRememberList(MutableVector<RememberObserverHolder> list) {
        Set<RememberObserver> set = this.abandoning;
        if (set == null) {
            return;
        }
        Object[] content$iv = list.content;
        int size$iv = list.getSize();
        for (int i$iv = 0; i$iv < size$iv; i$iv++) {
            RememberObserverHolder instance = (RememberObserverHolder) content$iv[i$iv];
            RememberObserver wrapped = instance.getWrapped();
            set.remove(wrapped);
            try {
                wrapped.onRemembered();
                Unit unit = Unit.INSTANCE;
            } catch (Throwable e$iv) {
                CompositionErrorContext $this$withComposeStackTrace_u24lambda_u240_u240$iv = this.traceContext;
                if ($this$withComposeStackTrace_u24lambda_u240_u240$iv != null) {
                    $this$withComposeStackTrace_u24lambda_u240_u240$iv.attachComposeStackTrace(e$iv, instance);
                }
                throw e$iv;
            }
        }
    }

    public final void dispatchSideEffects() {
        if (this.sideEffects.getSize() != 0) {
            Object token$iv = Trace.INSTANCE.beginSection("Compose:sideeffects");
            try {
                MutableVector<Function0<Unit>> mutableVector = this.sideEffects;
                Object[] content$iv = mutableVector.content;
                int size$iv = mutableVector.getSize();
                for (int i$iv = 0; i$iv < size$iv; i$iv++) {
                    ((Function0) content$iv[i$iv]).invoke();
                }
                this.sideEffects.clear();
                Unit unit = Unit.INSTANCE;
            } finally {
                Trace.INSTANCE.endSection(token$iv);
            }
        }
    }

    public final void dispatchAbandons() {
        Set<RememberObserver> set = this.abandoning;
        if (set != null && !set.isEmpty()) {
            Object token$iv = Trace.INSTANCE.beginSection("Compose:abandons");
            try {
                Iterator<RememberObserver> it = set.iterator();
                while (it.hasNext()) {
                    RememberObserver instance = it.next();
                    it.remove();
                    instance.onAbandoned();
                }
                Unit unit = Unit.INSTANCE;
            } finally {
                Trace.INSTANCE.endSection(token$iv);
            }
        }
    }

    private final void recordLeaving(Object instance) {
        this.leaving.add(instance);
    }

    private final <T> T withComposeStackTrace(Object instance, Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable e) {
            CompositionErrorContext $this$withComposeStackTrace_u24lambda_u240_u240 = this.traceContext;
            if ($this$withComposeStackTrace_u24lambda_u240_u240 != null) {
                $this$withComposeStackTrace_u24lambda_u240_u240.attachComposeStackTrace(e, instance);
            }
            throw e;
        }
    }
}
