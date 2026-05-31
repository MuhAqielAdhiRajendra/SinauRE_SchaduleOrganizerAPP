package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes13.dex */
interface MessageInfoFactory {
    boolean isSupported(Class<?> cls);

    MessageInfo messageInfoFor(Class<?> cls);
}
