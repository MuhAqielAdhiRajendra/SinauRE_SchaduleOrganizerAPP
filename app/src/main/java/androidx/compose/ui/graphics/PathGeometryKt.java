package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.PathSegment;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PathGeometry.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004*\u00020\u00022\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0007\u001a\u00020\u0002\u001a\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"computeDirection", "Landroidx/compose/ui/graphics/Path$Direction;", "Landroidx/compose/ui/graphics/Path;", "divide", "", "contours", "reverse", "destination", "floatCountForType", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/ui/graphics/PathSegment$Type;", "ui-graphics"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PathGeometryKt {

    /* JADX INFO: compiled from: PathGeometry.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PathSegment.Type.values().length];
            try {
                iArr[PathSegment.Type.Move.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PathSegment.Type.Line.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PathSegment.Type.Quadratic.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[PathSegment.Type.Conic.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[PathSegment.Type.Cubic.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[PathSegment.Type.Close.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[PathSegment.Type.Done.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final androidx.compose.ui.graphics.Path.Direction computeDirection(androidx.compose.ui.graphics.Path r33) {
        /*
            Method dump skipped, instruction units count: 262
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.PathGeometryKt.computeDirection(androidx.compose.ui.graphics.Path):androidx.compose.ui.graphics.Path$Direction");
    }

    public static /* synthetic */ List divide$default(Path path, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return divide(path, list);
    }

    public static final List<Path> divide(Path $this$divide, List<Path> list) {
        Path path = AndroidPath_androidKt.Path();
        boolean first = true;
        boolean isEmpty = true;
        PathIterator iterator = $this$divide.iterator();
        float[] points = new float[8];
        PathSegment.Type type = PathIterator.next$default(iterator, points, 0, 2, null);
        Path path2 = path;
        while (type != PathSegment.Type.Done) {
            switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
                case 1:
                    if (!first && !isEmpty) {
                        list.add(path2);
                        path2 = AndroidPath_androidKt.Path();
                    }
                    path2.moveTo(points[0], points[1]);
                    isEmpty = true;
                    first = false;
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 2:
                    path2.lineTo(points[2], points[3]);
                    isEmpty = false;
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 3:
                    path2.quadraticTo(points[2], points[3], points[4], points[5]);
                    isEmpty = false;
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 4:
                case 7:
                    break;
                case 5:
                    path2.cubicTo(points[2], points[3], points[4], points[5], points[6], points[7]);
                    isEmpty = false;
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                case 6:
                    path2.close();
                    type = PathIterator.next$default(iterator, points, 0, 2, null);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        if (!first && !isEmpty) {
            list.add(path2);
        }
        return list;
    }

    public static /* synthetic */ Path reverse$default(Path path, Path path2, int i, Object obj) {
        if ((i & 1) != 0) {
            path2 = AndroidPath_androidKt.Path();
        }
        return reverse(path, path2);
    }

    public static final Path reverse(Path $this$reverse, Path destination) {
        char c;
        float[] points;
        boolean insertMove;
        int dataIndex;
        int i;
        PathIterator iterator = $this$reverse.iterator();
        char c2 = 0;
        int count = iterator.calculateSize(false);
        ArrayList segments = new ArrayList(count);
        ArrayList data = new ArrayList(count);
        float[] points2 = new float[8];
        for (PathSegment.Type type = PathIterator.next$default(iterator, points2, 0, 2, null); type != PathSegment.Type.Done; type = PathIterator.next$default(iterator, points2, 0, 2, null)) {
            segments.add(type);
            if (type != PathSegment.Type.Close) {
                float[] fArrCopyOf = Arrays.copyOf(points2, floatCountForType(type));
                Intrinsics.checkNotNullExpressionValue(fArrCopyOf, "copyOf(...)");
                data.add(fArrCopyOf);
            }
        }
        boolean insertMove2 = true;
        int dataIndex2 = data.size();
        boolean insertClose = false;
        int i2 = segments.size() - 1;
        while (-1 < i2) {
            if (insertMove2) {
                int dataIndex3 = dataIndex2 - 1;
                float[] points3 = (float[]) data.get(dataIndex3);
                int offset = ArraysKt.getLastIndex(points3);
                c = c2;
                destination.moveTo(points3[offset - 1], points3[offset]);
                points = points3;
                insertMove = false;
                dataIndex = dataIndex3;
            } else {
                c = c2;
                points = (float[]) data.get(dataIndex2);
                insertMove = insertMove2;
                dataIndex = dataIndex2;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[((PathSegment.Type) segments.get(i2)).ordinal()]) {
                case 1:
                    i = i2;
                    if (insertClose) {
                        destination.close();
                        insertClose = false;
                    }
                    insertMove2 = true;
                    continue;
                    dataIndex2 = dataIndex;
                    i2 = i - 1;
                    c2 = c;
                    break;
                case 2:
                    i = i2;
                    destination.lineTo(points[c], points[1]);
                    dataIndex--;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i2 = i - 1;
                    c2 = c;
                    break;
                case 3:
                    i = i2;
                    destination.quadraticTo(points[2], points[3], points[c], points[1]);
                    dataIndex--;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i2 = i - 1;
                    c2 = c;
                    break;
                case 4:
                    i = i2;
                    break;
                case 5:
                    i = i2;
                    destination.cubicTo(points[4], points[5], points[2], points[3], points[c], points[1]);
                    dataIndex--;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i2 = i - 1;
                    c2 = c;
                    break;
                case 6:
                    insertClose = true;
                    i = i2;
                    insertMove2 = insertMove;
                    continue;
                    dataIndex2 = dataIndex;
                    i2 = i - 1;
                    c2 = c;
                    break;
                case 7:
                    i = i2;
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            insertMove2 = insertMove;
            dataIndex2 = dataIndex;
            i2 = i - 1;
            c2 = c;
        }
        if (insertClose) {
            destination.close();
        }
        return destination;
    }

    private static final int floatCountForType(PathSegment.Type type) {
        switch (WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 6;
            case 4:
                return 8;
            case 5:
                return 8;
            case 6:
            case 7:
                return 0;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
