package androidx.compose.ui.node;

import kotlin.Metadata;

/* JADX INFO: compiled from: MyersDiff.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0012\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u0006H\u0002\u001a \u0010\u000b\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0006H\u0000\u001aO\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u0017\u0010\u0018\u001aW\u0010\u0012\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u001a\u0010\u001b\u001aW\u0010\u0014\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\u001c\u0010\u001b\u001a\r\u0010\u001d\u001a\u00020\u0003*\u00020\rH\u0082\b\u001a8\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u00032\u0006\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u0016H\u0000\u001a\u001c\u0010%\u001a\u00020\b*\u00020\u00162\u0006\u0010&\u001a\u00020\u00032\u0006\u0010'\u001a\u00020\u0003H\u0002¨\u0006("}, d2 = {"calculateDiff", "Landroidx/compose/ui/node/IntStack;", "oldSize", "", "newSize", "cb", "Landroidx/compose/ui/node/DiffCallback;", "applyDiff", "", "diagonals", "callback", "executeDiff", "midPoint", "", "oldStart", "oldEnd", "newStart", "newEnd", "forward", "Landroidx/compose/ui/node/CenteredArray;", "backward", "snake", "", "midPoint-q5eDKzI", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[I[I)Z", "d", "forward-4l5_RBY", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[II[I)Z", "backward-4l5_RBY", "toInt", "fillSnake", "startX", "startY", "endX", "endY", "reverse", "data", "swap", "i", "j", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class MyersDiffKt {
    private static final IntStack calculateDiff(int oldSize, int newSize, DiffCallback cb) {
        int i = 1;
        int i2 = 2;
        int max = ((oldSize + newSize) + 1) / 2;
        IntStack diagonals = new IntStack(max * 3);
        IntStack stack = new IntStack(max * 4);
        stack.pushRange(0, oldSize, 0, newSize);
        int[] forward = CenteredArray.m6939constructorimpl(new int[(max * 2) + 1]);
        int[] backward = CenteredArray.m6939constructorimpl(new int[(max * 2) + 1]);
        int[] snake = Snake.m7171constructorimpl(new int[5]);
        while (stack.isNotEmpty()) {
            int newEnd = stack.pop();
            int newStart = stack.pop();
            int oldEnd = stack.pop();
            int oldStart = stack.pop();
            boolean found = m7058midPointq5eDKzI(oldStart, oldEnd, newStart, newEnd, cb, forward, backward, snake);
            int[] snake2 = snake;
            if (found) {
                int i3 = i;
                int i4 = i2;
                if (Math.min(snake2[i2] - snake2[0], snake2[3] - snake2[i3]) > 0) {
                    Snake.m7169addDiagonalToStackimpl(snake2, diagonals);
                }
                stack.pushRange(oldStart, snake2[0], newStart, snake2[i3]);
                stack.pushRange(snake2[i4], oldEnd, snake2[3], newEnd);
                snake = snake2;
                i = i3;
                i2 = i4;
            } else {
                snake = snake2;
            }
        }
        diagonals.sortDiagonals();
        diagonals.pushDiagonal(oldSize, newSize, 0);
        return diagonals;
    }

    private static final void applyDiff(IntStack diagonals, DiffCallback callback) {
        int posX = 0;
        int posY = 0;
        int i = 0;
        while (i < diagonals.getLastIndex()) {
            int startX = diagonals.get(i) - diagonals.get(i + 2);
            int startY = diagonals.get(i + 1) - diagonals.get(i + 2);
            int len = diagonals.get(i + 2);
            i += 3;
            while (posX < startX) {
                callback.remove(posY, posX);
                posX++;
            }
            while (posY < startY) {
                callback.insert(posY);
                posY++;
            }
            while (true) {
                int len2 = len - 1;
                if (len > 0) {
                    callback.same(posX, posY);
                    posX++;
                    posY++;
                    len = len2;
                }
            }
        }
    }

    public static final void executeDiff(int oldSize, int newSize, DiffCallback callback) {
        IntStack diagonals = calculateDiff(oldSize, newSize, callback);
        applyDiff(diagonals, callback);
    }

    /* JADX INFO: renamed from: midPoint-q5eDKzI, reason: not valid java name */
    private static final boolean m7058midPointq5eDKzI(int oldStart, int oldEnd, int newStart, int newEnd, DiffCallback cb, int[] forward, int[] backward, int[] snake) {
        int i = oldStart;
        int i2 = oldEnd;
        int oldSize = i2 - i;
        int newSize = newEnd - newStart;
        if (oldSize < 1 || newSize < 1) {
            return false;
        }
        int max = ((oldSize + newSize) + 1) / 2;
        int[] iArr = forward;
        CenteredArray.m6945setimpl(iArr, 1, i);
        int[] iArr2 = backward;
        CenteredArray.m6945setimpl(iArr2, 1, i2);
        int d = 0;
        while (d < max) {
            boolean found = m7057forward4l5_RBY(i, i2, newStart, newEnd, cb, iArr, iArr2, d, snake);
            if (found) {
                return true;
            }
            boolean found2 = m7056backward4l5_RBY(oldStart, oldEnd, newStart, newEnd, cb, forward, backward, d, snake);
            if (found2) {
                return true;
            }
            d++;
            i = oldStart;
            i2 = oldEnd;
            iArr = forward;
            iArr2 = backward;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: forward-4l5_RBY, reason: not valid java name */
    private static final boolean m7057forward4l5_RBY(int oldStart, int oldEnd, int newStart, int newEnd, DiffCallback cb, int[] forward, int[] backward, int d, int[] snake) {
        int x;
        int startX;
        boolean z;
        int i = oldEnd;
        int oldSize = i - oldStart;
        int newSize = newEnd - newStart;
        boolean z2 = true;
        boolean checkForSnake = (Math.abs(oldSize - newSize) & 1) == 1;
        int delta = oldSize - newSize;
        int k = -d;
        while (k <= d) {
            if (k == (-d) || (k != d && CenteredArray.m6942getimpl(forward, k + 1) > CenteredArray.m6942getimpl(forward, k - 1))) {
                int startX2 = CenteredArray.m6942getimpl(forward, k + 1);
                x = startX2;
                startX = startX2;
            } else {
                int startX3 = CenteredArray.m6942getimpl(forward, k - 1);
                x = startX3 + 1;
                startX = startX3;
            }
            int y = (newStart + (x - oldStart)) - k;
            int startY = y - ((d != 0 ? z2 : 0) & (x == startX ? z2 : 0));
            while (x < i && y < newEnd) {
                if (!cb.areItemsTheSame(x, y)) {
                    break;
                }
                x++;
                y++;
            }
            CenteredArray.m6945setimpl(forward, k, x);
            if (checkForSnake) {
                z = z2;
                int backwardsK = delta - k;
                if (backwardsK >= (-d) + 1 && backwardsK <= d - 1) {
                    if (CenteredArray.m6942getimpl(backward, backwardsK) <= x) {
                        fillSnake(startX, startY, x, y, false, snake);
                        return z;
                    }
                }
            } else {
                z = z2;
            }
            k += 2;
            i = oldEnd;
            z2 = z;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: backward-4l5_RBY, reason: not valid java name */
    private static final boolean m7056backward4l5_RBY(int oldStart, int oldEnd, int newStart, int newEnd, DiffCallback cb, int[] forward, int[] backward, int d, int[] snake) {
        int x;
        int startX;
        boolean z;
        int oldSize = oldEnd - oldStart;
        int newSize = newEnd - newStart;
        boolean z2 = true;
        boolean checkForSnake = ((oldSize - newSize) & 1) == 0;
        int delta = oldSize - newSize;
        int k = -d;
        while (k <= d) {
            if (k == (-d) || (k != d && CenteredArray.m6942getimpl(backward, k + 1) < CenteredArray.m6942getimpl(backward, k - 1))) {
                int startX2 = CenteredArray.m6942getimpl(backward, k + 1);
                x = startX2;
                startX = startX2;
            } else {
                int startX3 = CenteredArray.m6942getimpl(backward, k - 1);
                x = startX3 - 1;
                startX = startX3;
            }
            int y = newEnd - ((oldEnd - x) - k);
            int startY = y + ((d != 0 ? z2 : 0) & (x == startX ? z2 : 0));
            int y2 = y;
            int x2 = x;
            while (x2 > oldStart && y2 > newStart) {
                z = z2;
                if (!cb.areItemsTheSame(x2 - 1, y2 - 1)) {
                    break;
                }
                x2--;
                y2--;
                z2 = z;
            }
            z = z2;
            CenteredArray.m6945setimpl(backward, k, x2);
            if (checkForSnake) {
                int forwardsK = delta - k;
                if (forwardsK >= (-d) && forwardsK <= d) {
                    if (CenteredArray.m6942getimpl(forward, forwardsK) >= x2) {
                        fillSnake(x2, y2, startX, startY, true, snake);
                        return z;
                    }
                }
            }
            k += 2;
            z2 = z;
        }
        return false;
    }

    private static final int toInt(boolean z) {
        return z ? 1 : 0;
    }

    public static final void fillSnake(int i, int i2, int i3, int i4, boolean z, int[] iArr) {
        if (iArr.length < 5) {
            return;
        }
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
        iArr[4] = z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(int[] $this$swap, int i, int j) {
        int tmp = $this$swap[i];
        $this$swap[i] = $this$swap[j];
        $this$swap[j] = tmp;
    }
}
