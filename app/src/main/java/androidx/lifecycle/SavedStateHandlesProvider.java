package androidx.lifecycle;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SavedStateHandleSupport.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\f\u0010\u0013\u001a\u00060\u000bj\u0002`\fH\u0016J\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\f2\u0006\u0010\u0017\u001a\u00020\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0018\u00010\u000bj\u0004\u0018\u0001`\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0019"}, d2 = {"Landroidx/lifecycle/SavedStateHandlesProvider;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "<init>", "(Landroidx/savedstate/SavedStateRegistry;Landroidx/lifecycle/ViewModelStoreOwner;)V", "restored", "", "restoredState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "viewModel", "Landroidx/lifecycle/SavedStateHandlesVM;", "getViewModel", "()Landroidx/lifecycle/SavedStateHandlesVM;", "viewModel$delegate", "Lkotlin/Lazy;", "saveState", "performRestore", "", "consumeRestoredStateForKey", "key", "", "lifecycle-viewmodel-savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SavedStateHandlesProvider implements SavedStateRegistry.SavedStateProvider {
    private boolean restored;
    private Bundle restoredState;
    private final SavedStateRegistry savedStateRegistry;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    public SavedStateHandlesProvider(SavedStateRegistry savedStateRegistry, final ViewModelStoreOwner viewModelStoreOwner) {
        Intrinsics.checkNotNullParameter(savedStateRegistry, "savedStateRegistry");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner, "viewModelStoreOwner");
        this.savedStateRegistry = savedStateRegistry;
        this.viewModel = LazyKt.lazy(new Function0() { // from class: androidx.lifecycle.SavedStateHandlesProvider$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return SavedStateHandleSupport.getSavedStateHandlesVM(viewModelStoreOwner);
            }
        });
    }

    private final SavedStateHandlesVM getViewModel() {
        return (SavedStateHandlesVM) this.viewModel.getValue();
    }

    @Override // androidx.savedstate.SavedStateRegistry.SavedStateProvider
    public Bundle saveState() {
        Pair[] pairs$iv;
        Map initialState$iv = MapsKt.emptyMap();
        if (initialState$iv.isEmpty()) {
            pairs$iv = new Pair[0];
        } else {
            Collection destination$iv$iv$iv = new ArrayList(initialState$iv.size());
            for (Map.Entry item$iv$iv$iv : initialState$iv.entrySet()) {
                String key$iv = (String) item$iv$iv$iv.getKey();
                Object value$iv = item$iv$iv$iv.getValue();
                destination$iv$iv$iv.add(TuplesKt.to(key$iv, value$iv));
            }
            Collection $this$toTypedArray$iv$iv = (List) destination$iv$iv$iv;
            pairs$iv = (Pair[]) $this$toTypedArray$iv$iv.toArray(new Pair[0]);
        }
        Bundle $this$savedState_u24lambda_u241$iv = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairs$iv, pairs$iv.length));
        Bundle $this$saveState_u24lambda_u240 = SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u241$iv);
        Bundle it = this.restoredState;
        if (it != null) {
            SavedStateWriter.m8612putAllimpl($this$saveState_u24lambda_u240, it);
        }
        for (Map.Entry<String, SavedStateHandle> entry : getViewModel().getHandles().entrySet()) {
            String key = entry.getKey();
            SavedStateHandle handle = entry.getValue();
            Map initialState$iv2 = initialState$iv;
            Bundle savedState = handle.savedStateProvider().saveState();
            Bundle $this$saveState_u24lambda_u240_u241_u240 = SavedStateReader.m8522constructorimpl(savedState);
            if (!SavedStateReader.m8600isEmptyimpl($this$saveState_u24lambda_u240_u241_u240)) {
                SavedStateWriter.m8635putSavedStateimpl($this$saveState_u24lambda_u240, key, savedState);
            }
            initialState$iv = initialState$iv2;
        }
        this.restored = false;
        return $this$savedState_u24lambda_u241$iv;
    }

    public final void performRestore() {
        Pair[] pairs$iv;
        if (!this.restored) {
            Bundle newState = this.savedStateRegistry.consumeRestoredStateForKey(SavedStateHandleSupport.SAVED_STATE_KEY);
            Map initialState$iv = MapsKt.emptyMap();
            if (initialState$iv.isEmpty()) {
                pairs$iv = new Pair[0];
            } else {
                Collection destination$iv$iv$iv = new ArrayList(initialState$iv.size());
                for (Map.Entry item$iv$iv$iv : initialState$iv.entrySet()) {
                    String key$iv = (String) item$iv$iv$iv.getKey();
                    Object value$iv = item$iv$iv$iv.getValue();
                    destination$iv$iv$iv.add(TuplesKt.to(key$iv, value$iv));
                }
                Collection $this$toTypedArray$iv$iv = (List) destination$iv$iv$iv;
                pairs$iv = (Pair[]) $this$toTypedArray$iv$iv.toArray(new Pair[0]);
            }
            Bundle $this$savedState_u24lambda_u241$iv = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairs$iv, pairs$iv.length));
            Bundle $this$performRestore_u24lambda_u240 = SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u241$iv);
            Bundle it = this.restoredState;
            if (it != null) {
                SavedStateWriter.m8612putAllimpl($this$performRestore_u24lambda_u240, it);
            }
            if (newState != null) {
                SavedStateWriter.m8612putAllimpl($this$performRestore_u24lambda_u240, newState);
            }
            this.restoredState = $this$savedState_u24lambda_u241$iv;
            this.restored = true;
            getViewModel();
        }
    }

    public final Bundle consumeRestoredStateForKey(String key) {
        Pair[] pairs$iv;
        Intrinsics.checkNotNullParameter(key, "key");
        performRestore();
        Bundle state = this.restoredState;
        if (state == null) {
            return null;
        }
        Bundle $this$consumeRestoredStateForKey_u24lambda_u240 = SavedStateReader.m8522constructorimpl(state);
        if (!SavedStateReader.m8523containsimpl($this$consumeRestoredStateForKey_u24lambda_u240, key)) {
            return null;
        }
        Bundle $this$consumeRestoredStateForKey_u24lambda_u241 = SavedStateReader.m8522constructorimpl(state);
        Bundle result = SavedStateReader.m8584getSavedStateOrNullimpl($this$consumeRestoredStateForKey_u24lambda_u241, key);
        if (result == null) {
            Map initialState$iv = MapsKt.emptyMap();
            if (initialState$iv.isEmpty()) {
                pairs$iv = new Pair[0];
            } else {
                Collection destination$iv$iv$iv = new ArrayList(initialState$iv.size());
                for (Map.Entry item$iv$iv$iv : initialState$iv.entrySet()) {
                    String key$iv = (String) item$iv$iv$iv.getKey();
                    Object value$iv = item$iv$iv$iv.getValue();
                    destination$iv$iv$iv.add(TuplesKt.to(key$iv, value$iv));
                }
                Collection $this$toTypedArray$iv$iv = (List) destination$iv$iv$iv;
                pairs$iv = (Pair[]) $this$toTypedArray$iv$iv.toArray(new Pair[0]);
            }
            Bundle $this$savedState_u24lambda_u241$iv = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairs$iv, pairs$iv.length));
            SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u241$iv);
            result = $this$savedState_u24lambda_u241$iv;
        }
        Bundle $this$consumeRestoredStateForKey_u24lambda_u242 = SavedStateWriter.m8608constructorimpl(state);
        SavedStateWriter.m8644removeimpl($this$consumeRestoredStateForKey_u24lambda_u242, key);
        Bundle $this$consumeRestoredStateForKey_u24lambda_u243 = SavedStateReader.m8522constructorimpl(state);
        if (SavedStateReader.m8600isEmptyimpl($this$consumeRestoredStateForKey_u24lambda_u243)) {
            this.restoredState = null;
        }
        return result;
    }
}
