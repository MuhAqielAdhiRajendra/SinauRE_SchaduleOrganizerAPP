package androidx.core.os;

import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: classes12.dex */
public final class EnvironmentCompat {

    @Deprecated
    public static final String MEDIA_UNKNOWN = "unknown";

    public static String getStorageState(File path) {
        return Environment.getExternalStorageState(path);
    }

    private EnvironmentCompat() {
    }
}
