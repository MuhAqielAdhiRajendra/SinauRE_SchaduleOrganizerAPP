package androidx.compose.runtime.composer.linkbuffer;

import kotlin.Metadata;
import kotlin.UInt;

/* JADX INFO: compiled from: GroupHandle.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\b\u001a%\u0010\u0004\u001a\u00060\u0001j\u0002`\u00032\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u00072\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0080\b\u001a0\u0010\u0004\u001a\u00060\u0001j\u0002`\u00032\n\u0010\r\u001a\u00060\u0006j\u0002`\u00072\n\u0010\u000e\u001a\u00060\u0006j\u0002`\u00072\n\u0010\b\u001a\u00060\u0006j\u0002`\u0007H\u0000\"\u0012\u0010\u0002\u001a\u00060\u0001j\u0002`\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u001c\u0010\t\u001a\u00020\u0006*\u00060\u0001j\u0002`\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001c\u0010\b\u001a\u00020\u0006*\u00060\u0001j\u0002`\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000b*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001¨\u0006\u000f"}, d2 = {"GroupHandle", "", "NULL_GROUP_HANDLE", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "makeGroupHandle", "groupContext", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "group", "context", "getContext", "(J)I", "getGroup", "parent", "predecessor", "runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class GroupHandleKt {
    public static final long NULL_GROUP_HANDLE = -1;

    public static final long makeGroupHandle(int groupContext, int group) {
        return (((long) groupContext) << 32) | (((long) UInt.m9024constructorimpl(group)) & 4294967295L);
    }

    public static final int getContext(long $this$context) {
        return (int) ($this$context >>> 32);
    }

    public static final int getGroup(long $this$group) {
        return (int) $this$group;
    }

    public static final long makeGroupHandle(int parent, int predecessor, int group) {
        if (group >= 0) {
            return (4294967295L & ((long) UInt.m9024constructorimpl(group))) | (((long) predecessor) << 32);
        }
        return (4294967295L & ((long) UInt.m9024constructorimpl(-1))) | (((long) parent) << 32);
    }
}
