package com.example.scheduleorganizer.util;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.PreferenceDataStoreDelegateKt;
import androidx.datastore.preferences.core.Preferences;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: ChatHistoryStore.kt */
/* JADX INFO: loaded from: classes8.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005¨\u0006\b"}, d2 = {"chatDataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getChatDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "chatDataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "app"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ChatHistoryStoreKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl(ChatHistoryStoreKt.class, "chatDataStore", "getChatDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", 1)};
    private static final ReadOnlyProperty chatDataStore$delegate = PreferenceDataStoreDelegateKt.preferencesDataStore$default("ai_chat_prefs", null, null, null, 14, null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final DataStore<Preferences> getChatDataStore(Context $this$chatDataStore) {
        return (DataStore) chatDataStore$delegate.getValue($this$chatDataStore, $$delegatedProperties[0]);
    }
}
