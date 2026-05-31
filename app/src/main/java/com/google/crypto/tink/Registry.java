package com.google.crypto.tink;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.internal.KeyTypeManager;
import com.google.crypto.tink.internal.MutablePrimitiveRegistry;
import com.google.crypto.tink.internal.PrivateKeyTypeManager;
import com.google.crypto.tink.proto.KeyData;
import com.google.crypto.tink.shaded.protobuf.ByteString;
import com.google.crypto.tink.shaded.protobuf.InvalidProtocolBufferException;
import com.google.crypto.tink.shaded.protobuf.MessageLite;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
public final class Registry {
    private static final Logger logger = Logger.getLogger(Registry.class.getName());
    private static final AtomicReference<KeyManagerRegistry> keyManagerRegistry = new AtomicReference<>(new KeyManagerRegistry());
    private static final ConcurrentMap<String, KeyDeriverContainer> keyDeriverMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> newKeyAllowedMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Catalogue<?>> catalogueMap = new ConcurrentHashMap();
    private static final ConcurrentMap<String, KeyTemplate> keyTemplateMap = new ConcurrentHashMap();

    private interface KeyDeriverContainer {
        KeyData deriveKey(ByteString serializedKeyFormat, InputStream stream) throws GeneralSecurityException;
    }

    private static <KeyProtoT extends MessageLite> KeyDeriverContainer createDeriverFor(final KeyTypeManager<KeyProtoT> keyManager) {
        return new KeyDeriverContainer() { // from class: com.google.crypto.tink.Registry.1
            /* JADX WARN: Unknown type variable: KeyProtoT in type: com.google.crypto.tink.internal.KeyTypeManager$KeyFactory<KeyFormatProtoT extends com.google.crypto.tink.shaded.protobuf.MessageLite, KeyProtoT> */
            private <KeyFormatProtoT extends MessageLite> MessageLite deriveKeyWithFactory(ByteString serializedKeyFormat, InputStream stream, KeyTypeManager.KeyFactory<KeyFormatProtoT, KeyProtoT> keyFactory) throws GeneralSecurityException {
                try {
                    MessageLite keyFormat = keyFactory.parseKeyFormat(serializedKeyFormat);
                    keyFactory.validateKeyFormat(keyFormat);
                    return keyFactory.deriveKey(keyFormat, stream);
                } catch (InvalidProtocolBufferException e) {
                    throw new GeneralSecurityException("parsing key format failed in deriveKey", e);
                }
            }

            @Override // com.google.crypto.tink.Registry.KeyDeriverContainer
            public KeyData deriveKey(ByteString serializedKeyFormat, InputStream stream) throws GeneralSecurityException {
                MessageLite keyValue = deriveKeyWithFactory(serializedKeyFormat, stream, keyManager.keyFactory());
                return (KeyData) KeyData.newBuilder().setTypeUrl(keyManager.getKeyType()).setValue(keyValue.toByteString()).setKeyMaterialType(keyManager.keyMaterialType()).build();
            }
        };
    }

    static synchronized void reset() {
        keyManagerRegistry.set(new KeyManagerRegistry());
        MutablePrimitiveRegistry.resetGlobalInstanceTestOnly();
        keyDeriverMap.clear();
        newKeyAllowedMap.clear();
        catalogueMap.clear();
        keyTemplateMap.clear();
    }

