package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.JoinedKey;
import kotlin.Metadata;

/* JADX INFO: compiled from: KeyInfo.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0001\u0018\u00002\u00020\u0001B7\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u0012\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0012\u0010\u0014\u001a\u00020\u00038Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0015\u0010\rR\u0011\u0010\u0016\u001a\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000f¨\u0006\u0018"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/KeyInfo;", "", "key", "", "objectKey", "handle", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupHandle;", "nodes", "index", "<init>", "(ILjava/lang/Object;JII)V", "getKey", "()I", "getObjectKey", "()Ljava/lang/Object;", "getHandle", "()J", "getNodes", "getIndex", "address", "getAddress", "joinedKey", "getJoinedKey", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class KeyInfo {
    public static final int $stable = 8;
    private final long handle;
    private final int index;
    private final int key;
    private final int nodes;
    private final Object objectKey;

    public KeyInfo(int key, Object objectKey, long handle, int nodes, int index) {
        this.key = key;
        this.objectKey = objectKey;
        this.handle = handle;
        this.nodes = nodes;
        this.index = index;
    }

    public final int getKey() {
        return this.key;
    }

    public final Object getObjectKey() {
        return this.objectKey;
    }

    public final long getHandle() {
        return this.handle;
    }

    public final int getNodes() {
        return this.nodes;
    }

    public final int getIndex() {
        return this.index;
    }

    public final int getAddress() {
        return GroupHandleKt.getGroup(getHandle());
    }

    public final Object getJoinedKey() {
        return this.objectKey != null ? new JoinedKey(Integer.valueOf(this.key), this.objectKey) : Integer.valueOf(this.key);
    }
}
