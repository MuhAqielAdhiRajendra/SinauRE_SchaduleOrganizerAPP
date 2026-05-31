package com.google.crypto.tink.aead.internal;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import com.google.crypto.tink.subtle.EngineFactory;
import com.google.crypto.tink.subtle.SubtleUtil;
import com.google.crypto.tink.subtle.Validators;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes13.dex */
public final class InsecureNonceAesGcmJce {
    public static final int IV_SIZE_IN_BYTES = 12;
    public static final int TAG_SIZE_IN_BYTES = 16;
    private final SecretKey keySpec;
    private final boolean prependIv;
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.aead.internal.InsecureNonceAesGcmJce.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance("AES/GCM/NoPadding");
            } catch (GeneralSecurityException ex) {
                throw new IllegalStateException(ex);
            }
        }
    };

    public InsecureNonceAesGcmJce(final byte[] key, boolean prependIv) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-GCM in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateAesKeySize(key.length);
        this.keySpec = new SecretKeySpec(key, "AES");
        this.prependIv = prependIv;
    }

    public byte[] encrypt(final byte[] iv, final byte[] plaintext, final byte[] associatedData) throws GeneralSecurityException {
        int ciphertextLength;
        if (iv.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        if (plaintext.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        if (this.prependIv) {
            ciphertextLength = plaintext.length + 12 + 16;
        } else {
            ciphertextLength = plaintext.length + 16;
        }
        byte[] ciphertext = new byte[ciphertextLength];
        if (this.prependIv) {
            System.arraycopy(iv, 0, ciphertext, 0, 12);
        }
        AlgorithmParameterSpec params = getParams(iv);
        localCipher.get().init(1, this.keySpec, params);
        if (associatedData != null && associatedData.length != 0) {
            localCipher.get().updateAAD(associatedData);
        }
        int ciphertextOutputOffset = this.prependIv ? 12 : 0;
        int written = localCipher.get().doFinal(plaintext, 0, plaintext.length, ciphertext, ciphertextOutputOffset);
        if (written != plaintext.length + 16) {
            int actualTagSize = written - plaintext.length;
            throw new GeneralSecurityException(String.format("encryption failed; GCM tag must be %s bytes, but got only %s bytes", 16, Integer.valueOf(actualTagSize)));
        }
        return ciphertext;
    }

    public byte[] decrypt(final byte[] iv, final byte[] ciphertext, final byte[] associatedData) throws GeneralSecurityException {
        if (iv.length != 12) {
            throw new GeneralSecurityException("iv is wrong size");
        }
        int minimumCiphertextLength = this.prependIv ? 28 : 16;
        if (ciphertext.length < minimumCiphertextLength) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        if (this.prependIv && !ByteBuffer.wrap(iv).equals(ByteBuffer.wrap(ciphertext, 0, 12))) {
            throw new GeneralSecurityException("iv does not match prepended iv");
        }
        AlgorithmParameterSpec params = getParams(iv);
        localCipher.get().init(2, this.keySpec, params);
        if (associatedData != null && associatedData.length != 0) {
            localCipher.get().updateAAD(associatedData);
        }
        int ciphertextInputOffset = this.prependIv ? 12 : 0;
        int ciphertextLength = this.prependIv ? ciphertext.length - 12 : ciphertext.length;
        return localCipher.get().doFinal(ciphertext, ciphertextInputOffset, ciphertextLength);
    }

    private static AlgorithmParameterSpec getParams(final byte[] iv) throws GeneralSecurityException {
        return getParams(iv, 0, iv.length);
    }

    private static AlgorithmParameterSpec getParams(final byte[] buf, int offset, int len) throws GeneralSecurityException {
        if (SubtleUtil.isAndroid() && SubtleUtil.androidApiLevel() <= 19) {
            return new IvParameterSpec(buf, offset, len);
        }
        return new GCMParameterSpec(128, buf, offset, len);
    }
}
