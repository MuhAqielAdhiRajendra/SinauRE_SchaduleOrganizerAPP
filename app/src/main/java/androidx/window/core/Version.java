package androidx.window.core;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Version.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001dB)\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0016\u001a\u00020\u0007H\u0016J\u0011\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0018\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010\u001c\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Landroidx/window/core/Version;", "", "major", "", "minor", "patch", "description", "", "<init>", "(IIILjava/lang/String;)V", "getMajor", "()I", "getMinor", "getPatch", "getDescription", "()Ljava/lang/String;", "bigInteger", "Ljava/math/BigInteger;", "getBigInteger", "()Ljava/math/BigInteger;", "bigInteger$delegate", "Lkotlin/Lazy;", "toString", "compareTo", "other", "equals", "", "", "hashCode", "Companion", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Version implements Comparable<Version> {
    private static final String VERSION_PATTERN_STRING = "(\\d+)(?:\\.(\\d+))(?:\\.(\\d+))(?:-(.+))?";

    /* JADX INFO: renamed from: bigInteger$delegate, reason: from kotlin metadata */
    private final Lazy bigInteger;
    private final String description;
    private final int major;
    private final int minor;
    private final int patch;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Version UNKNOWN = new Version(0, 0, 0, "");
    private static final Version VERSION_0_1 = new Version(0, 1, 0, "");
    private static final Version VERSION_1_0 = new Version(1, 0, 0, "");
    private static final Version CURRENT = VERSION_1_0;

    public /* synthetic */ Version(int i, int i2, int i3, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, i3, str);
    }

    private Version(int major, int minor, int patch, String description) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.description = description;
        this.bigInteger = LazyKt.lazy(new Function0() { // from class: androidx.window.core.Version$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Version version = this.f$0;
                return BigInteger.valueOf(version.major).shiftLeft(32).or(BigInteger.valueOf(version.minor)).shiftLeft(32).or(BigInteger.valueOf(version.patch));
            }
        });
    }

    public final String getDescription() {
        return this.description;
    }

    public final int getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    public final int getPatch() {
        return this.patch;
    }

    private final BigInteger getBigInteger() {
        Object value = this.bigInteger.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (BigInteger) value;
    }

    public String toString() {
        String postfix;
        if (!StringsKt.isBlank(this.description)) {
            postfix = '-' + this.description;
        } else {
            postfix = "";
        }
        return this.major + '.' + this.minor + '.' + this.patch + postfix;
    }

    @Override // java.lang.Comparable
    public int compareTo(Version other) {
        Intrinsics.checkNotNullParameter(other, "other");
        return getBigInteger().compareTo(other.getBigInteger());
    }

    public boolean equals(Object other) {
        return (other instanceof Version) && this.major == ((Version) other).major && this.minor == ((Version) other).minor && this.patch == ((Version) other).patch;
    }

    public int hashCode() {
        int result = (17 * 31) + this.major;
        return (((result * 31) + this.minor) * 31) + this.patch;
    }

    /* JADX INFO: compiled from: Version.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/core/Version$Companion;", "", "<init>", "()V", "UNKNOWN", "Landroidx/window/core/Version;", "getUNKNOWN", "()Landroidx/window/core/Version;", "VERSION_0_1", "getVERSION_0_1", "VERSION_1_0", "getVERSION_1_0", "CURRENT", "getCURRENT", "VERSION_PATTERN_STRING", "", "parse", "versionString", "window_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Version getUNKNOWN() {
            return Version.UNKNOWN;
        }

        public final Version getVERSION_0_1() {
            return Version.VERSION_0_1;
        }

        public final Version getVERSION_1_0() {
            return Version.VERSION_1_0;
        }

        public final Version getCURRENT() {
            return Version.CURRENT;
        }

        @JvmStatic
        public final Version parse(String versionString) {
            String strGroup;
            if (versionString == null || StringsKt.isBlank(versionString)) {
                return null;
            }
            Matcher matcher = Pattern.compile(Version.VERSION_PATTERN_STRING).matcher(versionString);
            if (!matcher.matches() || (strGroup = matcher.group(1)) == null) {
                return null;
            }
            int major = Integer.parseInt(strGroup);
            String strGroup2 = matcher.group(2);
            if (strGroup2 == null) {
                return null;
            }
            int minor = Integer.parseInt(strGroup2);
            String strGroup3 = matcher.group(3);
            if (strGroup3 == null) {
                return null;
            }
            int patch = Integer.parseInt(strGroup3);
            String description = matcher.group(4) != null ? matcher.group(4) : "";
            Intrinsics.checkNotNull(description);
            return new Version(major, minor, patch, description, null);
        }
    }

    @JvmStatic
    public static final Version parse(String versionString) {
        return INSTANCE.parse(versionString);
    }
}
