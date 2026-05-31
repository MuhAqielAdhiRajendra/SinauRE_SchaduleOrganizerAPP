package androidx.compose.ui.spatial;

import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: RectListDebugger.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\f\u0010\u0010\u001a\u00020\u000e*\u00020\u0011H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/spatial/RectListDebuggerModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "<init>", "()V", "paint", "Landroid/graphics/Paint;", "token", "", "getToken", "()Ljava/lang/Object;", "setToken", "(Ljava/lang/Object;)V", "onAttach", "", "onDetach", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class RectListDebuggerModifierNode extends Modifier.Node implements DrawModifierNode {
    private Paint paint;
    private Object token;

    public RectListDebuggerModifierNode() {
        androidx.compose.ui.graphics.Paint it = AndroidPaint_androidKt.Paint();
        it.mo5189setColor8_81llA(Color.INSTANCE.m5347getRed0d7_KjU());
        it.mo5193setStylek9PVt8s(PaintingStyle.INSTANCE.m5595getStrokeTiuSbCo());
        this.paint = AndroidPaint_androidKt.getNativePaint(it);
    }

    public final Object getToken() {
        return this.token;
    }

    public final void setToken(Object obj) {
        this.token = obj;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.token = DelegatableNodeKt.requireOwner(this).getRectManager().registerOnChangedCallback(new Function0<Unit>() { // from class: androidx.compose.ui.spatial.RectListDebuggerModifierNode.onAttach.1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                DrawModifierNodeKt.invalidateDraw(RectListDebuggerModifierNode.this);
            }
        });
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        DelegatableNodeKt.requireOwner(this).getRectManager().unregisterOnChangedCallback(this.token);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        RectList rectList = DelegatableNodeKt.requireOwner(this).getRectManager().getRects();
        Canvas canvas = AndroidCanvas_androidKt.getNativeCanvas($this$draw.getDrawContext().getCanvas());
        Paint paint = this.paint;
        long[] items$iv = rectList.items;
        int size$iv = rectList.itemsSize;
        int i$iv = 0;
        while (i$iv < items$iv.length - 2 && i$iv < size$iv) {
            long topLeft$iv = items$iv[i$iv + 0];
            long bottomRight$iv = items$iv[i$iv + 1];
            long j = items$iv[i$iv + 2];
            int l = (int) (topLeft$iv >> 32);
            int t = (int) topLeft$iv;
            RectList rectList2 = rectList;
            int r = (int) (bottomRight$iv >> 32);
            int $i$f$unpackY = (int) bottomRight$iv;
            paint = paint;
            canvas.drawRect(l, t, r, $i$f$unpackY, paint);
            i$iv += 3;
            rectList = rectList2;
        }
    }
}
