package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeySign;
import com.google.crypto.tink.config.internal.TinkFipsUtil;
import java.security.GeneralSecurityException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes13.dex */
public final class Ed25519Sign implements PublicKeySign {
    public static final TinkFipsUtil.AlgorithmFipsCompatibility FIPS = TinkFipsUtil.AlgorithmFipsCompatibility.ALGORITHM_NOT_FIPS;
    public static final int SECRET_KEY_LEN = 32;
    private final byte[] hashedPrivateKey;
    private final byte[] publicKey;

    public Ed25519Sign(final byte[] privateKey) throws GeneralSecurityException {
        if (!FIPS.isCompatible()) {
            throw new GeneralSecurityException("Can not use Ed25519 in FIPS-mode.");
        }
        if (privateKey.length != 32) {
            throw new IllegalArgumentException(String.format("Given private key's length is not %s", 32));
        }
        this.hashedPrivateKey = Ed25519.getHashedScalar(privateKey);
        this.publicKey = Ed25519.scalarMultWithBaseToBytes(this.hashedPrivateKey);
    }

    @Override // com.google.crypto.tink.PublicKeySign
    public byte[] sign(final byte[] data) throws GeneralSecurityException {
        return Ed25519.sign(data, this.publicKey, this.hashedPrivateKey);
    }

    public static final class KeyPair {
        private final byte[] privateKey;
        private final byte[] publicKey;

        private KeyPair(final byte[] publicKey, final byte[] privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public byte[] getPublicKey() {
            return Arrays.copyOf(this.publicKey, this.publicKey.length);
        }

        public byte[] getPrivateKey() {
            return Arrays.copyOf(this.privateKey, this.privateKey.length);
        }

        public static KeyPair newKeyPair() throws GeneralSecurityException {
            return newKeyPairFromSeed(Random.randBytes(32));
        }

        public static KeyPair newKeyPairFromSeed(byte[] secretSeed) throws GeneralSecurityException {
            if (secretSeed.length != 32) {
                throw new IllegalArgumentException(String.format("Given secret seed length is not %s", 32));
            }
            byte[] publicKey = Ed25519.scalarMultWithBaseToBytes(Ed25519.getHashedScalar(secretSeed));
            return new KeyPair(publicKey, secretSeed);
        }
    }
}
