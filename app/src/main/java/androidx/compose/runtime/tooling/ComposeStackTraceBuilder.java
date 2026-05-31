package androidx.compose.runtime.tooling;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.composer.GroupSourceInformation;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ComposeStackTraceBuilder.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bJ$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001H\u0002J&\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0013\u001a\u00020\u0001H\u0002J\f\u0010\u0014\u001a\u00020\u0015*\u00020\u000eH\u0002J,\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00012\b\u0010\u0018\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001J \u0010\u001a\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0001H\u0002J\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001dH&R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/tooling/ComposeStackTraceBuilder;", "", "<init>", "()V", "_trace", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "trace", "", "appendTraceFrame", "", "groupKey", "", "groupSourceInformation", "Landroidx/compose/runtime/composer/GroupSourceInformation;", "child", "extractTraceFrame", "targetChild", "sourceInformationOf", "group", "isCall", "", "processEdge", "objectKey", "sourceInformation", "childData", "appendGroupSourceInformation", TypedValues.AttributesType.S_TARGET, "anchor", "Landroidx/compose/runtime/Anchor;", "groupKeyOf", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class ComposeStackTraceBuilder {
    public static final int $stable = 8;
    private final List<ComposeStackTraceFrame> _trace = new ArrayList();

    public abstract int groupKeyOf(Anchor anchor);

    public abstract GroupSourceInformation sourceInformationOf(Anchor anchor);

    public final List<ComposeStackTraceFrame> trace() {
        return this._trace;
    }

    private final void appendTraceFrame(int groupKey, GroupSourceInformation groupSourceInformation, Object child) {
        ComposeStackTraceFrame frame = extractTraceFrame(groupKey, groupSourceInformation, child);
        if (frame != null) {
            this._trace.add(frame);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00a7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final androidx.compose.runtime.tooling.ComposeStackTraceFrame extractTraceFrame(int r21, androidx.compose.runtime.composer.GroupSourceInformation r22, java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.tooling.ComposeStackTraceBuilder.extractTraceFrame(int, androidx.compose.runtime.composer.GroupSourceInformation, java.lang.Object):androidx.compose.runtime.tooling.ComposeStackTraceFrame");
    }

    private final GroupSourceInformation sourceInformationOf(Object group) {
        if (group instanceof Anchor) {
            return sourceInformationOf((Anchor) group);
        }
        if (group instanceof GroupSourceInformation) {
            return (GroupSourceInformation) group;
        }
        throw new IllegalStateException(("Unexpected child source info " + group).toString());
    }

    private final boolean isCall(GroupSourceInformation $this$isCall) {
        String sourceInformation = $this$isCall.getSourceInformation();
        return sourceInformation != null && StringsKt.startsWith$default(sourceInformation, "C", false, 2, (Object) null);
    }

    public final void processEdge(int groupKey, Object objectKey, GroupSourceInformation sourceInformation, Object childData) {
        if (sourceInformation == null && !Intrinsics.areEqual(objectKey, Composer.INSTANCE.getEmpty())) {
            return;
        }
        if (childData == null || sourceInformation == null) {
            appendTraceFrame(groupKey, sourceInformation, null);
            return;
        }
        boolean found = appendGroupSourceInformation(groupKey, sourceInformation, childData);
        if (!found && !sourceInformation.getClosed()) {
            appendTraceFrame(groupKey, sourceInformation, childData);
        }
    }

    private final boolean appendGroupSourceInformation(int groupKey, GroupSourceInformation sourceInformation, Object target) {
        List groups = sourceInformation.getGroups();
        boolean found = false;
        if (groups == null) {
            if (!sourceInformation.getClosed()) {
                appendTraceFrame(groupKey, sourceInformation, null);
                return true;
            }
            int slotStart = sourceInformation.getDataStartOffset();
            int slotEnd = sourceInformation.getDataEndOffset();
            if (!(target instanceof Integer)) {
                return false;
            }
            int iIntValue = ((Number) target).intValue();
            if ((slotStart <= iIntValue && iIntValue < slotEnd) || (slotStart == slotEnd && (target instanceof Integer) && slotStart == ((Number) target).intValue())) {
                found = true;
            }
            if (found) {
                appendTraceFrame(sourceInformation.getKey(), sourceInformation, null);
            }
            return found;
        }
        List $this$fastForEach$iv = groups;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            if (item$iv instanceof Anchor) {
                if (Intrinsics.areEqual(item$iv, target)) {
                    appendTraceFrame(sourceInformation.getKey(), sourceInformation, item$iv);
                    return true;
                }
            } else if (item$iv instanceof GroupSourceInformation) {
                if (appendGroupSourceInformation(groupKey, (GroupSourceInformation) item$iv, target)) {
                    appendTraceFrame(sourceInformation.getKey(), sourceInformation, item$iv);
                    return true;
                }
            } else {
                throw new IllegalStateException(("Unexpected child source info " + item$iv).toString());
            }
        }
        return false;
    }
}
