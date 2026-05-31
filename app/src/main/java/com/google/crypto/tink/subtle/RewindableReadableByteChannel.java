package com.google.crypto.tink.subtle;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/* JADX INFO: loaded from: classes13.dex */
public final class RewindableReadableByteChannel implements ReadableByteChannel {
    final ReadableByteChannel baseChannel;
    ByteBuffer buffer = null;
    boolean canRewind = true;
    boolean directRead = false;

    public RewindableReadableByteChannel(ReadableByteChannel baseChannel) {
        this.baseChannel = baseChannel;
    }

    public synchronized void disableRewinding() {
        this.canRewind = false;
    }

    public synchronized void rewind() throws IOException {
        if (!this.canRewind) {
            throw new IOException("Cannot rewind anymore.");
        }
        if (this.buffer != null) {
            this.buffer.position(0);
        }
    }

    private synchronized void setBufferLimit(int newLimit) {
        if (this.buffer.capacity() < newLimit) {
            int pos = this.buffer.position();
            int newBufferCapacity = Math.max(this.buffer.capacity() * 2, newLimit);
            ByteBuffer newBuffer = ByteBuffer.allocate(newBufferCapacity);
            this.buffer.rewind();
            newBuffer.put(this.buffer);
            newBuffer.position(pos);
            this.buffer = newBuffer;
        }
        this.buffer.limit(newLimit);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer dst) throws IOException {
        if (this.directRead) {
            return this.baseChannel.read(dst);
        }
        int bytesToReadCount = dst.remaining();
        if (bytesToReadCount == 0) {
            return 0;
        }
        if (this.buffer == null) {
            if (!this.canRewind) {
                this.directRead = true;
                return this.baseChannel.read(dst);
            }
            this.buffer = ByteBuffer.allocate(bytesToReadCount);
            int baseReadResult = this.baseChannel.read(this.buffer);
            this.buffer.flip();
            if (baseReadResult > 0) {
                dst.put(this.buffer);
            }
            return baseReadResult;
        }
        int iRemaining = this.buffer.remaining();
        ByteBuffer byteBuffer = this.buffer;
        if (iRemaining >= bytesToReadCount) {
            int limit = byteBuffer.limit();
            this.buffer.limit(this.buffer.position() + bytesToReadCount);
            dst.put(this.buffer);
            this.buffer.limit(limit);
            if (!this.canRewind && !this.buffer.hasRemaining()) {
                this.buffer = null;
                this.directRead = true;
            }
            return bytesToReadCount;
        }
        int bytesFromBufferCount = byteBuffer.remaining();
        int stillToReadCount = bytesToReadCount - bytesFromBufferCount;
        int currentReadPos = this.buffer.position();
        int contentLimit = this.buffer.limit();
        setBufferLimit(contentLimit + stillToReadCount);
        this.buffer.position(contentLimit);
        int baseReadResult2 = this.baseChannel.read(this.buffer);
        this.buffer.flip();
        this.buffer.position(currentReadPos);
        dst.put(this.buffer);
        if (bytesFromBufferCount == 0 && baseReadResult2 < 0) {
            return -1;
        }
        int bytesCount = this.buffer.position() - currentReadPos;
        if (!this.canRewind && !this.buffer.hasRemaining()) {
            this.buffer = null;
            this.directRead = true;
        }
        return bytesCount;
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.canRewind = false;
        this.directRead = true;
        this.baseChannel.close();
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.baseChannel.isOpen();
    }
}
