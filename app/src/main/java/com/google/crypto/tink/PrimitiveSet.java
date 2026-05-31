package com.google.crypto.tink;

import com.google.crypto.tink.internal.MutableSerializationRegistry;
import com.google.crypto.tink.internal.ProtoKeySerialization;
import com.google.crypto.tink.monitoring.MonitoringAnnotations;
import com.google.crypto.tink.proto.KeyStatusType;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.proto.OutputPrefixType;
import com.google.crypto.tink.subtle.Hex;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
public final class PrimitiveSet<P> {
    private final MonitoringAnnotations annotations;
    private final boolean isMutable;
    private Entry<P> primary;
    private final Class<P> primitiveClass;
    private final ConcurrentMap<Prefix, List<Entry<P>>> primitives;

    public static final class Entry<P> {

        @Nullable
        private final P fullPrimitive;
        private final byte[] identifier;
        private final Key key;
        private final int keyId;
        private final String keyType;
        private final OutputPrefixType outputPrefixType;

        @Nullable
        private final P primitive;
        private final KeyStatusType status;

        Entry(@Nullable P fullPrimitive, @Nullable P primitive, final byte[] identifier, KeyStatusType status, OutputPrefixType outputPrefixType, int keyId, String keyType, Key key) {
            this.fullPrimitive = fullPrimitive;
            this.primitive = primitive;
            this.identifier = Arrays.copyOf(identifier, identifier.length);
            this.status = status;
            this.outputPrefixType = outputPrefixType;
            this.keyId = keyId;
            this.keyType = keyType;
            this.key = key;
        }

        @Nullable
        public P getFullPrimitive() {
            return this.fullPrimitive;
        }

        @Nullable
        public P getPrimitive() {
            return this.primitive;
        }

        public KeyStatusType getStatus() {
            return this.status;
        }

        public OutputPrefixType getOutputPrefixType() {
            return this.outputPrefixType;
        }

        @Nullable
        public final byte[] getIdentifier() {
            if (this.identifier == null) {
                return null;
            }
            return Arrays.copyOf(this.identifier, this.identifier.length);
        }

        public int getKeyId() {
            return this.keyId;
        }

        public String getKeyType() {
            return this.keyType;
        }

        public Key getKey() {
            return this.key;
        }

