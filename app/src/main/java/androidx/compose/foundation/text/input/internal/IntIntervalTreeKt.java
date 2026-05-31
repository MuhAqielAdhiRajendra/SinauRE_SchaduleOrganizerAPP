package androidx.compose.foundation.text.input.internal;

import kotlin.Metadata;

/* JADX INFO: compiled from: IntIntervalTree.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a(\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000*\f\b\u0000\u0010\u0000\"\u00020\u00012\u00020\u0001*\f\b\u0000\u0010\u0002\"\u00020\u00032\u00020\u0003¨\u0006\u0010"}, d2 = {"NodeList", "Landroidx/collection/MutableIntList;", "TreeColor", "", "TreeColorRed", "TreeColorBlack", "TreeColorDeleted", "Unvisited", "LeftVisited", "RightVisited", "intersect", "", "lStart", "lEnd", "rStart", "rEnd", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class IntIntervalTreeKt {
    public static final int LeftVisited = 1;
    public static final int RightVisited = 2;
    public static final int TreeColorBlack = 1;
    public static final int TreeColorDeleted = 2;
    public static final int TreeColorRed = 0;
    public static final int Unvisited = 0;

    public static final boolean intersect(int lStart, int lEnd, int rStart, int rEnd) {
        return ((rStart < lEnd) & (lStart < rEnd)) | (((lStart == lEnd) | (rStart == rEnd)) & (lStart == rStart));
    }
}
