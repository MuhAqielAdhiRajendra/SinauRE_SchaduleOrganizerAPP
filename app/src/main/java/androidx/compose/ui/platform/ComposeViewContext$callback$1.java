package androidx.compose.ui.platform;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.view.ViewTreeObserver;
import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX INFO: compiled from: ComposeViewContext.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0017J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"androidx/compose/ui/platform/ComposeViewContext$callback$1", "Landroid/content/ComponentCallbacks2;", "Landroid/view/ViewTreeObserver$OnWindowFocusChangeListener;", "onConfigurationChanged", "", "configuration", "Landroid/content/res/Configuration;", "onLowMemory", "onTrimMemory", "level", "", "onWindowFocusChanged", "hasFocus", "", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ComposeViewContext$callback$1 implements ComponentCallbacks2, ViewTreeObserver.OnWindowFocusChangeListener {
    final /* synthetic */ ComposeViewContext this$0;

    ComposeViewContext$callback$1(ComposeViewContext $receiver) {
        this.this$0 = $receiver;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.this$0.onConfigurationChanged$ui(configuration);
    }

    @Override // android.content.ComponentCallbacks
    @Deprecated(message = "This callback is superseded by onTrimMemory")
    public void onLowMemory() {
        this.this$0.getImageVectorCache().clear();
        this.this$0.getResourceIdCache().clear();
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int level) {
        this.this$0.getImageVectorCache().clear();
        this.this$0.getResourceIdCache().clear();
    }

    @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
    public void onWindowFocusChanged(boolean hasFocus) {
        this.this$0.getWindowInfo().setWindowFocused(hasFocus);
    }
}