        @Nullable
        public Parameters getParameters() {
            if (this.key == null) {
                return null;
            }
            return this.key.getParameters();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <P> Entry<P> addEntryToMap(@Nullable P fullPrimitive, @Nullable P primitive, Keyset.Key key, ConcurrentMap<Prefix, List<Entry<P>>> primitives) throws GeneralSecurityException {
        Integer idRequirement = Integer.valueOf(key.getKeyId());
        if (key.getOutputPrefixType() == OutputPrefixType.RAW) {
            idRequirement = null;
        }
        Key keyObject = MutableSerializationRegistry.globalInstance().parseKeyWithLegacyFallback(ProtoKeySerialization.create(key.getKeyData().getTypeUrl(), key.getKeyData().getValue(), key.getKeyData().getKeyMaterialType(), key.getOutputPrefixType(), idRequirement), InsecureSecretKeyAccess.get());
        Entry<P> entry = new Entry<>(fullPrimitive, primitive, CryptoFormat.getOutputPrefix(key), key.getStatus(), key.getOutputPrefixType(), key.getKeyId(), key.getKeyData().getTypeUrl(), keyObject);
        List<Entry<P>> list = new ArrayList<>();
        list.add(entry);
        Prefix identifier = new Prefix(entry.getIdentifier());
        Collection<? extends Entry<P>> existing = (List) primitives.put(identifier, Collections.unmodifiableList(list));
        if (existing != null) {
            List<Entry<P>> newList = new ArrayList<>();
            newList.addAll(existing);
            newList.add(entry);
            primitives.put(identifier, Collections.unmodifiableList(newList));
        }
        return entry;
    }

    @Nullable
    public Entry<P> getPrimary() {
        return this.primary;
    }

    public boolean hasAnnotations() {
        return !this.annotations.toMap().isEmpty();
    }

    public MonitoringAnnotations getAnnotations() {
        return this.annotations;
    }

    public List<Entry<P>> getRawPrimitives() {
        return getPrimitive(CryptoFormat.RAW_PREFIX);
    }

    public List<Entry<P>> getPrimitive(final byte[] identifier) {
        List<Entry<P>> found = this.primitives.get(new Prefix(identifier));
        return found != null ? found : Collections.emptyList();
    }

    public Collection<List<Entry<P>>> getAll() {
        return this.primitives.values();
    }

    private PrimitiveSet(Class<P> primitiveClass) {
        this.primitives = new ConcurrentHashMap();
        this.primitiveClass = primitiveClass;
        this.annotations = MonitoringAnnotations.EMPTY;
        this.isMutable = true;
    }

    private PrimitiveSet(ConcurrentMap<Prefix, List<Entry<P>>> primitives, Entry<P> primary, MonitoringAnnotations annotations, Class<P> primitiveClass) {
        this.primitives = primitives;
        this.primary = primary;
        this.primitiveClass = primitiveClass;
        this.annotations = annotations;
        this.isMutable = false;
    }

    @Deprecated
    public static <P> PrimitiveSet<P> newPrimitiveSet(Class<P> primitiveClass) {
        return new PrimitiveSet<>(primitiveClass);
    }

    @Deprecated
    public void setPrimary(final Entry<P> primary) {
        if (!this.isMutable) {
            throw new IllegalStateException("setPrimary cannot be called on an immutable primitive set");
        }
        if (primary == null) {
            throw new IllegalArgumentException("the primary entry must be non-null");
        }
        if (primary.getStatus() != KeyStatusType.ENABLED) {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
        List<Entry<P>> entries = getPrimitive(primary.getIdentifier());
        if (entries.isEmpty()) {
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        }
        this.primary = primary;
    }

    @Deprecated
    public Entry<P> addPrimitive(final P primitive, Keyset.Key key) throws GeneralSecurityException {
        if (!this.isMutable) {
            throw new IllegalStateException("addPrimitive cannot be called on an immutable primitive set");
        }
        if (key.getStatus() != KeyStatusType.ENABLED) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
        return addEntryToMap(null, primitive, key, this.primitives);
    }

    public Class<P> getPrimitiveClass() {
        return this.primitiveClass;
    }

    private static class Prefix implements Comparable<Prefix> {
        private final byte[] prefix;

        private Prefix(byte[] prefix) {
            this.prefix = Arrays.copyOf(prefix, prefix.length);
        }

        public int hashCode() {
            return Arrays.hashCode(this.prefix);
        }

        public boolean equals(Object o) {
            if (!(o instanceof Prefix)) {
                return false;
            }
            Prefix other = (Prefix) o;
            return Arrays.equals(this.prefix, other.prefix);
        }

        @Override // java.lang.Comparable
        public int compareTo(Prefix o) {
            if (this.prefix.length != o.prefix.length) {
                return this.prefix.length - o.prefix.length;
            }
            for (int i = 0; i < this.prefix.length; i++) {
                if (this.prefix[i] != o.prefix[i]) {
                    return this.prefix[i] - o.prefix[i];
                }
            }
            return 0;
        }

        public String toString() {
            return Hex.encode(this.prefix);
        }
    }

    public static class Builder<P> {
        private MonitoringAnnotations annotations;
        private Entry<P> primary;
        private final Class<P> primitiveClass;
        private ConcurrentMap<Prefix, List<Entry<P>>> primitives;

        private Builder<P> addPrimitive(@Nullable final P fullPrimitive, @Nullable final P primitive, Keyset.Key key, boolean asPrimary) throws GeneralSecurityException {
            if (this.primitives == null) {
                throw new IllegalStateException("addPrimitive cannot be called after build");
            }
            if (fullPrimitive == null && primitive == null) {
                throw new GeneralSecurityException("at least one of the `fullPrimitive` or `primitive` must be set");
            }
            if (key.getStatus() == KeyStatusType.ENABLED) {
                Entry<P> entry = PrimitiveSet.addEntryToMap(fullPrimitive, primitive, key, this.primitives);
                if (asPrimary) {
                    if (this.primary != null) {
                        throw new IllegalStateException("you cannot set two primary primitives");
                    }
                    this.primary = entry;
                }
                return this;
            }
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }

        public Builder<P> addPrimitive(final P primitive, Keyset.Key key) throws GeneralSecurityException {
            return addPrimitive(null, primitive, key, false);
        }

        public Builder<P> addPrimaryPrimitive(final P primitive, Keyset.Key key) throws GeneralSecurityException {
            return addPrimitive(null, primitive, key, true);
        }

        public Builder<P> addFullPrimitiveAndOptionalPrimitive(@Nullable final P fullPrimitive, @Nullable final P primitive, Keyset.Key key) throws GeneralSecurityException {
            return addPrimitive(fullPrimitive, primitive, key, false);
        }

        public Builder<P> addPrimaryFullPrimitiveAndOptionalPrimitive(@Nullable final P fullPrimitive, @Nullable final P primitive, Keyset.Key key) throws GeneralSecurityException {
            return addPrimitive(fullPrimitive, primitive, key, true);
        }

        public Builder<P> setAnnotations(MonitoringAnnotations annotations) {
            if (this.primitives == null) {
                throw new IllegalStateException("setAnnotations cannot be called after build");
            }
            this.annotations = annotations;
            return this;
        }

        public PrimitiveSet<P> build() throws GeneralSecurityException {
            if (this.primitives == null) {
                throw new IllegalStateException("build cannot be called twice");
            }
            PrimitiveSet<P> output = new PrimitiveSet<>(this.primitives, this.primary, this.annotations, this.primitiveClass);
            this.primitives = null;
            return output;
        }

        private Builder(Class<P> primitiveClass) {
            this.primitives = new ConcurrentHashMap();
            this.primitiveClass = primitiveClass;
            this.annotations = MonitoringAnnotations.EMPTY;
        }
    }

    public static <P> Builder<P> newBuilder(Class<P> primitiveClass) {
        return new Builder<>(primitiveClass);
    }
}
