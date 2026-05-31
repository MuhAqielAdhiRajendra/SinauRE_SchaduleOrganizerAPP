package com.google.crypto.tink.subtle;

import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes13.dex */
public final class AesCtrJceCipher implements IndCpaCipher {
    private static final String CIPHER_ALGORITHM = "AES/CTR/NoPadding";
    private static final String KEY_ALGORITHM = "AES";
    private static final int MIN_IV_SIZE_IN_BYTES = 12;
    private final int blockSize;
    private final int ivSize;
    private final SecretKeySpec keySpec;
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_REQUIRES_BORINGCRYPTO;
    private static final ThreadLocal<Cipher> localCipher = new ThreadLocal<Cipher>() { // from class: com.google.crypto.tink.subtle.AesCtrJceCipher.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Cipher initialValue() {
            try {
                return EngineFactory.CIPHER.getInstance(AesCtrJceCipher.CIPHER_ALGORITHM);
            } catch (GeneralSecurityException ex) {
                throw new IllegalStateException(ex);
            }
        }
    };

    public AesCtrJceCipher(final byte[] key, int ivSize) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use AES-CTR in FIPS-mode, as BoringCrypto module is not available.");
        }
        Validators.validateAesKeySize(key.length);
        this.keySpec = new SecretKeySpec(key, KEY_ALGORITHM);
        this.blockSize = localCipher.get().getBlockSize();
        if (ivSize < 12 || ivSize > this.blockSize) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.ivSize = ivSize;
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] encrypt(final byte[] plaintext) throws GeneralSecurityException {
        if (plaintext.length > Integer.MAX_VALUE - this.ivSize) {
            throw new GeneralSecurityException("plaintext length can not exceed " + (Integer.MAX_VALUE - this.ivSize));
        }
        byte[] ciphertext = new byte[this.ivSize + plaintext.length];
        byte[] iv = Random.randBytes(this.ivSize);
        System.arraycopy(iv, 0, ciphertext, 0, this.ivSize);
        doCtr(plaintext, 0, plaintext.length, ciphertext, this.ivSize, iv, true);
        return ciphertext;
    }

    @Override // com.google.crypto.tink.subtle.IndCpaCipher
    public byte[] decrypt(final byte[] ciphertext) throws GeneralSecurityException {
        if (ciphertext.length < this.ivSize) {
            throw new GeneralSecurityException("ciphertext too short");
        }
        byte[] iv = new byte[this.ivSize];
        System.arraycopy(ciphertext, 0, iv, 0, this.ivSize);
        byte[] plaintext = new byte[ciphertext.length - this.ivSize];
        doCtr(ciphertext, this.ivSize, ciphertext.length - this.ivSize, plaintext, 0, iv, false);
        return plaintext;
    }

    private void doCtr(final byte[] input, int inputOffset, int inputLen, byte[] output, int outputOffset, final byte[] iv, boolean encrypt) throws GeneralSecurityException {
        Cipher cipher = localCipher.get();
        byte[] counter = new byte[this.blockSize];
        System.arraycopy(iv, 0, counter, 0, this.ivSize);
        IvParameterSpec paramSpec = new IvParameterSpec(counter);
        SecretKeySpec secretKeySpec = this.keySpec;
        if (encrypt) {
            cipher.init(1, secretKeySpec, paramSpec);
        } else {
            cipher.init(2, secretKeySpec, paramSpec);
        }
        int numBytes = cipher.doFinal(input, inputOffset, inputLen, output, outputOffset);
        if (numBytes != inputLen) {
            throw new GeneralSecurityException("stored output's length does not match input's length");
        }
    }
}
