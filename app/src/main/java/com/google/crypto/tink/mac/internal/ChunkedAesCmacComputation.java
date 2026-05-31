package com.google.crypto.tink.mac.internal;

import com.google.crypto.tink.InsecureSecretKeyAccess;
import com.google.crypto.tink.mac.AesCmacKey;
import com.google.crypto.tink.mac.AesCmacParameters;
import com.google.crypto.tink.mac.ChunkedMacComputation;
import com.google.crypto.tink.subtle.Bytes;
import com.google.crypto.tink.subtle.EngineFactory;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes13.dex */
final class ChunkedAesCmacComputation implements ChunkedMacComputation {
    private static final byte[] FORMAT_VERSION = {0};
    private final AesCmacKey key;
    private final ByteBuffer localStash;
    private final byte[] subKey1;
    private final byte[] subKey2;
    private final ByteBuffer x;
    private final ByteBuffer y;
    private boolean finalized = false;
    private final Cipher aes = EngineFactory.CIPHER.getInstance("AES/ECB/NoPadding");

    ChunkedAesCmacComputation(AesCmacKey key) throws GeneralSecurityException {
        this.key = key;
        this.aes.init(1, new SecretKeySpec(this.key.getAesKey().toByteArray(InsecureSecretKeyAccess.get()), "AES"));
        byte[] zeroes = new byte[16];
        byte[] l = this.aes.doFinal(zeroes);
        this.subKey1 = AesUtil.dbl(l);
        this.subKey2 = AesUtil.dbl(this.subKey1);
        this.localStash = ByteBuffer.allocate(16);
        this.x = ByteBuffer.allocate(16);
        this.y = ByteBuffer.allocate(16);
    }

    private void munch(ByteBuffer data) throws GeneralSecurityException {
        this.y.rewind();
        this.x.rewind();
        Bytes.xor(this.y, this.x, data, 16);
        this.y.rewind();
        this.x.rewind();
        this.aes.doFinal(this.y, this.x);
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacComputation
    public void update(ByteBuffer data) throws GeneralSecurityException {
        if (this.finalized) {
            throw new IllegalStateException("Can not update after computing the MAC tag. Please create a new object.");
        }
        if (this.localStash.remaining() != 16) {
            int bytesToCopy = Math.min(this.localStash.remaining(), data.remaining());
            for (int i = 0; i < bytesToCopy; i++) {
                this.localStash.put(data.get());
            }
        }
        if (this.localStash.remaining() == 0 && data.remaining() > 0) {
            this.localStash.rewind();
            munch(this.localStash);
            this.localStash.rewind();
        }
        while (data.remaining() > 16) {
            munch(data);
        }
        this.localStash.put(data);
    }

    @Override // com.google.crypto.tink.mac.ChunkedMacComputation
    public byte[] computeMac() throws GeneralSecurityException {
        byte[] lastChunkToPad;
        if (this.finalized) {
            throw new IllegalStateException("Can not compute after computing the MAC tag. Please create a new object.");
        }
        if (this.key.getParameters().getVariant() == AesCmacParameters.Variant.LEGACY) {
            update(ByteBuffer.wrap(FORMAT_VERSION));
        }
        this.finalized = true;
        int iRemaining = this.localStash.remaining();
        ByteBuffer byteBuffer = this.localStash;
        if (iRemaining > 0) {
            byte[] lastChunkToPad2 = Arrays.copyOf(byteBuffer.array(), this.localStash.position());
            lastChunkToPad = Bytes.xor(AesUtil.cmacPad(lastChunkToPad2), this.subKey2);
        } else {
            byte[] mLast = byteBuffer.array();
            lastChunkToPad = Bytes.xor(mLast, 0, this.subKey1, 0, 16);
        }
        return Bytes.concat(this.key.getOutputPrefix().toByteArray(), Arrays.copyOf(this.aes.doFinal(Bytes.xor(lastChunkToPad, this.x.array())), this.key.getParameters().getCryptographicTagSizeBytes()));
    }
}
