package com.google.crypto.tink.hybrid.internal;

import com.google.crypto.tink.HybridEncrypt;
import com.google.crypto.tink.proto.HpkeParams;
import com.google.crypto.tink.proto.HpkePublicKey;
import com.google.crypto.tink.subtle.Bytes;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes13.dex */
@Immutable
final class HpkeEncrypt implements HybridEncrypt {
    private static final byte[] EMPTY_ASSOCIATED_DATA = new byte[0];
    private final HpkeAead aead;
    private final HpkeKdf kdf;
    private final HpkeKem kem;
    private final HpkePublicKey recipientPublicKey;

    private HpkeEncrypt(HpkePublicKey recipientPublicKey, HpkeKem kem, HpkeKdf kdf, HpkeAead aead) {
        this.recipientPublicKey = recipientPublicKey;
        this.kem = kem;
        this.kdf = kdf;
        this.aead = aead;
    }

    static HpkeEncrypt createHpkeEncrypt(HpkePublicKey recipientPublicKey) throws GeneralSecurityException {
        if (recipientPublicKey.getPublicKey().isEmpty()) {
            throw new IllegalArgumentException("HpkePublicKey.public_key is empty.");
        }
        HpkeParams params = recipientPublicKey.getParams();
        HpkeKem kem = HpkePrimitiveFactory.createKem(params);
        HpkeKdf kdf = HpkePrimitiveFactory.createKdf(params);
        HpkeAead aead = HpkePrimitiveFactory.createAead(params);
        return new HpkeEncrypt(recipientPublicKey, kem, kdf, aead);
    }

    @Override // com.google.crypto.tink.HybridEncrypt
    public byte[] encrypt(final byte[] plaintext, final byte[] contextInfo) throws GeneralSecurityException {
        byte[] info = contextInfo;
        if (info == null) {
            info = new byte[0];
        }
        HpkeContext context = HpkeContext.createSenderContext(this.recipientPublicKey, this.kem, this.kdf, this.aead, info);
        byte[] ciphertext = context.seal(plaintext, EMPTY_ASSOCIATED_DATA);
        return Bytes.concat(context.getEncapsulatedKey(), ciphertext);
    }
}
