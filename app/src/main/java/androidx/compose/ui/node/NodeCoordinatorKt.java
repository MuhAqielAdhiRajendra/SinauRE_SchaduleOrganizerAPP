package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: NodeCoordinator.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006H\u0002\u001a-\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\fH\u0002¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"compareEquals", "", "a", "Landroidx/collection/MutableObjectIntMap;", "Landroidx/compose/ui/layout/AlignmentLine;", "b", "", "", "nextUntil", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DelegatableNode;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/node/NodeKind;", "stopType", "nextUntil-hw7D004", "(Landroidx/compose/ui/node/DelegatableNode;II)Landroidx/compose/ui/Modifier$Node;", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class NodeCoordinatorKt {
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0081, code lost:
    
        return r16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean compareEquals(androidx.collection.MutableObjectIntMap<androidx.compose.ui.layout.AlignmentLine> r25, java.util.Map<androidx.compose.ui.layout.AlignmentLine, java.lang.Integer> r26) {
        /*
            r0 = 0
            if (r25 != 0) goto L4
            return r0
        L4:
            int r1 = r25.get_size()
            int r2 = r26.size()
            if (r1 == r2) goto Lf
            return r0
        Lf:
            r1 = r25
            androidx.collection.ObjectIntMap r1 = (androidx.collection.ObjectIntMap) r1
            r2 = 0
            java.lang.Object[] r3 = r1.keys
            int[] r4 = r1.values
            r5 = r1
            r6 = 0
            long[] r7 = r5.metadata
            int r8 = r7.length
            int r8 = r8 + (-2)
            r9 = 0
            if (r9 > r8) goto Lb2
        L22:
            r11 = r7[r9]
            r13 = r11
            r15 = 0
            r16 = r0
            r17 = r1
            long r0 = ~r13
            r18 = 7
            long r0 = r0 << r18
            long r0 = r0 & r13
            r18 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r0 = r0 & r18
            int r0 = (r0 > r18 ? 1 : (r0 == r18 ? 0 : -1))
            if (r0 == 0) goto La0
            int r0 = r9 - r8
            int r0 = ~r0
            int r0 = r0 >>> 31
            r1 = 8
            int r0 = 8 - r0
            r13 = 0
        L45:
            if (r13 >= r0) goto L95
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r11
            r18 = 0
            r19 = 128(0x80, double:6.3E-322)
            int r19 = (r14 > r19 ? 1 : (r14 == r19 ? 0 : -1))
            if (r19 >= 0) goto L54
            r14 = 1
            goto L56
        L54:
            r14 = r16
        L56:
            if (r14 == 0) goto L84
            int r14 = r9 << 3
            int r14 = r14 + r13
            r15 = r14
            r18 = 0
            r19 = r3[r15]
            r20 = 1
            r10 = r4[r15]
            r21 = r1
            r1 = r19
            androidx.compose.ui.layout.AlignmentLine r1 = (androidx.compose.ui.layout.AlignmentLine) r1
            r19 = 0
            r22 = r2
            r2 = r26
            java.lang.Object r23 = r2.get(r1)
            java.lang.Integer r23 = (java.lang.Integer) r23
            r24 = r1
            if (r23 != 0) goto L7b
            goto L81
        L7b:
            int r1 = r23.intValue()
            if (r1 == r10) goto L82
        L81:
            return r16
        L82:
            goto L8c
        L84:
            r21 = r1
            r22 = r2
            r20 = 1
            r2 = r26
        L8c:
            long r11 = r11 >> r21
            int r13 = r13 + 1
            r1 = r21
            r2 = r22
            goto L45
        L95:
            r21 = r1
            r22 = r2
            r20 = 1
            r2 = r26
            if (r0 != r1) goto Lbb
            goto La6
        La0:
            r22 = r2
            r20 = 1
            r2 = r26
        La6:
            if (r9 == r8) goto Lba
            int r9 = r9 + 1
            r0 = r16
            r1 = r17
            r2 = r22
            goto L22
        Lb2:
            r17 = r1
            r22 = r2
            r20 = 1
            r2 = r26
        Lba:
        Lbb:
            return r20
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinatorKt.compareEquals(androidx.collection.MutableObjectIntMap, java.util.Map):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: nextUntil-hw7D004, reason: not valid java name */
    public static final Modifier.Node m7098nextUntilhw7D004(DelegatableNode $this$nextUntil_u2dhw7D004, int type, int stopType) {
        Modifier.Node child = $this$nextUntil_u2dhw7D004.getNode().getChild();
        if (child == null || (child.getAggregateChildKindSet() & type) == 0) {
            return null;
        }
        for (Modifier.Node next = child; next != null; next = next.getChild()) {
            int kindSet = next.getKindSet();
            if ((kindSet & stopType) != 0) {
                return null;
            }
            if ((kindSet & type) != 0) {
                return next;
            }
        }
        return null;
    }
}
