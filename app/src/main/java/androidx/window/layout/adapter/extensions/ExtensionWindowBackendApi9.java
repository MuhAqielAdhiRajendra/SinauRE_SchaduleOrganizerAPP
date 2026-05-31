package androidx.window.layout.adapter.extensions;

import android.content.Context;
import androidx.window.core.ConsumerAdapter;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.WindowLayoutInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExtensionWindowBackendApi9.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0011\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi9;", "Landroidx/window/layout/adapter/extensions/ExtensionWindowBackendApi6;", "component", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "adapter", "Landroidx/window/core/ConsumerAdapter;", "<init>", "(Landroidx/window/extensions/layout/WindowLayoutComponent;Landroidx/window/core/ConsumerAdapter;)V", "getCurrentWindowLayoutInfo", "Landroidx/window/layout/WindowLayoutInfo;", "context", "Landroid/content/Context;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class ExtensionWindowBackendApi9 extends ExtensionWindowBackendApi6 {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExtensionWindowBackendApi9(WindowLayoutComponent component, ConsumerAdapter adapter) {
        super(component, adapter);
        Intrinsics.checkNotNullParameter(component, "component");
        Intrinsics.checkNotNullParameter(adapter, "adapter");
    }

    @Override // androidx.window.layout.adapter.extensions.ExtensionWindowBackendApi0, androidx.window.layout.adapter.WindowBackend
    public WindowLayoutInfo getCurrentWindowLayoutInfo(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ExtensionsWindowLayoutInfoAdapter extensionsWindowLayoutInfoAdapter = ExtensionsWindowLayoutInfoAdapter.INSTANCE;
        androidx.window.extensions.layout.WindowLayoutInfo currentWindowLayoutInfo = getComponent().getCurrentWindowLayoutInfo(context);
        Intrinsics.checkNotNullExpressionValue(currentWindowLayoutInfo, "getCurrentWindowLayoutInfo(...)");
        return extensionsWindowLayoutInfoAdapter.translate$window_release(context, currentWindowLayoutInfo);
    }
}
