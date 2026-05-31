package androidx.compose.runtime.composer.linkbuffer;

import androidx.compose.runtime.composer.GroupSourceInformation;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LinkGroupSourceInformation.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010!\u001a\u00020\"2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0003J\u000e\u0010$\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0003J\u000e\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020'J\u0018\u0010(\u001a\u00020\"2\b\u0010)\u001a\u0004\u0018\u00010'2\u0006\u0010*\u001a\u00020'J\u000e\u0010+\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0003J\b\u0010,\u001a\u00020\u0000H\u0002J\u0010\u0010-\u001a\u00020\"2\u0006\u0010*\u001a\u00020\u0012H\u0002J\u0010\u0010.\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'H\u0002J\u000e\u0010/\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nRF\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011j\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001`\u00132\u001a\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011j\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001`\u0013@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\n\"\u0004\b\u001f\u0010 ¨\u00060"}, d2 = {"Landroidx/compose/runtime/composer/linkbuffer/LinkGroupSourceInformation;", "Landroidx/compose/runtime/composer/GroupSourceInformation;", "key", "", "sourceInformation", "", "dataStartOffset", "<init>", "(ILjava/lang/String;I)V", "getKey", "()I", "getSourceInformation", "()Ljava/lang/String;", "setSourceInformation", "(Ljava/lang/String;)V", "getDataStartOffset", "value", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "groups", "getGroups", "()Ljava/util/ArrayList;", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "dataEndOffset", "getDataEndOffset", "setDataEndOffset", "(I)V", "startGrouplessCall", "", "dataOffset", "endGrouplessCall", "reportGroup", "anchor", "Landroidx/compose/runtime/composer/linkbuffer/LinkAnchor;", "addGroupAfter", "predecessor", "group", "close", "openInformation", "add", "hasGroup", "removeGroup", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class LinkGroupSourceInformation implements GroupSourceInformation {
    public static final int $stable = 8;
    private boolean closed;
    private int dataEndOffset;
    private final int dataStartOffset;
    private ArrayList<Object> groups;
    private final int key;
    private String sourceInformation;

    public LinkGroupSourceInformation(int key, String sourceInformation, int dataStartOffset) {
        this.key = key;
        this.sourceInformation = sourceInformation;
        this.dataStartOffset = dataStartOffset;
    }

    @Override // androidx.compose.runtime.composer.GroupSourceInformation
    public int getKey() {
        return this.key;
    }

    @Override // androidx.compose.runtime.composer.GroupSourceInformation
    public String getSourceInformation() {
        return this.sourceInformation;
    }

    public void setSourceInformation(String str) {
        this.sourceInformation = str;
    }

    @Override // androidx.compose.runtime.composer.GroupSourceInformation
    public int getDataStartOffset() {
        return this.dataStartOffset;
    }

    @Override // androidx.compose.runtime.composer.GroupSourceInformation
    public ArrayList<Object> getGroups() {
        return this.groups;
    }

    @Override // androidx.compose.runtime.composer.GroupSourceInformation
    public boolean getClosed() {
        return this.closed;
    }

    public void setClosed(boolean z) {
        this.closed = z;
    }

    @Override // androidx.compose.runtime.composer.GroupSourceInformation
    public int getDataEndOffset() {
        return this.dataEndOffset;
    }

    public void setDataEndOffset(int i) {
        this.dataEndOffset = i;
    }

    public final void startGrouplessCall(int key, String sourceInformation, int dataOffset) {
        openInformation().add(new LinkGroupSourceInformation(key, sourceInformation, dataOffset));
    }

    public final void endGrouplessCall(int dataOffset) {
        openInformation().close(dataOffset);
    }

    public final void reportGroup(LinkAnchor anchor) {
        openInformation().add(anchor);
    }

    public final void addGroupAfter(LinkAnchor predecessor, LinkAnchor group) {
        ArrayList<Object> groups = getGroups();
        if (groups == null) {
            groups = new ArrayList<>();
            this.groups = groups;
        }
        int index = 0;
        if (predecessor != null) {
            ArrayList<Object> arrayList = groups;
            int index$iv = 0;
            int size$iv = arrayList.size();
            while (true) {
                if (index$iv >= size$iv) {
                    index = -1;
                    break;
                }
                Object value$iv = arrayList.get(index$iv);
                if (Intrinsics.areEqual(value$iv, predecessor) || ((value$iv instanceof LinkGroupSourceInformation) && ((LinkGroupSourceInformation) value$iv).hasGroup(predecessor))) {
                    index = index$iv;
                    break;
                }
                index$iv++;
            }
        }
        groups.add(index, group);
    }

    public final void close(int dataOffset) {
        setClosed(true);
        setDataEndOffset(dataOffset);
    }

    private final LinkGroupSourceInformation openInformation() {
        LinkGroupSourceInformation linkGroupSourceInformationOpenInformation;
        ArrayList<Object> groups = getGroups();
        Object obj = null;
        if (groups != null) {
            int index$iv = groups.size() - 1;
            while (true) {
                if (index$iv < 0) {
                    break;
                }
                Object value$iv = groups.get(index$iv);
                if ((value$iv instanceof LinkGroupSourceInformation) && !((LinkGroupSourceInformation) value$iv).getClosed()) {
                    obj = value$iv;
                    break;
                }
                index$iv--;
            }
        }
        LinkGroupSourceInformation linkGroupSourceInformation = (LinkGroupSourceInformation) obj;
        return (linkGroupSourceInformation == null || (linkGroupSourceInformationOpenInformation = linkGroupSourceInformation.openInformation()) == null) ? this : linkGroupSourceInformationOpenInformation;
    }

    private final void add(Object group) {
        ArrayList<Object> groups = getGroups();
        if (groups == null) {
            groups = new ArrayList<>();
            this.groups = groups;
        }
        groups.add(group);
    }

    private final boolean hasGroup(LinkAnchor anchor) {
        boolean z;
        List groups = getGroups();
        if (groups == null) {
            return false;
        }
        List $this$fastAny$iv = groups;
        int index$iv$iv = 0;
        int size = $this$fastAny$iv.size();
        while (true) {
            if (index$iv$iv < size) {
                Object item$iv$iv = $this$fastAny$iv.get(index$iv$iv);
                if (Intrinsics.areEqual(item$iv$iv, anchor) || ((item$iv$iv instanceof LinkGroupSourceInformation) && ((LinkGroupSourceInformation) item$iv$iv).hasGroup(anchor))) {
                    z = true;
                    break;
                }
                index$iv$iv++;
            } else {
                z = false;
                break;
            }
        }
        return z;
    }

    public final boolean removeGroup(LinkAnchor anchor) {
        ArrayList<Object> groups = getGroups();
        if (groups != null) {
            for (int index = groups.size() - 1; index >= 0; index--) {
                Object item = groups.get(index);
                if (item instanceof LinkAnchor) {
                    if (Intrinsics.areEqual(item, anchor)) {
                        groups.remove(index);
                    }
                } else if ((item instanceof LinkGroupSourceInformation) && !((LinkGroupSourceInformation) item).removeGroup(anchor)) {
                    groups.remove(index);
                }
            }
            if (!groups.isEmpty()) {
                return true;
            }
            this.groups = null;
            return false;
        }
        return true;
    }
}
