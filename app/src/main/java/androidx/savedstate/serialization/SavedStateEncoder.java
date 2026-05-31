package androidx.savedstate.serialization;

import android.os.Bundle;
import androidx.core.os.BundleKt;
import androidx.savedstate.SavedStateReader;
import androidx.savedstate.SavedStateWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: SavedStateEncoder.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J)\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004H\u0002¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010!\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u0016H\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u001aH\u0016J\u0010\u0010'\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0018\u00100\u001a\u00020\u001e2\u0006\u00101\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u00102\u001a\u00020\u001eH\u0016J)\u00103\u001a\u00020\u001e\"\u0004\b\u0000\u001042\f\u00105\u001a\b\u0012\u0004\u0012\u0002H4062\u0006\u0010\f\u001a\u0002H4H\u0016¢\u0006\u0002\u00107R\u001a\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u00068"}, d2 = {"Landroidx/savedstate/serialization/SavedStateEncoder;", "Lkotlinx/serialization/encoding/AbstractEncoder;", "savedState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "<init>", "(Landroid/os/Bundle;Landroidx/savedstate/serialization/SavedStateConfiguration;)V", "getSavedState$savedstate", "()Landroid/os/Bundle;", "Landroid/os/Bundle;", "value", "", "key", "getKey$savedstate", "()Ljava/lang/String;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "shouldEncodeElementDefault", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "index", "", "beginStructure", "Lkotlinx/serialization/encoding/CompositeEncoder;", "putClassDiscriminatorIfRequired", "", "(Landroidx/savedstate/serialization/SavedStateConfiguration;Lkotlinx/serialization/descriptors/SerialDescriptor;Landroid/os/Bundle;)V", "encodeElement", "encodeBoolean", "encodeByte", "", "encodeShort", "", "encodeInt", "encodeLong", "", "encodeFloat", "", "encodeDouble", "", "encodeChar", "", "encodeString", "encodeEnum", "enumDescriptor", "encodeNull", "encodeSerializableValue", "T", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SavedStateEncoder extends AbstractEncoder {
    private final SavedStateConfiguration configuration;
    private String key;
    private final Bundle savedState;

    /* JADX INFO: renamed from: getSavedState$savedstate, reason: from getter */
    public final Bundle getSavedState() {
        return this.savedState;
    }

    public SavedStateEncoder(Bundle savedState, SavedStateConfiguration configuration) {
        Intrinsics.checkNotNullParameter(savedState, "savedState");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        this.savedState = savedState;
        this.configuration = configuration;
        this.key = "";
    }

    /* JADX INFO: renamed from: getKey$savedstate, reason: from getter */
    public final String getKey() {
        return this.key;
    }

    @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    public SerializersModule getSerializersModule() {
        return this.configuration.getSerializersModule();
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this.configuration.getEncodeDefaults();
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginStructure(SerialDescriptor descriptor) {
        Pair[] pairs$iv;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (Intrinsics.areEqual(this.key, "")) {
            putClassDiscriminatorIfRequired(this.configuration, descriptor, this.savedState);
            return this;
        }
        Map initialState$iv = MapsKt.emptyMap();
        if (initialState$iv.isEmpty()) {
            pairs$iv = new Pair[0];
        } else {
            Collection destination$iv$iv$iv = new ArrayList(initialState$iv.size());
            for (Map.Entry item$iv$iv$iv : initialState$iv.entrySet()) {
                String key$iv = (String) item$iv$iv$iv.getKey();
                Object value$iv = item$iv$iv$iv.getValue();
                destination$iv$iv$iv.add(TuplesKt.to(key$iv, value$iv));
            }
            Collection $this$toTypedArray$iv$iv = (List) destination$iv$iv$iv;
            pairs$iv = (Pair[]) $this$toTypedArray$iv$iv.toArray(new Pair[0]);
        }
        Bundle childState = BundleKt.bundleOf((Pair[]) Arrays.copyOf(pairs$iv, pairs$iv.length));
        SavedStateWriter.m8608constructorimpl(childState);
        Bundle $this$write$iv = this.savedState;
        Bundle $this$beginStructure_u24lambda_u240 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8635putSavedStateimpl($this$beginStructure_u24lambda_u240, this.key, childState);
        putClassDiscriminatorIfRequired(this.configuration, descriptor, childState);
        return new SavedStateEncoder(childState, this.configuration);
    }

    private final void putClassDiscriminatorIfRequired(SavedStateConfiguration configuration, SerialDescriptor descriptor, Bundle savedState) {
        if (configuration.getClassDiscriminatorMode() != 1) {
            return;
        }
        Bundle $this$putClassDiscriminatorIfRequired_u24lambda_u241 = SavedStateReader.m8522constructorimpl(savedState);
        if (SavedStateReader.m8523containsimpl($this$putClassDiscriminatorIfRequired_u24lambda_u241, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY)) {
            return;
        }
        if (!Intrinsics.areEqual(descriptor.getKind(), StructureKind.CLASS.INSTANCE) && !Intrinsics.areEqual(descriptor.getKind(), StructureKind.OBJECT.INSTANCE)) {
            return;
        }
        Bundle $this$putClassDiscriminatorIfRequired_u24lambda_u242 = SavedStateWriter.m8608constructorimpl(savedState);
        SavedStateWriter.m8641putStringimpl($this$putClassDiscriminatorIfRequired_u24lambda_u242, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, descriptor.getSerialName());
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder
    public boolean encodeElement(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.key = descriptor.getElementName(index);
        if (this.configuration.getClassDiscriminatorMode() == 1) {
            Bundle $this$read$iv = this.savedState;
            Bundle $this$encodeElement_u24lambda_u243 = SavedStateReader.m8522constructorimpl($this$read$iv);
            boolean hasClassDiscriminator = SavedStateReader.m8523containsimpl($this$encodeElement_u24lambda_u243, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
            boolean hasConflictingElementName = Intrinsics.areEqual(this.key, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
            if (hasClassDiscriminator && hasConflictingElementName) {
                Bundle $this$read$iv2 = this.savedState;
                Bundle $this$encodeElement_u24lambda_u244 = SavedStateReader.m8522constructorimpl($this$read$iv2);
                String classDiscriminator = SavedStateReader.m8593getStringimpl($this$encodeElement_u24lambda_u244, ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY);
                throw new IllegalArgumentException("SavedStateEncoder for " + classDiscriminator + " has property '" + this.key + "' that conflicts with the class discriminator. You can rename a property with @SerialName annotation.");
            }
        }
        return true;
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeBoolean(boolean value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeBoolean_u24lambda_u245 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8614putBooleanimpl($this$encodeBoolean_u24lambda_u245, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeByte(byte value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeByte_u24lambda_u246 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8625putIntimpl($this$encodeByte_u24lambda_u246, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeShort(short value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeShort_u24lambda_u247 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8625putIntimpl($this$encodeShort_u24lambda_u247, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeInt(int value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeInt_u24lambda_u248 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8625putIntimpl($this$encodeInt_u24lambda_u248, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeLong(long value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeLong_u24lambda_u249 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8629putLongimpl($this$encodeLong_u24lambda_u249, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeFloat(float value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeFloat_u24lambda_u2410 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8623putFloatimpl($this$encodeFloat_u24lambda_u2410, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeDouble(double value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeDouble_u24lambda_u2411 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8621putDoubleimpl($this$encodeDouble_u24lambda_u2411, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeChar(char value) {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeChar_u24lambda_u2412 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8616putCharimpl($this$encodeChar_u24lambda_u2412, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeString(String value) {
        Intrinsics.checkNotNullParameter(value, "value");
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeString_u24lambda_u2413 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8641putStringimpl($this$encodeString_u24lambda_u2413, this.key, value);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeEnum(SerialDescriptor enumDescriptor, int index) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeEnum_u24lambda_u2414 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8625putIntimpl($this$encodeEnum_u24lambda_u2414, this.key, index);
    }

    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        Bundle $this$write$iv = this.savedState;
        Bundle $this$encodeNull_u24lambda_u2415 = SavedStateWriter.m8608constructorimpl($this$write$iv);
        SavedStateWriter.m8631putNullimpl($this$encodeNull_u24lambda_u2415, this.key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
    public <T> void encodeSerializableValue(SerializationStrategy<? super T> serializer, T value) {
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        boolean platformEncoded = SavedStateEncoder_androidKt.encodeFormatSpecificTypesOnPlatform(this, serializer, value);
        if (platformEncoded) {
            return;
        }
        SerialDescriptor descriptor = serializer.getDescriptor();
        if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getIntListDescriptor())) {
            if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getStringListDescriptor())) {
                if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getBooleanArrayDescriptor())) {
                    Bundle $this$write$iv = this.savedState;
                    Bundle $this$encodeSerializableValue_u24lambda_u2418 = SavedStateWriter.m8608constructorimpl($this$write$iv);
                    String str = this.key;
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.BooleanArray");
                    SavedStateWriter.m8615putBooleanArrayimpl($this$encodeSerializableValue_u24lambda_u2418, str, (boolean[]) value);
                    return;
                }
                if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getCharArrayDescriptor())) {
                    if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getDoubleArrayDescriptor())) {
                        if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getFloatArrayDescriptor())) {
                            if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getIntArrayDescriptor())) {
                                if (!Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getLongArrayDescriptor())) {
                                    if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getStringArrayDescriptor())) {
                                        Bundle $this$write$iv2 = this.savedState;
                                        Bundle $this$encodeSerializableValue_u24lambda_u2424 = SavedStateWriter.m8608constructorimpl($this$write$iv2);
                                        String str2 = this.key;
                                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.Array<kotlin.String>");
                                        SavedStateWriter.m8642putStringArrayimpl($this$encodeSerializableValue_u24lambda_u2424, str2, (String[]) value);
                                        return;
                                    }
                                    super.encodeSerializableValue(serializer, value);
                                    return;
                                }
                                Bundle $this$write$iv3 = this.savedState;
                                Bundle $this$encodeSerializableValue_u24lambda_u2423 = SavedStateWriter.m8608constructorimpl($this$write$iv3);
                                String str3 = this.key;
                                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.LongArray");
                                SavedStateWriter.m8630putLongArrayimpl($this$encodeSerializableValue_u24lambda_u2423, str3, (long[]) value);
                                return;
                            }
                            Bundle $this$write$iv4 = this.savedState;
                            Bundle $this$encodeSerializableValue_u24lambda_u2422 = SavedStateWriter.m8608constructorimpl($this$write$iv4);
                            String str4 = this.key;
                            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.IntArray");
                            SavedStateWriter.m8626putIntArrayimpl($this$encodeSerializableValue_u24lambda_u2422, str4, (int[]) value);
                            return;
                        }
                        Bundle $this$write$iv5 = this.savedState;
                        Bundle $this$encodeSerializableValue_u24lambda_u2421 = SavedStateWriter.m8608constructorimpl($this$write$iv5);
                        String str5 = this.key;
                        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.FloatArray");
                        SavedStateWriter.m8624putFloatArrayimpl($this$encodeSerializableValue_u24lambda_u2421, str5, (float[]) value);
                        return;
                    }
                    Bundle $this$write$iv6 = this.savedState;
                    Bundle $this$encodeSerializableValue_u24lambda_u2420 = SavedStateWriter.m8608constructorimpl($this$write$iv6);
                    String str6 = this.key;
                    Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.DoubleArray");
                    SavedStateWriter.m8622putDoubleArrayimpl($this$encodeSerializableValue_u24lambda_u2420, str6, (double[]) value);
                    return;
                }
                Bundle $this$write$iv7 = this.savedState;
                Bundle $this$encodeSerializableValue_u24lambda_u2419 = SavedStateWriter.m8608constructorimpl($this$write$iv7);
                String str7 = this.key;
                Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.CharArray");
                SavedStateWriter.m8617putCharArrayimpl($this$encodeSerializableValue_u24lambda_u2419, str7, (char[]) value);
                return;
            }
            Bundle $this$write$iv8 = this.savedState;
            Bundle $this$encodeSerializableValue_u24lambda_u2417 = SavedStateWriter.m8608constructorimpl($this$write$iv8);
            String str8 = this.key;
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>");
            SavedStateWriter.m8643putStringListimpl($this$encodeSerializableValue_u24lambda_u2417, str8, (List) value);
            return;
        }
        Bundle $this$write$iv9 = this.savedState;
        Bundle $this$encodeSerializableValue_u24lambda_u2416 = SavedStateWriter.m8608constructorimpl($this$write$iv9);
        String str9 = this.key;
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Int>");
        SavedStateWriter.m8627putIntListimpl($this$encodeSerializableValue_u24lambda_u2416, str9, (List) value);
    }
}
