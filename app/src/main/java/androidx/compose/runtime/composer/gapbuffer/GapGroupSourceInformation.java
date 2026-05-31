package androidx.compose.runtime.composer.gapbuffer;

import androidx.compose.runtime.composer.GroupSourceInformation;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SlotTable.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\"\u001a\u00020#2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010$\u001a\u00020\u0003J\u000e\u0010%\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0003J\u0016\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0003J\u0016\u0010&\u001a\u00020#2\u0006\u0010*\u001a\u00020+2\u0006\u0010)\u001a\u00020\u0003J\u001e\u0010,\u001a\u00020#2\u0006\u0010'\u001a\u00020(2\u0006\u0010-\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u0003J\u000e\u0010.\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0003J\b\u0010/\u001a\u00020\u0000H\u0002J\u0010\u00100\u001a\u00020#2\u0006\u0010)\u001a\u00020\u0012H\u0002J\u0010\u00101\u001a\u00020\u00192\u0006\u00102\u001a\u000203H\u0002J\u000e\u00104\u001a\u00020\u00192\u0006\u00102\u001a\u000203R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\nR.\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011j\n\u0012\u0004\u0012\u00020\u0012\u0018\u0001`\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\n\"\u0004\b \u0010!¨\u00065"}, d2 = {"Landroidx/compose/runtime/composer/gapbuffer/GapGroupSourceInformation;", "Landroidx/compose/runtime/composer/GroupSourceInformation;", "key", "", "sourceInformation", "", "dataStartOffset", "<init>", "(ILjava/lang/String;I)V", "getKey", "()I", "getSourceInformation", "()Ljava/lang/String;", "setSourceInformation", "(Ljava/lang/String;)V", "getDataStartOffset", "groups", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getGroups", "()Ljava/util/ArrayList;", "setGroups", "(Ljava/util/ArrayList;)V", "closed", "", "getClosed", "()Z", "setClosed", "(Z)V", "dataEndOffset", "getDataEndOffset", "setDataEndOffset", "(I)V", "startGrouplessCall", "", "dataOffset", "endGrouplessCall", "reportGroup", "writer", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "group", "table", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "addGroupAfter", "predecessor", "close", "openInformation", "add", "hasAnchor", "anchor", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "removeAnchor", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class GapGroupSourceInformation implements GroupSourceInformation {
    public static final int $stable = 8;
    private boolean closed;
    private int dataEndOffset;
    private final int dataStartOffset;
    private ArrayList<Object> groups;
    private final int key;
    private String sourceInformation;

    public GapGroupSourceInformation(int key, String sourceInformation, int dataStartOffset) {
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

    public void setGroups(ArrayList<Object> arrayList) {
        this.groups = arrayList;
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
        openInformation().add(new GapGroupSourceInformation(key, sourceInformation, dataOffset));
    }

    public final void endGrouplessCall(int dataOffset) {
        openInformation().close(dataOffset);
    }

    public final void reportGroup(SlotWriter writer, int group) {
        openInformation().add(writer.anchor(group));
    }

    public final void reportGroup(SlotTable table, int group) {
        openInformation().add(table.anchor(group));
    }

    public final void addGroupAfter(SlotWriter writer, int predecessor, int group) {
        GapAnchor anchor;
        ArrayList<Object> groups = getGroups();
        if (groups == null) {
            groups = new ArrayList<>();
            setGroups(groups);
        }
        int index = 0;
        if (predecessor >= 0 && (anchor = writer.tryAnchor$runtime(predecessor)) != null) {
            ArrayList<Object> arrayList = groups;
            int index$iv = 0;
            int size$iv = arrayList.size();
            while (true) {
                if (index$iv >= size$iv) {
                    index = -1;
                    break;
                }
                Object value$iv = arrayList.get(index$iv);
                if (Intrinsics.areEqual(value$iv, anchor) || ((value$iv instanceof GapGroupSourceInformation) && ((GapGroupSourceInformation) value$iv).hasAnchor(anchor))) {
                    index = index$iv;
                    break;
                }
                index$iv++;
            }
        }
        groups.add(index, writer.anchor(group));
    }

    public final void close(int dataOffset) {
        setClosed(true);
        setDataEndOffset(dataOffset);
    }

    private final GapGroupSourceInformation openInformation() {
        Object value$iv;
        GapGroupSourceInformation gapGroupSourceInformationOpenInformation;
        ArrayList<Object> groups = getGroups();
        if (groups != null) {
            int index$iv = groups.size() - 1;
            while (true) {
                if (index$iv < 0) {
                    value$iv = null;
                    break;
                }
                value$iv = groups.get(index$iv);
                if ((value$iv instanceof GapGroupSourceInformation) && !((GapGroupSourceInformation) value$iv).getClosed()) {
                    break;
                }
                index$iv--;
            }
        } else {
            value$iv = null;
        }
        GapGroupSourceInformation gapGroupSourceInformation = value$iv instanceof GapGroupSourceInformation ? (GapGroupSourceInformation) value$iv : null;
        return (gapGroupSourceInformation == null || (gapGroupSourceInformationOpenInformation = gapGroupSourceInformation.openInformation()) == null) ? this : gapGroupSourceInformationOpenInformation;
    }

    private final void add(Object group) {
        ArrayList<Object> groups = getGroups();
        if (groups == null) {
            groups = new ArrayList<>();
        }
        setGroups(groups);
        groups.add(group);
    }

    private final boolean hasAnchor(GapAnchor anchor) {
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
                if (Intrinsics.areEqual(item$iv$iv, anchor) || ((item$iv$iv instanceof GapGroupSourceInformation) && ((GapGroupSourceInformation) item$iv$iv).hasAnchor(anchor))) {
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

    public final boolean removeAnchor(GapAnchor anchor) {
        ArrayList<Object> groups = getGroups();
        if (groups != null) {
            for (int index = groups.size() - 1; index >= 0; index--) {
                Object item = groups.get(index);
                if (item instanceof GapAnchor) {
                    if (Intrinsics.areEqual(item, anchor)) {
                        groups.remove(index);
                    }
                } else if ((item instanceof GapGroupSourceInformation) && !((GapGroupSourceInformation) item).removeAnchor(anchor)) {
                    groups.remove(index);
                }
            }
            if (!groups.isEmpty()) {
                return true;
            }
            setGroups(null);
            return false;
        }
        return true;
    }
}
