package androidx.savedstate;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SavedState.android.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001aC\u0010\u0002\u001a\u00060\u0001j\u0002`\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00052\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0086\bø\u0001\u0000\u001a7\u0010\u0002\u001a\u00060\u0001j\u0002`\u00032\n\u0010\u0004\u001a\u00060\u0001j\u0002`\u00032\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0086\bø\u0001\u0000*\n\u0010\u0000\"\u00020\u00012\u00020\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"SavedState", "Landroid/os/Bundle;", "savedState", "Landroidx/savedstate/SavedState;", "initialState", "", "", "", "builderAction", "Lkotlin/Function1;", "Landroidx/savedstate/SavedStateWriter;", "", "Lkotlin/ExtensionFunctionType;", "savedstate"}, k = 5, mv = {2, 0, 0}, xi = 48, xs = "androidx/savedstate/SavedStateKt")
final /* synthetic */ class SavedStateKt__SavedState_androidKt {
    public static /* synthetic */ Bundle savedState$default(Map initialState, Function1 builderAction, int i, Object obj) {
        Pair[] pairs;
        if ((i & 1) != 0) {
            initialState = MapsKt.emptyMap();
        }
        if ((i & 2) != 0) {
            Function1 builderAction2 = new Function1<SavedStateWriter, Unit>() { // from class: androidx.savedstate.SavedStateKt__SavedState_androidKt.savedState.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SavedStateWriter savedStateWriter) {
                    m8519invokexApjlu4(savedStateWriter.getSource());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-xApjlu4, reason: not valid java name */
                public final void m8519invokexApjlu4(Bundle bundle) {
                    Intrinsics.checkNotNullParameter(bundle, "<this>");
                }
            };
            builderAction = builderAction2;
        }
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        if (initialState.isEmpty()) {
            pairs = new Pair[0];
        } else {
            Map $this$map$iv = initialState;
            Collection destination$iv$iv = new ArrayList($this$map$iv.size());
            for (Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
                String key = (String) item$iv$iv.getKey();
                Object value = item$iv$iv.getValue();
                destination$iv$iv.add(TuplesKt.to(key, value));
            }
            Collection $this$toTypedArray$iv = (List) destination$iv$iv;
            pairs = (Pair[]) $this$toTypedArray$iv.toArray(new Pair[0]);
        }
        Bundle $this$savedState_u24lambda_u241 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairs, pairs.length));
        builderAction.invoke(SavedStateWriter.m8606boximpl(SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u241)));
        return $this$savedState_u24lambda_u241;
    }

    public static final Bundle savedState(Map<String, ? extends Object> initialState, Function1<? super SavedStateWriter, Unit> builderAction) {
        Pair[] pairs;
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        if (initialState.isEmpty()) {
            pairs = new Pair[0];
        } else {
            Collection destination$iv$iv = new ArrayList(initialState.size());
            for (Map.Entry<String, ? extends Object> entry : initialState.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                destination$iv$iv.add(TuplesKt.to(key, value));
            }
            Collection $this$toTypedArray$iv = (List) destination$iv$iv;
            pairs = (Pair[]) $this$toTypedArray$iv.toArray(new Pair[0]);
        }
        Bundle $this$savedState_u24lambda_u241 = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairs, pairs.length));
        builderAction.invoke(SavedStateWriter.m8606boximpl(SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u241)));
        return $this$savedState_u24lambda_u241;
    }

    public static /* synthetic */ Bundle savedState$default(Bundle initialState, Function1 builderAction, int i, Object obj) {
        if ((i & 2) != 0) {
            Function1 builderAction2 = new Function1<SavedStateWriter, Unit>() { // from class: androidx.savedstate.SavedStateKt__SavedState_androidKt.savedState.3
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SavedStateWriter savedStateWriter) {
                    m8520invokexApjlu4(savedStateWriter.getSource());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-xApjlu4, reason: not valid java name */
                public final void m8520invokexApjlu4(Bundle bundle) {
                    Intrinsics.checkNotNullParameter(bundle, "<this>");
                }
            };
            builderAction = builderAction2;
        }
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        Bundle $this$savedState_u24lambda_u242 = new Bundle(initialState);
        builderAction.invoke(SavedStateWriter.m8606boximpl(SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u242)));
        return $this$savedState_u24lambda_u242;
    }

    public static final Bundle savedState(Bundle initialState, Function1<? super SavedStateWriter, Unit> builderAction) {
        Intrinsics.checkNotNullParameter(initialState, "initialState");
        Intrinsics.checkNotNullParameter(builderAction, "builderAction");
        Bundle $this$savedState_u24lambda_u242 = new Bundle(initialState);
        builderAction.invoke(SavedStateWriter.m8606boximpl(SavedStateWriter.m8608constructorimpl($this$savedState_u24lambda_u242)));
        return $this$savedState_u24lambda_u242;
    }
}
