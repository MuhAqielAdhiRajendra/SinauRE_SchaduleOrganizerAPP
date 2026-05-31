package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.Anchor;
import kotlin.Metadata;

/* JADX INFO: compiled from: LinkAnchor.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u001e\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "Landroidx/compose/runtime/Anchor;", "address", "", "Landroidx/compose/runtime/composer/linkbuffer/GroupAddress;", "<init>", "(I)V", "getAddress", "()I", "setAddress", "valid", "", "getValid", "()Z", "toString", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LinkAnchor implements Anchor {
    public static final int $stable = 8;
    private int address;

    public LinkAnchor(int address) {
        this.address = address;
    }

    public final int getAddress() {
        return this.address;
    }

    public final void setAddress(int i) {
        this.address = i;
    }

    @Override // androidx.compose.runtime.Anchor
    public boolean getValid() {
        return this.address != -1;
    }

    public String toString() {
        return super.toString() + "{ address: " + this.address + " }";
    }
}
