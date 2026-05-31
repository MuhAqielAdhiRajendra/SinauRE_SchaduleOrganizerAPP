package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidDragAndDropSource.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/draganddrop/CacheDrawScopeDragShadowCallback;", "", "<init>", "()V", "graphicsLayer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "drawDragShadow", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "cachePicture", "Landroidx/compose/ui/draw/DrawResult;", "scope", "Landroidx/compose/ui/draw/CacheDrawScope;", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CacheDrawScopeDragShadowCallback {
    public static final int $stable = 8;
    private GraphicsLayer graphicsLayer;

    public final void drawDragShadow(DrawScope drawScope) {
        GraphicsLayer layer = this.graphicsLayer;
        if (layer == null) {
            throw new IllegalArgumentException("No cached drag shadow. Check if the drag source node was rendered first");
        }
        GraphicsLayerKt.drawLayer(drawScope, layer);
    }

    public final DrawResult cachePicture(CacheDrawScope scope) {
        GraphicsLayer $this$cachePicture_u24lambda_u240_u240 = scope.obtainGraphicsLayer();
        scope.m4849recordTdoYBX4($this$cachePicture_u24lambda_u240_u240, (7 & 1) != 0 ? scope : null, (7 & 2) != 0 ? scope.getLayoutDirection() : null, (7 & 4) != 0 ? IntSizeKt.m8332toIntSizeuvyYCjk(scope.m4848getSizeNHjbRc()) : 0L, new Function1() { // from class: androidx.compose.foundation.draganddrop.CacheDrawScopeDragShadowCallback$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CacheDrawScopeDragShadowCallback.cachePicture$lambda$0$0$0((ContentDrawScope) obj);
            }
        });
        this.graphicsLayer = $this$cachePicture_u24lambda_u240_u240;
        return scope.onDrawWithContent(new Function1() { // from class: androidx.compose.foundation.draganddrop.CacheDrawScopeDragShadowCallback$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CacheDrawScopeDragShadowCallback.cachePicture$lambda$0$1(this.f$0, (ContentDrawScope) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cachePicture$lambda$0$0$0(ContentDrawScope $this$record) {
        $this$record.drawContent();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit cachePicture$lambda$0$1(CacheDrawScopeDragShadowCallback this$0, ContentDrawScope $this$onDrawWithContent) {
        GraphicsLayer graphicsLayer = this$0.graphicsLayer;
        Intrinsics.checkNotNull(graphicsLayer);
        GraphicsLayerKt.drawLayer($this$onDrawWithContent, graphicsLayer);
        return Unit.INSTANCE;
    }
}
