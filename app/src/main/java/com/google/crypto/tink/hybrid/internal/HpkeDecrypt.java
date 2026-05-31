package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePrivateKey;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
final class HpkeDecrypt implements HybridDecrypt {
    private static final byte[] EMPTY_ASSOCIATED_DATA = new byte[0];
    private final HpkeAead aead;
    private final int encapsulatedKeyLength;
    private final HpkeKdf kdf;
    private final HpkeKem kem;
    private final HpkeKemPrivateKey recipientPrivateKey;

    private HpkeDecrypt(HpkeKemPrivateKey recipientPrivateKey, HpkeKem kem, HpkeKdf kdf, HpkeAead aead, int encapsulatedKeyLength) {
        this.recipientPrivateKey = recipientPrivateKey;
        this.kem = kem;
        this.kdf = kdf;
        this.aead = aead;
        this.encapsulatedKeyLength = encapsulatedKeyLength;
    }

    private static int encodingSizeInBytes(com.google.crypto.tink.proto.HpkeKem kemProtoEnum) {
        switch (kemProtoEnum) {
            case DHKEM_X25519_HKDF_SHA256:
                return 32;
            case DHKEM_P256_HKDF_SHA256:
                return 65;
            case DHKEM_P384_HKDF_SHA384:
                return 97;
            case DHKEM_P521_HKDF_SHA512:
                return 133;
            default:
                throw new IllegalArgumentException("Unable to determine KEM-encoding length for " + kemProtoEnum.name());
        }
    }

    static HpkeDecrypt createHpkeDecrypt(HpkePrivateKey recipientPrivateKey) throws GeneralSecurityException {
        if (!recipientPrivateKey.hasPublicKey()) {
            throw new IllegalArgumentException("HpkePrivateKey is missing public_key field.");
        }
        if (!recipientPrivateKey.getPublicKey().hasParams()) {
            throw new IllegalArgumentException("HpkePrivateKey.public_key is missing params field.");
        }
        if (recipientPrivateKey.getPrivateKey().isEmpty()) {
            throw new IllegalArgumentException("HpkePrivateKey.private_key is empty.");
        }
        HpkeParams params = recipientPrivateKey.getPublicKey().getParams();
        HpkeKem kem = HpkePrimitiveFactory.createKem(params);
        HpkeKdf kdf = HpkePrimitiveFactory.createKdf(params);
        HpkeAead aead = HpkePrimitiveFactory.createAead(params);
        int encapsulatedKeyLength = encodingSizeInBytes(params.getKem());
        HpkeKemPrivateKey recipientKemPrivateKey = HpkeKemKeyFactory.createPrivate(recipientPrivateKey);
        return new HpkeDecrypt(recipientKemPrivateKey, kem, kdf, aead, encapsulatedKeyLength);
    }

    @Override // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(final byte[] ciphertext, final byte[] contextInfo) throws GeneralSecurityException {
        byte[] info;
        if (ciphertext.length < this.encapsulatedKeyLength) {
            throw new GeneralSecurityException("Ciphertext is too short.");
        }
        if (contextInfo != null) {
            info = contextInfo;
        } else {
            byte[] info2 = new byte[0];
            info = info2;
        }
        byte[] encapsulatedKey = Arrays.copyOf(ciphertext, this.encapsulatedKeyLength);
        byte[] aeadCiphertext = Arrays.copyOfRange(ciphertext, this.encapsulatedKeyLength, ciphertext.length);
        HpkeContext context = HpkeContext.createRecipientContext(encapsulatedKey, this.recipientPrivateKey, this.kem, this.kdf, this.aead, info);
        return context.open(aeadCiphertext, EMPTY_ASSOCIATED_DATA);
    }
}
