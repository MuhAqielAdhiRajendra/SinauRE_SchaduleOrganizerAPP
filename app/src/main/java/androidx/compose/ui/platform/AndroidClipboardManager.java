package androidx.compose.ui.platform;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.os.Build;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidClipboardManager.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u000eH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0013H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0016\u001a\u00060\u0007j\u0002`\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\n¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/platform/AndroidClipboardManager;", "Landroidx/compose/ui/platform/ClipboardManager;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "_clipboardManager", "Landroid/content/ClipboardManager;", "clipboardManager", "getClipboardManager", "()Landroid/content/ClipboardManager;", "setText", "", "annotatedString", "Landroidx/compose/ui/text/AnnotatedString;", "getText", "hasText", "", "getClip", "Landroidx/compose/ui/platform/ClipEntry;", "setClip", "clipEntry", "nativeClipboard", "Landroidx/compose/ui/platform/NativeClipboard;", "getNativeClipboard", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidClipboardManager implements ClipboardManager {
    public static final int $stable = 8;
    private android.content.ClipboardManager _clipboardManager;
    private final Context context;

    public AndroidClipboardManager(Context context) {
        this.context = context;
    }

    private final android.content.ClipboardManager getClipboardManager() {
        android.content.ClipboardManager clipboardManager = this._clipboardManager;
        if (clipboardManager == null) {
            Object systemService = this.context.getSystemService("clipboard");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.content.ClipboardManager");
            android.content.ClipboardManager clipboardManager2 = (android.content.ClipboardManager) systemService;
            this._clipboardManager = clipboardManager2;
            return clipboardManager2;
        }
        return clipboardManager;
    }

    @Override // androidx.compose.ui.platform.ClipboardManager
    public void setText(AnnotatedString annotatedString) {
        getClipboardManager().setPrimaryClip(ClipData.newPlainText("plain text", AndroidClipboardManager_androidKt.convertToCharSequence(annotatedString)));
    }

    @Override // androidx.compose.ui.platform.ClipboardManager
    public AnnotatedString getText() {
        ClipData primaryClip = getClipboardManager().getPrimaryClip();
        if (primaryClip == null || primaryClip.getItemCount() <= 0) {
            return null;
        }
        ClipData.Item itemAt = primaryClip.getItemAt(0);
        return AndroidClipboardManager_androidKt.convertToAnnotatedString(itemAt != null ? itemAt.getText() : null);
    }

    @Override // androidx.compose.ui.platform.ClipboardManager
    public boolean hasText() {
        ClipDescription primaryClipDescription = getClipboardManager().getPrimaryClipDescription();
        if (primaryClipDescription != null) {
            return primaryClipDescription.hasMimeType("text/*");
        }
        return false;
    }

    @Override // androidx.compose.ui.platform.ClipboardManager
    public ClipEntry getClip() {
        ClipData p0 = getClipboardManager().getPrimaryClip();
        if (p0 != null) {
            return new ClipEntry(p0);
        }
        return null;
    }

    @Override // androidx.compose.ui.platform.ClipboardManager
    public void setClip(ClipEntry clipEntry) {
        if (clipEntry == null) {
            if (Build.VERSION.SDK_INT >= 28) {
                Api28ClipboardManagerClipClear.clearPrimaryClip(getClipboardManager());
                return;
            } else {
                getClipboardManager().setPrimaryClip(ClipData.newPlainText("", ""));
                return;
            }
        }
        getClipboardManager().setPrimaryClip(clipEntry.getClipData());
    }

    @Override // androidx.compose.ui.platform.ClipboardManager
    public android.content.ClipboardManager getNativeClipboard() {
        return getClipboardManager();
    }
}
