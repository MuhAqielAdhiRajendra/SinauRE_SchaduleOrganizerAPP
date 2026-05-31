package com.google.crypto.tink.internal;

import com.google.crypto.tink.Key;
import com.google.crypto.tink.Parameters;
import com.google.crypto.tink.SecretKeyAccess;
import com.google.crypto.tink.util.Bytes;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
public final class SerializationRegistry {
    private final Map<ParserIndex, KeyParser<?>> keyParserMap;
    private final Map<SerializerIndex, KeySerializer<?, ?>> keySerializerMap;
    private final Map<ParserIndex, ParametersParser<?>> parametersParserMap;
    private final Map<SerializerIndex, ParametersSerializer<?, ?>> parametersSerializerMap;

    public static final class Builder {
        private final Map<ParserIndex, KeyParser<?>> keyParserMap;
        private final Map<SerializerIndex, KeySerializer<?, ?>> keySerializerMap;
        private final Map<ParserIndex, ParametersParser<?>> parametersParserMap;
        private final Map<SerializerIndex, ParametersSerializer<?, ?>> parametersSerializerMap;

        public Builder() {
            this.keySerializerMap = new HashMap();
            this.keyParserMap = new HashMap();
            this.parametersSerializerMap = new HashMap();
            this.parametersParserMap = new HashMap();
        }

        public Builder(SerializationRegistry registry) {
            this.keySerializerMap = new HashMap(registry.keySerializerMap);
            this.keyParserMap = new HashMap(registry.keyParserMap);
            this.parametersSerializerMap = new HashMap(registry.parametersSerializerMap);
            this.parametersParserMap = new HashMap(registry.parametersParserMap);
        }

        public <KeyT extends Key, SerializationT extends Serialization> Builder registerKeySerializer(KeySerializer<KeyT, SerializationT> serializer) throws GeneralSecurityException {
            SerializerIndex index = new SerializerIndex(serializer.getKeyClass(), serializer.getSerializationClass());
            boolean zContainsKey = this.keySerializerMap.containsKey(index);
            Map<SerializerIndex, KeySerializer<?, ?>> map = this.keySerializerMap;
            if (zContainsKey) {
                KeySerializer<?, ?> existingSerializer = map.get(index);
                if (!existingSerializer.equals(serializer) || !serializer.equals(existingSerializer)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + index);
                }
            } else {
                map.put(index, serializer);
            }
            return this;
        }

