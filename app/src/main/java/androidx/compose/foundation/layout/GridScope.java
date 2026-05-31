package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: compiled from: Grid.kt */
/* JADX INFO: loaded from: classes.dex */
@LayoutScopeMarker
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eJ>\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\b\b\u0003\u0010\u0004\u001a\u00020\u00052\b\b\u0003\u0010\u0006\u001a\u00020\u00052\b\b\u0003\u0010\u0007\u001a\u00020\u00052\b\b\u0003\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nH'J&\u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\t\u001a\u00020\nH'ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/layout/GridScope;", "", "gridItem", "Landroidx/compose/ui/Modifier;", "row", "", "column", "rowSpan", "columnSpan", "alignment", "Landroidx/compose/ui/Alignment;", "rows", "Lkotlin/ranges/IntRange;", "columns", "Companion", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
public interface GridScope {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int GridIndexUnspecified = 0;
    public static final int MaxGridIndex = 1000;

    Modifier gridItem(Modifier modifier, int i, int i2, int i3, int i4, Alignment alignment);

    Modifier gridItem(Modifier modifier, IntRange intRange, IntRange intRange2, Alignment alignment);

    /* JADX INFO: compiled from: Grid.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Modifier gridItem$default(GridScope gridScope, Modifier modifier, int i, int i2, int i3, int i4, Alignment alignment, int i5, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: gridItem");
        }
        if ((i5 & 1) != 0) {
            i = 0;
        }
        if ((i5 & 2) != 0) {
            i2 = 0;
        }
        if ((i5 & 4) != 0) {
            i3 = 1;
        }
        if ((i5 & 8) != 0) {
            i4 = 1;
        }
        if ((i5 & 16) != 0) {
            alignment = Alignment.INSTANCE.getTopStart();
        }
        return gridScope.gridItem(modifier, i, i2, i3, i4, alignment);
    }

    static /* synthetic */ Modifier gridItem$default(GridScope gridScope, Modifier modifier, IntRange intRange, IntRange intRange2, Alignment alignment, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: gridItem");
        }
        if ((i & 4) != 0) {
            alignment = Alignment.INSTANCE.getTopStart();
        }
        return gridScope.gridItem(modifier, intRange, intRange2, alignment);
    }

    /* JADX INFO: compiled from: Grid.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/layout/GridScope$Companion;", "", "<init>", "()V", "MaxGridIndex", "", "getMaxGridIndex$annotations", "GridIndexUnspecified", "getGridIndexUnspecified$annotations", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int GridIndexUnspecified = 0;
        public static final int MaxGridIndex = 1000;

        public static /* synthetic */ void getGridIndexUnspecified$annotations() {
        }

        public static /* synthetic */ void getMaxGridIndex$annotations() {
        }

        private Companion() {
        }
    }
}
