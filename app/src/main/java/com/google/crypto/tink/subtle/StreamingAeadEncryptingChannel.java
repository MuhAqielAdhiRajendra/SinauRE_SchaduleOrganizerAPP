package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.security.GeneralSecurityException;

/* JADX INFO: loaded from: classes13.dex */
class StreamingAeadEncryptingChannel implements WritableByteChannel {
    private WritableByteChannel ciphertextChannel;
    ByteBuffer ctBuffer;
    private StreamSegmentEncrypter encrypter;
    boolean open = true;
    private int plaintextSegmentSize;
    ByteBuffer ptBuffer;

    public StreamingAeadEncryptingChannel(NonceBasedStreamingAead streamAead, WritableByteChannel ciphertextChannel, byte[] associatedData) throws GeneralSecurityException, IOException {
        this.ciphertextChannel = ciphertextChannel;
        this.encrypter = streamAead.newStreamSegmentEncrypter(associatedData);
        this.plaintextSegmentSize = streamAead.getPlaintextSegmentSize();
        this.ptBuffer = ByteBuffer.allocate(this.plaintextSegmentSize);
        this.ptBuffer.limit(this.plaintextSegmentSize - streamAead.getCiphertextOffset());
        this.ctBuffer = ByteBuffer.allocate(streamAead.getCiphertextSegmentSize());
        this.ctBuffer.put(this.encrypter.getHeader());
        this.ctBuffer.flip();
        ciphertextChannel.write(this.ctBuffer);
    }

    @Override // java.nio.channels.WritableByteChannel
    public synchronized int write(ByteBuffer pt) throws IOException {
        if (!this.open) {
            throw new ClosedChannelException();
        }
        if (this.ctBuffer.remaining() > 0) {
            this.ciphertextChannel.write(this.ctBuffer);
        }
        int startPosition = pt.position();
        while (pt.remaining() > this.ptBuffer.remaining()) {
            if (this.ctBuffer.remaining() > 0) {
                return pt.position() - startPosition;
            }
            int sliceSize = this.ptBuffer.remaining();
            ByteBuffer slice = pt.slice();
            slice.limit(sliceSize);
            pt.position(pt.position() + sliceSize);
            try {
                this.ptBuffer.flip();
                this.ctBuffer.clear();
                int iRemaining = slice.remaining();
                StreamSegmentEncrypter streamSegmentEncrypter = this.encrypter;
                if (iRemaining != 0) {
                    streamSegmentEncrypter.encryptSegment(this.ptBuffer, slice, false, this.ctBuffer);
                } else {
                    streamSegmentEncrypter.encryptSegment(this.ptBuffer, false, this.ctBuffer);
                }
                this.ctBuffer.flip();
                this.ciphertextChannel.write(this.ctBuffer);
                this.ptBuffer.clear();
                this.ptBuffer.limit(this.plaintextSegmentSize);
            } catch (GeneralSecurityException ex) {
                throw new IOException(ex);
            }
        }
        this.ptBuffer.put(pt);
        return pt.position() - startPosition;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        int n;
        if (this.open) {
            while (this.ctBuffer.remaining() > 0) {
                int n2 = this.ciphertextChannel.write(this.ctBuffer);
                if (n2 <= 0) {
                    throw new IOException("Failed to write ciphertext before closing");
                }
            }
            try {
                this.ctBuffer.clear();
                this.ptBuffer.flip();
                this.encrypter.encryptSegment(this.ptBuffer, true, this.ctBuffer);
                this.ctBuffer.flip();
                do {
                    int iRemaining = this.ctBuffer.remaining();
                    WritableByteChannel writableByteChannel = this.ciphertextChannel;
                    if (iRemaining > 0) {
                        n = writableByteChannel.write(this.ctBuffer);
                    } else {
                        writableByteChannel.close();
                        this.open = false;
                        return;
                    }
                } while (n > 0);
                throw new IOException("Failed to write ciphertext before closing");
            } catch (GeneralSecurityException ex) {
                throw new IOException(ex);
            }
        }
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.open;
    }
}
