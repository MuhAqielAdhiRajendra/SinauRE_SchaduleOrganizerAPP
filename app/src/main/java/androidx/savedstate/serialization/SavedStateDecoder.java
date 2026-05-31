package androidx.savedstate.serialization;

import android.os.Bundle;
import androidx.savedstate.SavedStateReader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.modules.SerializersModule;

/* JADX INFO: compiled from: SavedStateDecoder.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0012H\u0016J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020(H\u0016J\b\u0010)\u001a\u00020*H\u0016J\b\u0010+\u001a\u00020\rH\u0016J\u0010\u0010,\u001a\u00020\u00122\u0006\u0010-\u001a\u00020\u001aH\u0016J\b\u0010.\u001a\u00020\u001dH\u0016J!\u0010/\u001a\u0002H0\"\u0004\b\u0000\u001002\f\u00101\u001a\b\u0012\u0004\u0012\u0002H002H\u0016¢\u0006\u0002\u00103R\u001a\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u00064"}, d2 = {"Landroidx/savedstate/serialization/SavedStateDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "savedState", "Landroid/os/Bundle;", "Landroidx/savedstate/SavedState;", "configuration", "Landroidx/savedstate/serialization/SavedStateConfiguration;", "<init>", "(Landroid/os/Bundle;Landroidx/savedstate/serialization/SavedStateConfiguration;)V", "getSavedState$savedstate", "()Landroid/os/Bundle;", "Landroid/os/Bundle;", "value", "", "key", "getKey$savedstate", "()Ljava/lang/String;", "index", "", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "decodeElementIndex", "decodeBoolean", "", "decodeByte", "", "decodeShort", "", "decodeInt", "decodeLong", "", "decodeFloat", "", "decodeDouble", "", "decodeChar", "", "decodeString", "decodeEnum", "enumDescriptor", "decodeNotNullMark", "decodeSerializableValue", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "savedstate"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SavedStateDecoder extends AbstractDecoder {
    private final SavedStateConfiguration configuration;
    private int index;
    private String key;
    private final Bundle savedState;

    /* JADX INFO: renamed from: getSavedState$savedstate, reason: from getter */
    public final Bundle getSavedState() {
        return this.savedState;
    }

    public SavedStateDecoder(Bundle savedState, SavedStateConfiguration configuration) {
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

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.configuration.getSerializersModule();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (Intrinsics.areEqual(this.key, "")) {
            return this;
        }
        Bundle $this$read$iv = this.savedState;
        Bundle $this$beginStructure_u24lambda_u240 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return new SavedStateDecoder(SavedStateReader.m8579getSavedStateimpl($this$beginStructure_u24lambda_u240, this.key), this.configuration);
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        int elementCount;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (Intrinsics.areEqual(descriptor.getKind(), StructureKind.LIST.INSTANCE) || Intrinsics.areEqual(descriptor.getKind(), StructureKind.MAP.INSTANCE)) {
            Bundle $this$read$iv = this.savedState;
            Bundle $this$decodeElementIndex_u24lambda_u241 = SavedStateReader.m8522constructorimpl($this$read$iv);
            elementCount = SavedStateReader.m8602sizeimpl($this$decodeElementIndex_u24lambda_u241);
        } else {
            elementCount = descriptor.getElementsCount();
        }
        while (this.index < elementCount) {
            String elementName = descriptor.getElementName(this.index);
            if (descriptor.isElementOptional(this.index)) {
                Bundle $this$read$iv2 = this.savedState;
                Bundle $this$decodeElementIndex_u24lambda_u242 = SavedStateReader.m8522constructorimpl($this$read$iv2);
                if (!SavedStateReader.m8523containsimpl($this$decodeElementIndex_u24lambda_u242, elementName)) {
                    this.index++;
                }
            }
            this.key = elementName;
            int i = this.index;
            this.index = i + 1;
            return i;
        }
        return -1;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeBoolean_u24lambda_u243 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8531getBooleanimpl($this$decodeBoolean_u24lambda_u243, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeByte_u24lambda_u244 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return (byte) SavedStateReader.m8553getIntimpl($this$decodeByte_u24lambda_u244, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeShort_u24lambda_u245 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return (short) SavedStateReader.m8553getIntimpl($this$decodeShort_u24lambda_u245, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeInt_u24lambda_u246 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8553getIntimpl($this$decodeInt_u24lambda_u246, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeLong_u24lambda_u247 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8563getLongimpl($this$decodeLong_u24lambda_u247, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeFloat_u24lambda_u248 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8549getFloatimpl($this$decodeFloat_u24lambda_u248, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeDouble_u24lambda_u249 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8545getDoubleimpl($this$decodeDouble_u24lambda_u249, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeChar_u24lambda_u2410 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8535getCharimpl($this$decodeChar_u24lambda_u2410, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeString_u24lambda_u2411 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8593getStringimpl($this$decodeString_u24lambda_u2411, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeEnum_u24lambda_u2412 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return SavedStateReader.m8553getIntimpl($this$decodeEnum_u24lambda_u2412, this.key);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        Bundle $this$read$iv = this.savedState;
        Bundle $this$decodeNotNullMark_u24lambda_u2413 = SavedStateReader.m8522constructorimpl($this$read$iv);
        return !SavedStateReader.m8601isNullimpl($this$decodeNotNullMark_u24lambda_u2413, this.key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        T t = (T) SavedStateDecoder_androidKt.decodeFormatSpecificTypesOnPlatform(this, deserializer);
        if (t != null) {
            return t;
        }
        SerialDescriptor descriptor = deserializer.getDescriptor();
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getIntListDescriptor())) {
            return (T) SavedStateReader.m8556getIntListimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getStringListDescriptor())) {
            return (T) SavedStateReader.m8596getStringListimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getBooleanArrayDescriptor())) {
            return (T) SavedStateReader.m8532getBooleanArrayimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getCharArrayDescriptor())) {
            return (T) SavedStateReader.m8536getCharArrayimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getDoubleArrayDescriptor())) {
            return (T) SavedStateReader.m8546getDoubleArrayimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getFloatArrayDescriptor())) {
            return (T) SavedStateReader.m8550getFloatArrayimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getIntArrayDescriptor())) {
            return (T) SavedStateReader.m8554getIntArrayimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getLongArrayDescriptor())) {
            return (T) SavedStateReader.m8564getLongArrayimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        if (Intrinsics.areEqual(descriptor, SavedStateCodecUtilsKt.getStringArrayDescriptor())) {
            return (T) SavedStateReader.m8594getStringArrayimpl(SavedStateReader.m8522constructorimpl(this.savedState), this.key);
        }
        return (T) super.decodeSerializableValue(deserializer);
    }
}
