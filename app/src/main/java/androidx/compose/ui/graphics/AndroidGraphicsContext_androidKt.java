package androidx.compose.ui.graphics;

import android.view.ViewGroup;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidGraphicsContext.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"}, d2 = {"GraphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "layerContainer", "Landroid/view/ViewGroup;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidGraphicsContext_androidKt {
    public static final GraphicsContext GraphicsContext(ViewGroup layerContainer) {
        return new AndroidGraphicsContext(layerContainer);
    }
}
