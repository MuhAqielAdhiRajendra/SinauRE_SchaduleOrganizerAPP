package com.google.crypto.tink.internal;

import com.google.crypto.tink.util.Bytes;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Objects;
import javax.annotation.Nullable;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* JADX INFO: loaded from: classes13.dex */
public final class Util {
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static int randKeyId() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] rand = new byte[4];
        int result = 0;
        while (result == 0) {
            secureRandom.nextBytes(rand);
            result = ((rand[0] & ByteCompanionObject.MAX_VALUE) << 24) | ((rand[1] & UByte.MAX_VALUE) << 16) | ((rand[2] & UByte.MAX_VALUE) << 8) | (rand[3] & UByte.MAX_VALUE);
        }
        return result;
    }

    private static final byte toByteFromPrintableAscii(char c) {
        if (c < '!' || c > '~') {
            throw new TinkBugException("Not a printable ASCII character: " + c);
        }
        return (byte) c;
    }

    public static final Bytes toBytesFromPrintableAscii(String s) {
        byte[] result = new byte[s.length()];
        for (int i = 0; i < s.length(); i++) {
            result[i] = toByteFromPrintableAscii(s.charAt(i));
        }
        return Bytes.copyFrom(result);
    }

    public static boolean isAndroid() {
        return Objects.equals(System.getProperty("java.vendor"), "The Android Project");
    }

    @Nullable
    public static Integer getAndroidApiLevel() {
        if (!isAndroid()) {
            return null;
        }
        return BuildDispatchedCode.getApiLevel();
    }

    private Util() {
    }
}
