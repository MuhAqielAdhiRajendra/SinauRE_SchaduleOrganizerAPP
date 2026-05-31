package androidx.compose.foundation.layout;

import kotlin.Metadata;

/* JADX INFO: compiled from: FlexBox.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0014\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\t¨\u0006\u0019"}, d2 = {"Landroidx/compose/foundation/layout/FlexLine;", "", "<init>", "()V", "startIndex", "", "getStartIndex", "()I", "setStartIndex", "(I)V", "endIndex", "getEndIndex", "setEndIndex", "mainAxisSize", "getMainAxisSize", "setMainAxisSize", "crossAxisSize", "getCrossAxisSize", "setCrossAxisSize", "crossStart", "getCrossStart", "setCrossStart", "maxAboveBaseline", "getMaxAboveBaseline", "setMaxAboveBaseline", "foundation-layout"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class FlexLine {
    private int crossAxisSize;
    private int crossStart;
    private int endIndex;
    private int mainAxisSize;
    private int maxAboveBaseline;
    private int startIndex;

    public final int getStartIndex() {
        return this.startIndex;
    }

    public final void setStartIndex(int i) {
        this.startIndex = i;
    }

    public final int getEndIndex() {
        return this.endIndex;
    }

    public final void setEndIndex(int i) {
        this.endIndex = i;
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final void setMainAxisSize(int i) {
        this.mainAxisSize = i;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final void setCrossAxisSize(int i) {
        this.crossAxisSize = i;
    }

    public final int getCrossStart() {
        return this.crossStart;
    }

    public final void setCrossStart(int i) {
        this.crossStart = i;
    }

    public final int getMaxAboveBaseline() {
        return this.maxAboveBaseline;
    }

    public final void setMaxAboveBaseline(int i) {
        this.maxAboveBaseline = i;
    }
}
