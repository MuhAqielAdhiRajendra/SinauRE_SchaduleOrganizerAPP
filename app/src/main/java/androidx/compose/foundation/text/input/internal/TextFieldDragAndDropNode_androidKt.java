package androidx.compose.foundation.text.input.internal;

import android.view.DragEvent;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.content.MediaType;
import androidx.compose.ui.draganddrop.DragAndDropEvent;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTarget;
import androidx.compose.ui.draganddrop.DragAndDropTargetModifierNode;
import androidx.compose.ui.draganddrop.DragAndDrop_androidKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.AndroidClipboardManager_androidKt;
import androidx.compose.ui.platform.ClipEntry;
import androidx.compose.ui.platform.ClipMetadata;
import androidx.core.app.NotificationCompat;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: TextFieldDragAndDropNode.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aÒ\u0002\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\u00072\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102%\b\u0002\u0010\u0013\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u0015\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u0016\u001a\u001f\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u0019\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u001a\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00102%\b\u0002\u0010\u001b\u001a\u001f\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0010H\u0000¨\u0006\u001c"}, d2 = {"textFieldDragAndDropNode", "Landroidx/compose/ui/draganddrop/DragAndDropTargetModifierNode;", "hintMediaTypes", "Lkotlin/Function0;", "", "Landroidx/compose/foundation/content/MediaType;", "onDrop", "Lkotlin/Function2;", "Landroidx/compose/ui/platform/ClipEntry;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "clipEntry", "Landroidx/compose/ui/platform/ClipMetadata;", "clipMetadata", "", "dragAndDropRequestPermission", "Lkotlin/Function1;", "Landroidx/compose/ui/draganddrop/DragAndDropEvent;", "", "onStarted", NotificationCompat.CATEGORY_EVENT, "onEntered", "onMoved", "Landroidx/compose/ui/geometry/Offset;", "position", "onChanged", "onExited", "onEnded", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class TextFieldDragAndDropNode_androidKt {
    public static /* synthetic */ DragAndDropTargetModifierNode textFieldDragAndDropNode$default(Function0 function0, Function2 function2, Function1 function1, Function1 function12, Function1 function13, Function1 function14, Function1 function15, Function1 function16, Function1 function17, int i, Object obj) {
        if ((i & 8) != 0) {
            function12 = null;
        }
        if ((i & 16) != 0) {
            function13 = null;
        }
        if ((i & 32) != 0) {
            function14 = null;
        }
        if ((i & 64) != 0) {
            function15 = null;
        }
        if ((i & 128) != 0) {
            function16 = null;
        }
        if ((i & 256) != 0) {
            function17 = null;
        }
        return textFieldDragAndDropNode(function0, function2, function1, function12, function13, function14, function15, function16, function17);
    }

    public static final DragAndDropTargetModifierNode textFieldDragAndDropNode(final Function0<? extends Set<MediaType>> function0, final Function2<? super ClipEntry, ? super ClipMetadata, Boolean> function2, final Function1<? super DragAndDropEvent, Unit> function1, final Function1<? super DragAndDropEvent, Unit> function12, final Function1<? super DragAndDropEvent, Unit> function13, final Function1<? super Offset, Unit> function14, final Function1<? super DragAndDropEvent, Unit> function15, final Function1<? super DragAndDropEvent, Unit> function16, final Function1<? super DragAndDropEvent, Unit> function17) {
        return DragAndDropNodeKt.DragAndDropTargetModifierNode(new Function1() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDragAndDropNode_androidKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TextFieldDragAndDropNode_androidKt.textFieldDragAndDropNode$lambda$0(function0, (DragAndDropEvent) obj));
            }
        }, new DragAndDropTarget() { // from class: androidx.compose.foundation.text.input.internal.TextFieldDragAndDropNode_androidKt.textFieldDragAndDropNode.2
            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public boolean onDrop(DragAndDropEvent event) {
                function1.invoke(event);
                return function2.invoke(AndroidClipboardManager_androidKt.toClipEntry(DragAndDrop_androidKt.toAndroidDragEvent(event).getClipData()), AndroidClipboardManager_androidKt.toClipMetadata(DragAndDrop_androidKt.toAndroidDragEvent(event).getClipDescription())).booleanValue();
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onStarted(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function18 = function12;
                if (function18 != null) {
                    function18.invoke(event);
                }
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onEntered(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function18 = function13;
                if (function18 != null) {
                    function18.invoke(event);
                }
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onMoved(DragAndDropEvent event) {
                DragEvent $this$onMoved_u24lambda_u240 = DragAndDrop_androidKt.toAndroidDragEvent(event);
                Function1<Offset, Unit> function18 = function14;
                if (function18 != null) {
                    float x$iv = $this$onMoved_u24lambda_u240.getX();
                    float y$iv = $this$onMoved_u24lambda_u240.getY();
                    long v1$iv$iv = Float.floatToRawIntBits(x$iv);
                    long v2$iv$iv = Float.floatToRawIntBits(y$iv);
                    function18.invoke(Offset.m5057boximpl(Offset.m5060constructorimpl((v1$iv$iv << 32) | (v2$iv$iv & 4294967295L))));
                }
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onExited(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function18 = function16;
                if (function18 != null) {
                    function18.invoke(event);
                }
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onChanged(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function18 = function15;
                if (function18 != null) {
                    function18.invoke(event);
                }
            }

            @Override // androidx.compose.ui.draganddrop.DragAndDropTarget
            public void onEnded(DragAndDropEvent event) {
                Function1<DragAndDropEvent, Unit> function18 = function17;
                if (function18 != null) {
                    function18.invoke(event);
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final boolean textFieldDragAndDropNode$lambda$0(kotlin.jvm.functions.Function0 r10, androidx.compose.ui.draganddrop.DragAndDropEvent r11) {
        /*
            android.view.DragEvent r0 = androidx.compose.ui.draganddrop.DragAndDrop_androidKt.toAndroidDragEvent(r11)
            android.content.ClipDescription r0 = r0.getClipDescription()
            java.lang.Object r1 = r10.invoke()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r2 = 0
            boolean r3 = r1 instanceof java.util.Collection
            r4 = 0
            if (r3 == 0) goto L1f
            r3 = r1
            java.util.Collection r3 = (java.util.Collection) r3
            boolean r3 = r3.isEmpty()
            if (r3 == 0) goto L1f
            goto L58
        L1f:
            java.util.Iterator r3 = r1.iterator()
        L23:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L57
            java.lang.Object r5 = r3.next()
            r6 = r5
            androidx.compose.foundation.content.MediaType r6 = (androidx.compose.foundation.content.MediaType) r6
            r7 = 0
            androidx.compose.foundation.content.MediaType$Companion r8 = androidx.compose.foundation.content.MediaType.INSTANCE
            androidx.compose.foundation.content.MediaType r8 = r8.getAll()
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r8)
            r9 = 1
            if (r8 != 0) goto L52
            if (r0 == 0) goto L4c
            java.lang.String r8 = r6.getRepresentation()
            boolean r8 = r0.hasMimeType(r8)
            if (r8 != r9) goto L4c
            r8 = r9
            goto L4d
        L4c:
            r8 = r4
        L4d:
            if (r8 == 0) goto L50
            goto L52
        L50:
            r6 = r4
            goto L53
        L52:
            r6 = r9
        L53:
            if (r6 == 0) goto L23
            r4 = r9
            goto L58
        L57:
        L58:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.input.internal.TextFieldDragAndDropNode_androidKt.textFieldDragAndDropNode$lambda$0(kotlin.jvm.functions.Function0, androidx.compose.ui.draganddrop.DragAndDropEvent):boolean");
    }
}