    @Deprecated
    public static synchronized void addCatalogue(String catalogueName, Catalogue<?> catalogue) throws GeneralSecurityException {
        if (catalogueName == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        if (catalogue == null) {
            throw new IllegalArgumentException("catalogue must be non-null.");
        }
        if (catalogueMap.containsKey(catalogueName.toLowerCase(Locale.US))) {
            Catalogue<?> existing = catalogueMap.get(catalogueName.toLowerCase(Locale.US));
            if (!catalogue.getClass().getName().equals(existing.getClass().getName())) {
                logger.warning("Attempted overwrite of a catalogueName catalogue for name " + catalogueName);
                throw new GeneralSecurityException("catalogue for name " + catalogueName + " has been already registered");
            }
        }
        catalogueMap.put(catalogueName.toLowerCase(Locale.US), catalogue);
    }

    @Deprecated
    public static Catalogue<?> getCatalogue(String catalogueName) throws GeneralSecurityException {
        if (catalogueName == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        Catalogue<?> catalogue = catalogueMap.get(catalogueName.toLowerCase(Locale.US));
        if (catalogue == null) {
            String error = String.format("no catalogue found for %s. ", catalogueName);
            if (catalogueName.toLowerCase(Locale.US).startsWith("tinkaead")) {
                error = error + "Maybe call AeadConfig.register().";
            }
            if (catalogueName.toLowerCase(Locale.US).startsWith("tinkdeterministicaead")) {
                error = error + "Maybe call DeterministicAeadConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkstreamingaead")) {
                error = error + "Maybe call StreamingAeadConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkhybriddecrypt") || catalogueName.toLowerCase(Locale.US).startsWith("tinkhybridencrypt")) {
                error = error + "Maybe call HybridConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkmac")) {
                error = error + "Maybe call MacConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeysign") || catalogueName.toLowerCase(Locale.US).startsWith("tinkpublickeyverify")) {
                error = error + "Maybe call SignatureConfig.register().";
            } else if (catalogueName.toLowerCase(Locale.US).startsWith("tink")) {
                error = error + "Maybe call TinkConfig.register().";
            }
            throw new GeneralSecurityException(error);
        }
        return catalogue;
    }

    public static synchronized <P> void registerKeyManager(final KeyManager<P> manager) throws GeneralSecurityException {
        registerKeyManager((KeyManager) manager, true);
    }

    public static synchronized <P> void registerKeyManager(final KeyManager<P> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        try {
            if (manager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            KeyManagerRegistry newKeyManagerRegistry = new KeyManagerRegistry(keyManagerRegistry.get());
            newKeyManagerRegistry.registerKeyManager(manager);
            if (!TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS.isCompatible()) {
                throw new GeneralSecurityException("Registering key managers is not supported in FIPS mode");
            }
            String typeUrl = manager.getKeyType();
            ensureKeyManagerInsertable(typeUrl, Collections.emptyMap(), newKeyAllowed);
            newKeyAllowedMap.put(typeUrl, Boolean.valueOf(newKeyAllowed));
            keyManagerRegistry.set(newKeyManagerRegistry);
        } catch (Throwable th) {
            throw th;
        }
    }

    public static synchronized <KeyProtoT extends MessageLite> void registerKeyManager(final KeyTypeManager<KeyProtoT> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        if (manager == null) {
            throw new IllegalArgumentException("key manager must be non-null.");
        }
        KeyManagerRegistry newKeyManagerRegistry = new KeyManagerRegistry(keyManagerRegistry.get());
        newKeyManagerRegistry.registerKeyManager(manager);
        String typeUrl = manager.getKeyType();
        ensureKeyManagerInsertable(typeUrl, newKeyAllowed ? manager.keyFactory().keyFormats() : Collections.emptyMap(), newKeyAllowed);
        if (!keyManagerRegistry.get().typeUrlExists(typeUrl)) {
            keyDeriverMap.put(typeUrl, createDeriverFor(manager));
            if (newKeyAllowed) {
                registerKeyTemplates(typeUrl, manager.keyFactory().keyFormats());
            }
        }
        newKeyAllowedMap.put(typeUrl, Boolean.valueOf(newKeyAllowed));
        keyManagerRegistry.set(newKeyManagerRegistry);
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String typeUrl, final KeyManager<P> manager) throws GeneralSecurityException {
        registerKeyManager(typeUrl, manager, true);
    }

    @Deprecated
    public static synchronized <P> void registerKeyManager(String typeUrl, final KeyManager<P> manager, boolean newKeyAllowed) throws GeneralSecurityException {
        try {
            if (manager == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            if (!typeUrl.equals(manager.getKeyType())) {
                throw new GeneralSecurityException("Manager does not support key type " + typeUrl + ".");
            }
            registerKeyManager(manager, newKeyAllowed);
        } catch (Throwable th) {
            throw th;
        }
    }

    private static synchronized <KeyProtoT extends MessageLite, KeyFormatProtoT extends MessageLite> void ensureKeyManagerInsertable(String typeUrl, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> keyFormats, boolean newKeyAllowed) throws GeneralSecurityException {
        if (newKeyAllowed) {
            try {
                if (newKeyAllowedMap.containsKey(typeUrl) && !newKeyAllowedMap.get(typeUrl).booleanValue()) {
                    throw new GeneralSecurityException("New keys are already disallowed for key type " + typeUrl);
                }
            } finally {
            }
        }
        if (newKeyAllowed) {
            if (keyManagerRegistry.get().typeUrlExists(typeUrl)) {
                for (Map.Entry<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> entry : keyFormats.entrySet()) {
                    if (!keyTemplateMap.containsKey(entry.getKey())) {
                        throw new GeneralSecurityException("Attempted to register a new key template " + entry.getKey() + " from an existing key manager of type " + typeUrl);
                    }
                }
            } else {
                for (Map.Entry<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> entry2 : keyFormats.entrySet()) {
                    if (keyTemplateMap.containsKey(entry2.getKey())) {
                        throw new GeneralSecurityException("Attempted overwrite of a registered key template " + entry2.getKey());
                    }
                }
            }
        }
    }

    public static synchronized <KeyProtoT extends MessageLite, PublicKeyProtoT extends MessageLite> void registerAsymmetricKeyManagers(final PrivateKeyTypeManager<KeyProtoT, PublicKeyProtoT> privateKeyTypeManager, final KeyTypeManager<PublicKeyProtoT> publicKeyTypeManager, boolean newKeyAllowed) throws GeneralSecurityException {
        if (privateKeyTypeManager == null || publicKeyTypeManager == null) {
            throw new IllegalArgumentException("given key managers must be non-null.");
        }
        KeyManagerRegistry newKeyManagerRegistry = new KeyManagerRegistry(keyManagerRegistry.get());
        newKeyManagerRegistry.registerAsymmetricKeyManagers(privateKeyTypeManager, publicKeyTypeManager);
        String privateTypeUrl = privateKeyTypeManager.getKeyType();
        String publicTypeUrl = publicKeyTypeManager.getKeyType();
        ensureKeyManagerInsertable(privateTypeUrl, newKeyAllowed ? privateKeyTypeManager.keyFactory().keyFormats() : Collections.emptyMap(), newKeyAllowed);
        ensureKeyManagerInsertable(publicTypeUrl, Collections.emptyMap(), false);
        if (!keyManagerRegistry.get().typeUrlExists(privateTypeUrl)) {
            keyDeriverMap.put(privateTypeUrl, createDeriverFor(privateKeyTypeManager));
            if (newKeyAllowed) {
                registerKeyTemplates(privateKeyTypeManager.getKeyType(), privateKeyTypeManager.keyFactory().keyFormats());
            }
        }
        newKeyAllowedMap.put(privateTypeUrl, Boolean.valueOf(newKeyAllowed));
        newKeyAllowedMap.put(publicTypeUrl, false);
        keyManagerRegistry.set(newKeyManagerRegistry);
    }

    private static <KeyFormatProtoT extends MessageLite> void registerKeyTemplates(String typeUrl, Map<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> keyFormats) {
        for (Map.Entry<String, KeyTypeManager.KeyFactory.KeyFormat<KeyFormatProtoT>> entry : keyFormats.entrySet()) {
            keyTemplateMap.put(entry.getKey(), KeyTemplate.create(typeUrl, entry.getValue().keyFormat.toByteArray(), entry.getValue().outputPrefixType));
        }
    }

    public static synchronized <B, P> void registerPrimitiveWrapper(final PrimitiveWrapper<B, P> wrapper) throws GeneralSecurityException {
        MutablePrimitiveRegistry.globalInstance().registerPrimitiveWrapper(wrapper);
    }

    @Deprecated
    public static <P> KeyManager<P> getKeyManager(String typeUrl) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl);
    }

    public static <P> KeyManager<P> getKeyManager(String typeUrl, Class<P> primitiveClass) throws GeneralSecurityException {
        return keyManagerRegistry.get().getKeyManager(typeUrl, primitiveClass);
    }

    public static KeyManager<?> getUntypedKeyManager(String typeUrl) throws GeneralSecurityException {
        return keyManagerRegistry.get().getUntypedKeyManager(typeUrl);
    }

    public static synchronized KeyData newKeyData(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyManager<?> manager;
        manager = getUntypedKeyManager(keyTemplate.getTypeUrl());
        if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
        } else {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
        }
        return manager.newKeyData(keyTemplate.getValue());
    }

    public static synchronized KeyData newKeyData(KeyTemplate keyTemplate) throws GeneralSecurityException {
        return newKeyData(keyTemplate.getProto());
    }

    public static synchronized MessageLite newKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate) throws GeneralSecurityException {
        KeyManager<?> manager;
        manager = getUntypedKeyManager(keyTemplate.getTypeUrl());
        if (newKeyAllowedMap.get(keyTemplate.getTypeUrl()).booleanValue()) {
        } else {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + keyTemplate.getTypeUrl());
        }
        return manager.newKey(keyTemplate.getValue());
    }

    public static synchronized MessageLite newKey(String typeUrl, MessageLite format) throws GeneralSecurityException {
        KeyManager<?> manager;
        manager = getKeyManager(typeUrl);
        if (newKeyAllowedMap.get(typeUrl).booleanValue()) {
        } else {
            throw new GeneralSecurityException("newKey-operation not permitted for key type " + typeUrl);
        }
        return manager.newKey(format);
    }

    static synchronized KeyData deriveKey(com.google.crypto.tink.proto.KeyTemplate keyTemplate, InputStream randomStream) throws GeneralSecurityException {
        KeyDeriverContainer deriver;
        String typeUrl = keyTemplate.getTypeUrl();
        if (!keyDeriverMap.containsKey(typeUrl)) {
            throw new GeneralSecurityException("No keymanager registered or key manager cannot derive keys for " + typeUrl);
        }
        deriver = keyDeriverMap.get(typeUrl);
        return deriver.deriveKey(keyTemplate.getValue(), randomStream);
    }

    public static KeyData getPublicKeyData(String typeUrl, ByteString serializedPrivateKey) throws GeneralSecurityException {
        KeyManager<?> manager = getKeyManager(typeUrl);
        if (!(manager instanceof PrivateKeyManager)) {
            throw new GeneralSecurityException("manager for key type " + typeUrl + " is not a PrivateKeyManager");
        }
        return ((PrivateKeyManager) manager).getPublicKeyData(serializedPrivateKey);
    }

    @Deprecated
    public static <P> P getPrimitive(String typeUrl, MessageLite key) throws GeneralSecurityException {
        KeyManager<P> manager = keyManagerRegistry.get().getKeyManager(typeUrl);
        return manager.getPrimitive(key);
    }

    public static <P> P getPrimitive(String typeUrl, MessageLite key, Class<P> primitiveClass) throws GeneralSecurityException {
        KeyManager<P> manager = keyManagerRegistry.get().getKeyManager(typeUrl, primitiveClass);
        return manager.getPrimitive(key);
    }

    @Deprecated
    public static <P> P getPrimitive(String typeUrl, ByteString serializedKey) throws GeneralSecurityException {
        KeyManager<P> manager = keyManagerRegistry.get().getKeyManager(typeUrl);
        return manager.getPrimitive(serializedKey);
    }

    public static <P> P getPrimitive(String typeUrl, ByteString serializedKey, Class<P> primitiveClass) throws GeneralSecurityException {
        KeyManager<P> manager = keyManagerRegistry.get().getKeyManager(typeUrl, primitiveClass);
        return manager.getPrimitive(serializedKey);
    }

    @Deprecated
    public static <P> P getPrimitive(String str, byte[] bArr) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr));
    }

    public static <P> P getPrimitive(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(str, ByteString.copyFrom(bArr), cls);
    }

    @Deprecated
    public static <P> P getPrimitive(KeyData keyData) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue());
    }

    public static <P> P getPrimitive(KeyData keyData, Class<P> cls) throws GeneralSecurityException {
        return (P) getPrimitive(keyData.getTypeUrl(), keyData.getValue(), cls);
    }

    static <KeyT extends Key, P> P getFullPrimitive(KeyT keyt, Class<P> cls) throws GeneralSecurityException {
        return (P) MutablePrimitiveRegistry.globalInstance().getPrimitive(keyt, cls);
    }

    public static <B, P> P wrap(PrimitiveSet<B> primitiveSet, Class<P> cls) throws GeneralSecurityException {
        return (P) MutablePrimitiveRegistry.globalInstance().wrap(primitiveSet, cls);
    }

    public static <P> P wrap(PrimitiveSet<P> primitiveSet) throws GeneralSecurityException {
        return (P) wrap(primitiveSet, primitiveSet.getPrimitiveClass());
    }

    public static synchronized List<String> keyTemplates() {
        List<String> results;
        results = new ArrayList<>();
        results.addAll(keyTemplateMap.keySet());
        return Collections.unmodifiableList(results);
    }

    static synchronized Map<String, KeyTemplate> keyTemplateMap() {
        return Collections.unmodifiableMap(keyTemplateMap);
    }

    @Nullable
    public static Class<?> getInputPrimitive(Class<?> wrappedPrimitive) {
        try {
            return MutablePrimitiveRegistry.globalInstance().getInputPrimitiveClass(wrappedPrimitive);
        } catch (GeneralSecurityException e) {
            return null;
        }
    }

    static MessageLite parseKeyData(KeyData keyData) throws InvalidProtocolBufferException, GeneralSecurityException {
        return keyManagerRegistry.get().parseKeyData(keyData);
    }

    public static synchronized void restrictToFipsIfEmpty() throws GeneralSecurityException {
        if (TinkFipsUtil.useOnlyFips()) {
            return;
        }
        if (keyManagerRegistry.get().isEmpty()) {
            TinkFipsUtil.setFipsRestricted();
            return;
        }
        throw new GeneralSecurityException("Could not enable FIPS mode as Registry is not empty.");
    }

    private Registry() {
    }
}
