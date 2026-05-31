package androidx.savedstate.serialization;

import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: SavedStateRegistryOwnerDelegate.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aP\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u0002*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\b\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\nH\u0087\bø\u0001\u0000\u001aQ\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0006\b\u0000\u0010\u0003\u0018\u0001*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\b\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\nH\u0087\bø\u0001\u0000¢\u0006\u0002\b\u000b\u001aV\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0002*\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00030\r2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\nH\u0007\u001aW\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0003*\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00030\r2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00030\nH\u0007¢\u0006\u0002\b\u000b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000e"}, d2 = {"saved", "Lkotlin/properties/ReadWriteProperty;", "", "T", "Landroidx/savedstate/SavedStateRegistryOwner;", "key", "", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "init", "Lkotlin/Function0;", "savedNullable", "serializer", "Lkotlinx/serialization/KSerializer;", "savedstate"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SavedStateRegistryOwnerDelegateKt {
    public static /* synthetic */ ReadWriteProperty saved$default(SavedStateRegistryOwner $this$saved_u24default, String key, SavedStateConfiguration configuration, Function0 init, int i, Object obj) {
        if ((i & 1) != 0) {
            key = null;
        }
        if ((i & 2) != 0) {
            configuration = SavedStateConfiguration.DEFAULT;
        }
        Intrinsics.checkNotNullParameter($this$saved_u24default, "<this>");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(init, "init");
        SerializersModule serializersModule = configuration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return savedNullable($this$saved_u24default, SerializersKt.serializer(serializersModule, (KType) null), key, configuration, init);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the new 'saved' overload that supports both nullable and non-nullable types.")
    public static final /* synthetic */ <T> ReadWriteProperty<Object, T> saved(SavedStateRegistryOwner $this$saved, String key, SavedStateConfiguration configuration, Function0<? extends T> init) {
        Intrinsics.checkNotNullParameter($this$saved, "<this>");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(init, "init");
        SerializersModule serializersModule = configuration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return savedNullable($this$saved, SerializersKt.serializer(serializersModule, (KType) null), key, configuration, init);
    }

    public static /* synthetic */ ReadWriteProperty savedNullable$default(SavedStateRegistryOwner $this$saved_u24default, String key, SavedStateConfiguration configuration, Function0 init, int i, Object obj) {
        if ((i & 1) != 0) {
            key = null;
        }
        if ((i & 2) != 0) {
            configuration = SavedStateConfiguration.DEFAULT;
        }
        Intrinsics.checkNotNullParameter($this$saved_u24default, "<this>");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(init, "init");
        SerializersModule serializersModule = configuration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return savedNullable($this$saved_u24default, SerializersKt.serializer(serializersModule, (KType) null), key, configuration, init);
    }

    public static final /* synthetic */ <T> ReadWriteProperty<Object, T> savedNullable(SavedStateRegistryOwner $this$saved, String key, SavedStateConfiguration configuration, Function0<? extends T> init) {
        Intrinsics.checkNotNullParameter($this$saved, "<this>");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(init, "init");
        SerializersModule serializersModule = configuration.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall("kotlinx.serialization.serializer.withModule");
        return savedNullable($this$saved, SerializersKt.serializer(serializersModule, (KType) null), key, configuration, init);
    }

    public static /* synthetic */ ReadWriteProperty saved$default(SavedStateRegistryOwner savedStateRegistryOwner, KSerializer kSerializer, String str, SavedStateConfiguration savedStateConfiguration, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        return saved(savedStateRegistryOwner, kSerializer, str, savedStateConfiguration, function0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the new 'saved' overload that supports both nullable and non-nullable types.")
    public static final /* synthetic */ ReadWriteProperty saved(SavedStateRegistryOwner $this$saved, KSerializer serializer, String key, SavedStateConfiguration configuration, Function0 init) {
        Intrinsics.checkNotNullParameter($this$saved, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(init, "init");
        return new SavedStateRegistryOwnerDelegate($this$saved.getSavedStateRegistry(), serializer, key, configuration, init);
    }

    public static /* synthetic */ ReadWriteProperty savedNullable$default(SavedStateRegistryOwner savedStateRegistryOwner, KSerializer kSerializer, String str, SavedStateConfiguration savedStateConfiguration, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            savedStateConfiguration = SavedStateConfiguration.DEFAULT;
        }
        return savedNullable(savedStateRegistryOwner, kSerializer, str, savedStateConfiguration, function0);
    }

    public static final <T> ReadWriteProperty<Object, T> savedNullable(SavedStateRegistryOwner $this$saved, KSerializer<T> serializer, String key, SavedStateConfiguration configuration, Function0<? extends T> init) {
        Intrinsics.checkNotNullParameter($this$saved, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(init, "init");
        return new SavedStateRegistryOwnerDelegate($this$saved.getSavedStateRegistry(), serializer, key, configuration, init);
    }
}
