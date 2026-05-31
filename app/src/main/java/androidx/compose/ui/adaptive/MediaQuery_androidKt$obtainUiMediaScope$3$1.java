package androidx.compose.ui.adaptive;

import android.view.View;
import android.view.ViewTreeObserver;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.core.view.ViewCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: MediaQuery.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/runtime/DisposableEffectResult;", "Landroidx/compose/runtime/DisposableEffectScope;", "invoke"}, k = 3, mv = {2, 1, 0}, xi = 48)
final class MediaQuery_androidKt$obtainUiMediaScope$3$1 extends Lambda implements Function1<DisposableEffectScope, DisposableEffectResult> {
    final /* synthetic */ UiMediaScopeImpl $scope;
    final /* synthetic */ View $view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MediaQuery_androidKt$obtainUiMediaScope$3$1(View view, UiMediaScopeImpl uiMediaScopeImpl) {
        super(1);
        this.$view = view;
        this.$scope = uiMediaScopeImpl;
    }

    @Override // kotlin.jvm.functions.Function1
    public final DisposableEffectResult invoke(DisposableEffectScope $this$DisposableEffect) {
        final UiMediaScopeImpl uiMediaScopeImpl = this.$scope;
        final View view = this.$view;
        final ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$3$1$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                uiMediaScopeImpl.setImeVisible(MediaQuery_androidKt.isImeVisible(ViewCompat.getRootWindowInsets(view)));
            }
        };
        this.$view.getViewTreeObserver().addOnGlobalLayoutListener(listener);
        final View view2 = this.$view;
        return new DisposableEffectResult() { // from class: androidx.compose.ui.adaptive.MediaQuery_androidKt$obtainUiMediaScope$3$1$invoke$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                view2.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
            }
        };
    }
}
