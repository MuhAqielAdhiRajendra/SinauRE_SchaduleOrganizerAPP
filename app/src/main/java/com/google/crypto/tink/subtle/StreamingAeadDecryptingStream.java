package com.google.crypto.tink.subtle;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import kotlin.UByte;

/* JADX INFO: loaded from: classes13.dex */
class StreamingAeadDecryptingStream extends FilterInputStream {
    private static final int PLAINTEXT_SEGMENT_EXTRA_SIZE = 16;
    private final byte[] aad;
    private final ByteBuffer ciphertextSegment;
    private final int ciphertextSegmentSize;
    private final StreamSegmentDecrypter decrypter;
    private boolean decryptionErrorOccured;
    private boolean endOfCiphertext;
    private boolean endOfPlaintext;
    private final int firstCiphertextSegmentSize;
    private final int headerLength;
    private boolean headerRead;
    private final ByteBuffer plaintextSegment;
    private int segmentNr;

    public StreamingAeadDecryptingStream(NonceBasedStreamingAead streamAead, InputStream ciphertextStream, byte[] associatedData) throws GeneralSecurityException, IOException {
        super(ciphertextStream);
        this.decrypter = streamAead.newStreamSegmentDecrypter();
        this.headerLength = streamAead.getHeaderLength();
        this.aad = Arrays.copyOf(associatedData, associatedData.length);
        this.ciphertextSegmentSize = streamAead.getCiphertextSegmentSize();
        this.ciphertextSegment = ByteBuffer.allocate(this.ciphertextSegmentSize + 1);
        this.ciphertextSegment.limit(0);
        this.firstCiphertextSegmentSize = this.ciphertextSegmentSize - streamAead.getCiphertextOffset();
        this.plaintextSegment = ByteBuffer.allocate(streamAead.getPlaintextSegmentSize() + 16);
        this.plaintextSegment.limit(0);
        this.headerRead = false;
        this.endOfCiphertext = false;
        this.endOfPlaintext = false;
        this.segmentNr = 0;
        this.decryptionErrorOccured = false;
    }

    private void readHeader() throws IOException {
        if (this.headerRead) {
            setDecryptionErrorOccured();
            throw new IOException("Decryption failed.");
        }
        ByteBuffer header = ByteBuffer.allocate(this.headerLength);
        while (header.remaining() > 0) {
            int read = this.in.read(header.array(), header.position(), header.remaining());
            if (read == -1) {
                setDecryptionErrorOccured();
                throw new IOException("Ciphertext is too short");
            }
            if (read == 0) {
                throw new IOException("Could not read bytes from the ciphertext stream");
            }
            header.position(header.position() + read);
        }
        header.flip();
        try {
            this.decrypter.init(header, this.aad);
            this.headerRead = true;
        } catch (GeneralSecurityException ex) {
            throw new IOException(ex);
        }
    }

    private void setDecryptionErrorOccured() {
        this.decryptionErrorOccured = true;
        this.plaintextSegment.limit(0);
    }

    private void loadSegment() throws IOException {
        while (!this.endOfCiphertext && this.ciphertextSegment.remaining() > 0) {
            int read = this.in.read(this.ciphertextSegment.array(), this.ciphertextSegment.position(), this.ciphertextSegment.remaining());
            if (read > 0) {
                this.ciphertextSegment.position(this.ciphertextSegment.position() + read);
            } else if (read == -1) {
                this.endOfCiphertext = true;
            } else if (read == 0) {
                throw new IOException("Could not read bytes from the ciphertext stream");
            }
        }
        byte lastByte = 0;
        if (!this.endOfCiphertext) {
            lastByte = this.ciphertextSegment.get(this.ciphertextSegment.position() - 1);
            this.ciphertextSegment.position(this.ciphertextSegment.position() - 1);
        }
        this.ciphertextSegment.flip();
        this.plaintextSegment.clear();
        try {
            this.decrypter.decryptSegment(this.ciphertextSegment, this.segmentNr, this.endOfCiphertext, this.plaintextSegment);
            this.segmentNr++;
            this.plaintextSegment.flip();
            this.ciphertextSegment.clear();
            if (!this.endOfCiphertext) {
                this.ciphertextSegment.clear();
                this.ciphertextSegment.limit(this.ciphertextSegmentSize + 1);
                this.ciphertextSegment.put(lastByte);
            }
        } catch (GeneralSecurityException ex) {
            setDecryptionErrorOccured();
            throw new IOException(ex.getMessage() + "\n" + toString() + "\nsegmentNr:" + this.segmentNr + " endOfCiphertext:" + this.endOfCiphertext, ex);
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        byte[] oneByte = new byte[1];
        int ret = read(oneByte, 0, 1);
        if (ret == 1) {
            return oneByte[0] & UByte.MAX_VALUE;
        }
        if (ret == -1) {
            return ret;
        }
        throw new IOException("Reading failed");
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] dst) throws IOException {
        return read(dst, 0, dst.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] dst, int offset, int length) throws IOException {
        if (this.decryptionErrorOccured) {
            throw new IOException("Decryption failed.");
        }
        if (!this.headerRead) {
            readHeader();
            this.ciphertextSegment.clear();
            this.ciphertextSegment.limit(this.firstCiphertextSegmentSize + 1);
        }
        if (this.endOfPlaintext) {
            return -1;
        }
        int bytesRead = 0;
        while (true) {
            if (bytesRead >= length) {
                break;
            }
            if (this.plaintextSegment.remaining() == 0) {
                if (this.endOfCiphertext) {
                    this.endOfPlaintext = true;
                    break;
                }
                loadSegment();
            }
            int sliceSize = Math.min(this.plaintextSegment.remaining(), length - bytesRead);
            this.plaintextSegment.get(dst, bytesRead + offset, sliceSize);
            bytesRead += sliceSize;
        }
        if (bytesRead == 0 && this.endOfPlaintext) {
            return -1;
        }
        return bytesRead;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        super.close();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() {
        return this.plaintextSegment.remaining();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int readlimit) {
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long n) throws IOException {
        int bytesRead;
        long maxSkipBufferSize = this.ciphertextSegmentSize;
        long remaining = n;
        if (n <= 0) {
            return 0L;
        }
        int size = (int) Math.min(maxSkipBufferSize, remaining);
        byte[] skipBuffer = new byte[size];
        while (remaining > 0 && (bytesRead = read(skipBuffer, 0, (int) Math.min(size, remaining))) > 0) {
            remaining -= (long) bytesRead;
        }
        return n - remaining;
    }

    public synchronized String toString() {
        StringBuilder res;
        res = new StringBuilder();
        res.append("StreamingAeadDecryptingStream").append("\nsegmentNr:").append(this.segmentNr).append("\nciphertextSegmentSize:").append(this.ciphertextSegmentSize).append("\nheaderRead:").append(this.headerRead).append("\nendOfCiphertext:").append(this.endOfCiphertext).append("\nendOfPlaintext:").append(this.endOfPlaintext).append("\ndecryptionErrorOccured:").append(this.decryptionErrorOccured).append("\nciphertextSgement").append(" position:").append(this.ciphertextSegment.position()).append(" limit:").append(this.ciphertextSegment.limit()).append("\nplaintextSegment").append(" position:").append(this.plaintextSegment.position()).append(" limit:").append(this.plaintextSegment.limit());
        return res.toString();
    }
}
