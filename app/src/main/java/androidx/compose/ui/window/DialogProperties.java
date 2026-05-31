package androidx.compose.ui.window;

import android.os.IBinder;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidDialog.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0007\u0018\u00002\u00020\u0001BY\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u000f\u0010\u0010B'\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0011BE\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u0012B;\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0013B'\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u000f\u0010\u0014J\u0013\u0010\"\u001a\u00020\u00032\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010$\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u0006%"}, d2 = {"Landroidx/compose/ui/window/DialogProperties;", "", "dismissOnBackPress", "", "dismissOnClickOutside", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "usePlatformDefaultWidth", "decorFitsSystemWindows", "windowTitle", "", "windowType", "", "windowToken", "Landroid/os/IBinder;", "<init>", "(ZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZLjava/lang/String;ILandroid/os/IBinder;)V", "(ZZZ)V", "(ZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZLjava/lang/String;)V", "(ZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZ)V", "(ZZLandroidx/compose/ui/window/SecureFlagPolicy;)V", "getDismissOnBackPress", "()Z", "getDismissOnClickOutside", "getSecurePolicy", "()Landroidx/compose/ui/window/SecureFlagPolicy;", "getUsePlatformDefaultWidth", "getDecorFitsSystemWindows", "getWindowTitle", "()Ljava/lang/String;", "getWindowType", "()I", "getWindowToken", "()Landroid/os/IBinder;", "equals", "other", "hashCode", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DialogProperties {
    public static final int $stable = 0;
    private final boolean decorFitsSystemWindows;
    private final boolean dismissOnBackPress;
    private final boolean dismissOnClickOutside;
    private final SecureFlagPolicy securePolicy;
    private final boolean usePlatformDefaultWidth;
    private final String windowTitle;
    private final IBinder windowToken;
    private final int windowType;

    public DialogProperties() {
        this(false, false, null, false, false, null, 0, null, 255, null);
    }

    public DialogProperties(boolean dismissOnBackPress, boolean dismissOnClickOutside, SecureFlagPolicy securePolicy, boolean usePlatformDefaultWidth, boolean decorFitsSystemWindows, String windowTitle, int windowType, IBinder windowToken) {
        this.dismissOnBackPress = dismissOnBackPress;
        this.dismissOnClickOutside = dismissOnClickOutside;
        this.securePolicy = securePolicy;
        this.usePlatformDefaultWidth = usePlatformDefaultWidth;
        this.decorFitsSystemWindows = decorFitsSystemWindows;
        this.windowTitle = windowTitle;
        this.windowType = windowType;
        this.windowToken = windowToken;
    }

    public /* synthetic */ DialogProperties(boolean z, boolean z2, SecureFlagPolicy secureFlagPolicy, boolean z3, boolean z4, String str, int i, IBinder iBinder, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? true : z, (i2 & 2) != 0 ? true : z2, (i2 & 4) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy, (i2 & 8) != 0 ? true : z3, (i2 & 16) != 0 ? true : z4, (i2 & 32) != 0 ? "" : str, (i2 & 64) != 0 ? 2 : i, (i2 & 128) != 0 ? null : iBinder);
    }

    public final boolean getDismissOnBackPress() {
        return this.dismissOnBackPress;
    }

    public final boolean getDismissOnClickOutside() {
        return this.dismissOnClickOutside;
    }

    public final SecureFlagPolicy getSecurePolicy() {
        return this.securePolicy;
    }

    public final boolean getUsePlatformDefaultWidth() {
        return this.usePlatformDefaultWidth;
    }

    public final boolean getDecorFitsSystemWindows() {
        return this.decorFitsSystemWindows;
    }

    public final String getWindowTitle() {
        return this.windowTitle;
    }

    public final int getWindowType() {
        return this.windowType;
    }

    public final IBinder getWindowToken() {
        return this.windowToken;
    }

    public /* synthetic */ DialogProperties(boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3);
    }

    public DialogProperties(boolean dismissOnBackPress, boolean dismissOnClickOutside, boolean usePlatformDefaultWidth) {
        this(dismissOnBackPress, dismissOnClickOutside, SecureFlagPolicy.Inherit, usePlatformDefaultWidth, true, null, 0, null, 224, null);
    }

    public /* synthetic */ DialogProperties(boolean z, boolean z2, SecureFlagPolicy secureFlagPolicy, boolean z3, boolean z4, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy, (i & 8) != 0 ? true : z3, (i & 16) != 0 ? true : z4, (i & 32) != 0 ? "" : str);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ DialogProperties(boolean dismissOnBackPress, boolean dismissOnClickOutside, SecureFlagPolicy securePolicy, boolean usePlatformDefaultWidth, boolean decorFitsSystemWindows, String windowTitle) {
        this(dismissOnBackPress, dismissOnClickOutside, securePolicy, usePlatformDefaultWidth, decorFitsSystemWindows, windowTitle, 2, (IBinder) null);
    }

    public /* synthetic */ DialogProperties(boolean z, boolean z2, SecureFlagPolicy secureFlagPolicy, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy, (i & 8) != 0 ? true : z3, (i & 16) != 0 ? true : z4);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ DialogProperties(boolean dismissOnBackPress, boolean dismissOnClickOutside, SecureFlagPolicy securePolicy, boolean usePlatformDefaultWidth, boolean decorFitsSystemWindows) {
        this(dismissOnBackPress, dismissOnClickOutside, SecureFlagPolicy.Inherit, usePlatformDefaultWidth, true, "", 0, null, 192, null);
    }

    public /* synthetic */ DialogProperties(boolean z, boolean z2, SecureFlagPolicy secureFlagPolicy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? true : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ DialogProperties(boolean dismissOnBackPress, boolean dismissOnClickOutside, SecureFlagPolicy securePolicy) {
        this(dismissOnBackPress, dismissOnClickOutside, securePolicy, true, true, null, 0, null, 224, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof DialogProperties) && this.dismissOnBackPress == ((DialogProperties) other).dismissOnBackPress && this.dismissOnClickOutside == ((DialogProperties) other).dismissOnClickOutside && this.securePolicy == ((DialogProperties) other).securePolicy && this.usePlatformDefaultWidth == ((DialogProperties) other).usePlatformDefaultWidth && this.decorFitsSystemWindows == ((DialogProperties) other).decorFitsSystemWindows && this.windowType == ((DialogProperties) other).windowType && Intrinsics.areEqual(this.windowToken, ((DialogProperties) other).windowToken);
    }

    public int hashCode() {
        int result = Boolean.hashCode(this.dismissOnBackPress);
        int result2 = ((((((((((result * 31) + Boolean.hashCode(this.dismissOnClickOutside)) * 31) + this.securePolicy.hashCode()) * 31) + Boolean.hashCode(this.usePlatformDefaultWidth)) * 31) + Boolean.hashCode(this.decorFitsSystemWindows)) * 31) + this.windowType) * 31;
        IBinder iBinder = this.windowToken;
        return result2 + (iBinder != null ? iBinder.hashCode() : 0);
    }
}