        public <SerializationT extends Serialization> Builder registerKeyParser(KeyParser<SerializationT> parser) throws GeneralSecurityException {
            ParserIndex index = new ParserIndex(parser.getSerializationClass(), parser.getObjectIdentifier());
            boolean zContainsKey = this.keyParserMap.containsKey(index);
            Map<ParserIndex, KeyParser<?>> map = this.keyParserMap;
            if (zContainsKey) {
                KeyParser<?> existingParser = map.get(index);
                if (!existingParser.equals(parser) || !parser.equals(existingParser)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + index);
                }
            } else {
                map.put(index, parser);
            }
            return this;
        }

        public <ParametersT extends Parameters, SerializationT extends Serialization> Builder registerParametersSerializer(ParametersSerializer<ParametersT, SerializationT> serializer) throws GeneralSecurityException {
            SerializerIndex index = new SerializerIndex(serializer.getParametersClass(), serializer.getSerializationClass());
            boolean zContainsKey = this.parametersSerializerMap.containsKey(index);
            Map<SerializerIndex, ParametersSerializer<?, ?>> map = this.parametersSerializerMap;
            if (zContainsKey) {
                ParametersSerializer<?, ?> existingSerializer = map.get(index);
                if (!existingSerializer.equals(serializer) || !serializer.equals(existingSerializer)) {
                    throw new GeneralSecurityException("Attempt to register non-equal serializer for already existing object of type: " + index);
                }
            } else {
                map.put(index, serializer);
            }
            return this;
        }

        public <SerializationT extends Serialization> Builder registerParametersParser(ParametersParser<SerializationT> parser) throws GeneralSecurityException {
            ParserIndex index = new ParserIndex(parser.getSerializationClass(), parser.getObjectIdentifier());
            boolean zContainsKey = this.parametersParserMap.containsKey(index);
            Map<ParserIndex, ParametersParser<?>> map = this.parametersParserMap;
            if (zContainsKey) {
                ParametersParser<?> existingParser = map.get(index);
                if (!existingParser.equals(parser) || !parser.equals(existingParser)) {
                    throw new GeneralSecurityException("Attempt to register non-equal parser for already existing object of type: " + index);
                }
            } else {
                map.put(index, parser);
            }
            return this;
        }

        SerializationRegistry build() {
            return new SerializationRegistry(this);
        }
    }

    private SerializationRegistry(Builder builder) {
        this.keySerializerMap = new HashMap(builder.keySerializerMap);
        this.keyParserMap = new HashMap(builder.keyParserMap);
        this.parametersSerializerMap = new HashMap(builder.parametersSerializerMap);
        this.parametersParserMap = new HashMap(builder.parametersParserMap);
    }

    private static class SerializerIndex {
        private final Class<?> keyClass;
        private final Class<? extends Serialization> keySerializationClass;

        private SerializerIndex(Class<?> keyClass, Class<? extends Serialization> keySerializationClass) {
            this.keyClass = keyClass;
            this.keySerializationClass = keySerializationClass;
        }

        public boolean equals(Object o) {
            if (!(o instanceof SerializerIndex)) {
                return false;
            }
            SerializerIndex other = (SerializerIndex) o;
            return other.keyClass.equals(this.keyClass) && other.keySerializationClass.equals(this.keySerializationClass);
        }

        public int hashCode() {
            return Objects.hash(this.keyClass, this.keySerializationClass);
        }

        public String toString() {
            return this.keyClass.getSimpleName() + " with serialization type: " + this.keySerializationClass.getSimpleName();
        }
    }

    private static class ParserIndex {
        private final Class<? extends Serialization> keySerializationClass;
        private final Bytes serializationIdentifier;

        private ParserIndex(Class<? extends Serialization> keySerializationClass, Bytes serializationIdentifier) {
            this.keySerializationClass = keySerializationClass;
            this.serializationIdentifier = serializationIdentifier;
        }

        public boolean equals(Object o) {
            if (!(o instanceof ParserIndex)) {
                return false;
            }
            ParserIndex other = (ParserIndex) o;
            return other.keySerializationClass.equals(this.keySerializationClass) && other.serializationIdentifier.equals(this.serializationIdentifier);
        }

        public int hashCode() {
            return Objects.hash(this.keySerializationClass, this.serializationIdentifier);
        }

        public String toString() {
            return this.keySerializationClass.getSimpleName() + ", object identifier: " + this.serializationIdentifier;
        }
    }

    public <SerializationT extends Serialization> boolean hasParserForKey(SerializationT serializedKey) {
        ParserIndex index = new ParserIndex(serializedKey.getClass(), serializedKey.getObjectIdentifier());
        return this.keyParserMap.containsKey(index);
    }

    public <SerializationT extends Serialization> Key parseKey(SerializationT serializedKey, @Nullable SecretKeyAccess access) throws GeneralSecurityException {
        ParserIndex index = new ParserIndex(serializedKey.getClass(), serializedKey.getObjectIdentifier());
        if (!this.keyParserMap.containsKey(index)) {
            throw new GeneralSecurityException("No Key Parser for requested key type " + index + " available");
        }
        return this.keyParserMap.get(index).parseKey(serializedKey, access);
    }

    public <KeyT extends Key, SerializationT extends Serialization> boolean hasSerializerForKey(KeyT key, Class<SerializationT> serializationClass) {
        SerializerIndex index = new SerializerIndex(key.getClass(), serializationClass);
        return this.keySerializerMap.containsKey(index);
    }

    public <KeyT extends Key, SerializationT extends Serialization> SerializationT serializeKey(KeyT keyt, Class<SerializationT> cls, @Nullable SecretKeyAccess secretKeyAccess) throws GeneralSecurityException {
        SerializerIndex serializerIndex = new SerializerIndex(keyt.getClass(), cls);
        if (!this.keySerializerMap.containsKey(serializerIndex)) {
            throw new GeneralSecurityException("No Key serializer for " + serializerIndex + " available");
        }
        return (SerializationT) this.keySerializerMap.get(serializerIndex).serializeKey(keyt, secretKeyAccess);
    }

    public <SerializationT extends Serialization> boolean hasParserForParameters(SerializationT serializedParameters) {
        ParserIndex index = new ParserIndex(serializedParameters.getClass(), serializedParameters.getObjectIdentifier());
        return this.parametersParserMap.containsKey(index);
    }

    public <SerializationT extends Serialization> Parameters parseParameters(SerializationT serializedParameters) throws GeneralSecurityException {
        ParserIndex index = new ParserIndex(serializedParameters.getClass(), serializedParameters.getObjectIdentifier());
        if (!this.parametersParserMap.containsKey(index)) {
            throw new GeneralSecurityException("No Parameters Parser for requested key type " + index + " available");
        }
        return this.parametersParserMap.get(index).parseParameters(serializedParameters);
    }

    public <ParametersT extends Parameters, SerializationT extends Serialization> boolean hasSerializerForParameters(ParametersT parameters, Class<SerializationT> serializationClass) {
        SerializerIndex index = new SerializerIndex(parameters.getClass(), serializationClass);
        return this.parametersSerializerMap.containsKey(index);
    }

    public <ParametersT extends Parameters, SerializationT extends Serialization> SerializationT serializeParameters(ParametersT parameterst, Class<SerializationT> cls) throws GeneralSecurityException {
        SerializerIndex serializerIndex = new SerializerIndex(parameterst.getClass(), cls);
        if (!this.parametersSerializerMap.containsKey(serializerIndex)) {
            throw new GeneralSecurityException("No Key Format serializer for " + serializerIndex + " available");
        }
        return (SerializationT) this.parametersSerializerMap.get(serializerIndex).serializeParameters(parameterst);
    }
}
