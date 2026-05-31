package androidx.compose.ui.tooling.data;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: SlotTree.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/compose/ui/tooling/data/EmptyGroup;", "Landroidx/compose/ui/tooling/data/Group;", "<init>", "()V", "ui-tooling-data"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class EmptyGroup extends Group {
    public static final EmptyGroup INSTANCE = new EmptyGroup();

    private EmptyGroup() {
        super(null, null, null, null, SlotTreeKt.getEmptyBox(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), false, null);
    }
}
