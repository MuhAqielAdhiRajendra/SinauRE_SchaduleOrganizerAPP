package com.google.crypto.tink;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes13.dex */
final class KeyManagerRegistry {
    private static final Logger logger = Logger.getLogger(KeyManagerRegistry.class.getName());
    private final ConcurrentMap<String, KeyManagerContainer> keyManagerMap;

    private interface KeyManagerContainer {
        Class<?> getImplementingClass();

        <P> KeyManager<P> getKeyManager(Class<P> primitiveClass) throws GeneralSecurityException;

        KeyManager<?> getUntypedKeyManager();

        MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException;

        Class<?> publicKeyManagerClassOrNull();

        Set<Class<?>> supportedPrimitives();
    }

    KeyManagerRegistry(KeyManagerRegistry original) {
        this.keyManagerMap = new ConcurrentHashMap(original.keyManagerMap);
    }

    KeyManagerRegistry() {
        this.keyManagerMap = new ConcurrentHashMap();
    }

    private static <P> KeyManagerContainer createContainerFor(final KeyManager<P> keyManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.KeyManagerRegistry.1
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> primitiveClass) throws GeneralSecurityException {
                if (!keyManager.getPrimitiveClass().equals(primitiveClass)) {
                    throw new InternalError("This should never be called, as we always first check supportedPrimitives.");
                }
                KeyManager<Q> result = keyManager;
                return result;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                return keyManager;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return keyManager.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return Collections.singleton(keyManager.getPrimitiveClass());
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException {
                return null;
            }
        };
    }

    private static <KeyProtoT extends MessageLite> KeyManagerContainer createContainerFor(final KeyTypeManager<KeyProtoT> keyManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.KeyManagerRegistry.2
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> primitiveClass) throws GeneralSecurityException {
                try {
                    return new KeyManagerImpl(keyManager, primitiveClass);
                } catch (IllegalArgumentException e) {
                    throw new GeneralSecurityException("Primitive type not supported", e);
                }
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                return new KeyManagerImpl(keyManager, keyManager.firstSupportedPrimitiveClass());
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return keyManager.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return keyManager.supportedPrimitives();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return null;
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException {
                MessageLite key = keyManager.parseKey(serializedKey);
                keyManager.validateKey(key);
                return key;
            }
        };
    }

    private static <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> KeyManagerContainer createPrivateKeyContainerFor(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> publicKeyTypeManager) {
        return new KeyManagerContainer() { // from class: com.google.crypto.tink.KeyManagerRegistry.3
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public <Q> KeyManager<Q> getKeyManager(Class<Q> primitiveClass) throws GeneralSecurityException {
                try {
                    return new PrivateKeyManagerImpl(privateKeyTypeManager, publicKeyTypeManager, primitiveClass);
                } catch (IllegalArgumentException e) {
                    throw new GeneralSecurityException("Primitive type not supported", e);
                }
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public KeyManager<?> getUntypedKeyManager() {
                return new PrivateKeyManagerImpl(privateKeyTypeManager, publicKeyTypeManager, privateKeyTypeManager.firstSupportedPrimitiveClass());
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> getImplementingClass() {
                return privateKeyTypeManager.getClass();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Set<Class<?>> supportedPrimitives() {
                return privateKeyTypeManager.supportedPrimitives();
            }

            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public Class<?> publicKeyManagerClassOrNull() {
                return publicKeyTypeManager.getClass();
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [com.google.crypto.tink.shaded.protobuf.MessageLite] */
            @Override // com.google.crypto.tink.KeyManagerRegistry.KeyManagerContainer
            public MessageLite parseKey(ByteString serializedKey) throws InvalidProtocolBufferException, GeneralSecurityException {
                ?? key = privateKeyTypeManager.parseKey(serializedKey);
                privateKeyTypeManager.validateKey(key);
                return key;
            }
        };
    }

    private synchronized KeyManagerContainer getKeyManagerContainerOrThrow(String typeUrl) throws GeneralSecurityException {
        if (!this.keyManagerMap.containsKey(typeUrl)) {
            throw new GeneralSecurityException("No key manager found for key type " + typeUrl);
        }
        return this.keyManagerMap.get(typeUrl);
    }

    private static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }

    private synchronized <P> void registerKeyManagerContainer(final KeyManagerContainer containerToInsert, boolean forceOverwrite) throws GeneralSecurityException {
        String typeUrl = containerToInsert.getUntypedKeyManager().getKeyType();
        KeyManagerContainer container = this.keyManagerMap.get(typeUrl);
        if (container != null && !container.getImplementingClass().equals(containerToInsert.getImplementingClass())) {
            logger.warning("Attempted overwrite of a registered key manager for key type " + typeUrl);
            throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", typeUrl, container.getImplementingClass().getName(), containerToInsert.getImplementingClass().getName()));
        }
        ConcurrentMap<String, KeyManagerContainer> concurrentMap = this.keyManagerMap;
        if (!forceOverwrite) {
            concurrentMap.putIfAbsent(typeUrl, containerToInsert);
        } else {
            concurrentMap.put(typeUrl, containerToInsert);
        }
    }

    synchronized <P> void registerKeyManager(final KeyManager<P> manager) throws GeneralSecurityException {
        if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
            throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
        }
        registerKeyManagerContainer(createContainerFor(manager), false);
    }

    synchronized <KeyProtoT extends MessageLite> void registerKeyManager(final KeyTypeManager<KeyProtoT> manager) throws GeneralSecurityException {
        if (!manager.fipsStatus().isCompatible()) {
            throw new GeneralSecurityException("failed to register key manager " + manager.getClass() + " as it is not FIPS compatible.");
        }
        registerKeyManagerContainer(createContainerFor(manager), false);
    }

    synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> publicKeyTypeManager) throws GeneralSecurityException {
        Class<?> existingPublicKeyManagerClass;
        TinkFipsUtil.AlgorithmFipsCompatibility fipsStatusPrivateKey = privateKeyTypeManager.fipsStatus();
        TinkFipsUtil.AlgorithmFipsCompatibility fipsStatusPublicKey = publicKeyTypeManager.fipsStatus();
        if (!fipsStatusPrivateKey.isCompatible()) {
            throw new GeneralSecurityException("failed to register key manager " + privateKeyTypeManager.getClass() + " as it is not FIPS compatible.");
        }
        if (!fipsStatusPublicKey.isCompatible()) {
            throw new GeneralSecurityException("failed to register key manager " + publicKeyTypeManager.getClass() + " as it is not FIPS compatible.");
        }
        String privateTypeUrl = privateKeyTypeManager.getKeyType();
        String publicTypeUrl = publicKeyTypeManager.getKeyType();
        if (this.keyManagerMap.containsKey(privateTypeUrl) && this.keyManagerMap.get(privateTypeUrl).publicKeyManagerClassOrNull() != null && (existingPublicKeyManagerClass = this.keyManagerMap.get(privateTypeUrl).publicKeyManagerClassOrNull()) != null && !existingPublicKeyManagerClass.getName().equals(publicKeyTypeManager.getClass().getName())) {
            logger.warning("Attempted overwrite of a registered key manager for key type " + privateTypeUrl + " with inconsistent public key type " + publicTypeUrl);
            throw new GeneralSecurityException(String.format("public key manager corresponding to %s is already registered with %s, cannot be re-registered with %s", privateKeyTypeManager.getClass().getName(), existingPublicKeyManagerClass.getName(), publicKeyTypeManager.getClass().getName()));
        }
        registerKeyManagerContainer(createPrivateKeyContainerFor(privateKeyTypeManager, publicKeyTypeManager), true);
        registerKeyManagerContainer(createContainerFor(publicKeyTypeManager), false);
    }

    boolean typeUrlExists(String typeUrl) {
        return this.keyManagerMap.containsKey(typeUrl);
    }

    private static String toCommaSeparatedString(Set<Class<?>> setOfClasses) {
        StringBuilder b = new StringBuilder();
        boolean first = true;
        for (Class<?> clazz : setOfClasses) {
            if (!first) {
                b.append(", ");
            }
            b.append(clazz.getCanonicalName());
            first = false;
        }
        return b.toString();
    }

    @Deprecated
    <P> KeyManager<P> getKeyManager(String typeUrl) throws GeneralSecurityException {
        return getKeyManagerInternal(typeUrl, null);
    }

    <P> KeyManager<P> getKeyManager(String typeUrl, Class<P> primitiveClass) throws GeneralSecurityException {
        return getKeyManagerInternal(typeUrl, (Class) checkNotNull(primitiveClass));
    }

    private <P> KeyManager<P> getKeyManagerInternal(String str, Class<P> cls) throws GeneralSecurityException {
        KeyManagerContainer keyManagerContainerOrThrow = getKeyManagerContainerOrThrow(str);
        if (cls == null) {
            return (KeyManager<P>) keyManagerContainerOrThrow.getUntypedKeyManager();
        }
        if (keyManagerContainerOrThrow.supportedPrimitives().contains(cls)) {
            return keyManagerContainerOrThrow.getKeyManager(cls);
        }
        throw new GeneralSecurityException("Primitive type " + cls.getName() + " not supported by key manager of type " + keyManagerContainerOrThrow.getImplementingClass() + ", supported primitives: " + toCommaSeparatedString(keyManagerContainerOrThrow.supportedPrimitives()));
    }

    KeyManager<?> getUntypedKeyManager(String typeUrl) throws GeneralSecurityException {
        KeyManagerContainer container = getKeyManagerContainerOrThrow(typeUrl);
        return container.getUntypedKeyManager();
    }

    MessageLite parseKeyData(KeyData keyData) throws InvalidProtocolBufferException, GeneralSecurityException {
        KeyManagerContainer container = getKeyManagerContainerOrThrow(keyData.getTypeUrl());
        return container.parseKey(keyData.getValue());
    }

    boolean isEmpty() {
        return this.keyManagerMap.isEmpty();
    }
}
