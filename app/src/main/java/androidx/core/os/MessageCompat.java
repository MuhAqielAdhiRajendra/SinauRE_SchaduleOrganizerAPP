package androidx.core.os;

import android.os.Message;

/* JADX INFO: loaded from: classes12.dex */
public final class MessageCompat {
    public static void setAsynchronous(Message message, boolean async) {
        message.setAsynchronous(async);
    }

    public static boolean isAsynchronous(Message message) {
        return message.isAsynchronous();
    }

    private MessageCompat() {
    }
}
