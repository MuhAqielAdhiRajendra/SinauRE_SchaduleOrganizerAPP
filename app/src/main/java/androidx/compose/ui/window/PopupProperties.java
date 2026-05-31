package androidx.compose.ui.window;

import android.os.IBinder;
import androidx.core.view.PointerIconCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndroidPopup.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0007\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u000eB;\b\u0016\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u0011BO\b\u0017\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u0014BC\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u0015B1\b\u0017\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u0016BE\b\u0016\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u0017Be\b\u0016\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\r\u0010\u0018J\u0013\u0010(\u001a\u00020\u00052\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010*\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001cR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001cR\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u001cR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u000f\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b$\u0010\u001cR\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0011\u0010\u0010\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b'\u0010\u001c¨\u0006+"}, d2 = {"Landroidx/compose/ui/window/PopupProperties;", "", "flags", "", "inheritSecurePolicy", "", "dismissOnBackPress", "dismissOnClickOutside", "excludeFromSystemGesture", "usePlatformDefaultWidth", "windowType", "windowToken", "Landroid/os/IBinder;", "<init>", "(IZZZZZILandroid/os/IBinder;)V", "focusable", "clippingEnabled", "(ZZZZZ)V", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "(ZZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZZ)V", "(IZZZZZ)V", "(ZZZZ)V", "(ZZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZ)V", "(ZZZLandroidx/compose/ui/window/SecureFlagPolicy;ZZZILandroid/os/IBinder;)V", "getFlags$ui", "()I", "getInheritSecurePolicy$ui", "()Z", "getDismissOnBackPress", "getDismissOnClickOutside", "getExcludeFromSystemGesture", "getUsePlatformDefaultWidth", "getWindowType", "getWindowToken", "()Landroid/os/IBinder;", "getFocusable", "getSecurePolicy", "()Landroidx/compose/ui/window/SecureFlagPolicy;", "getClippingEnabled", "equals", "other", "hashCode", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class PopupProperties {
    public static final int $stable = 0;
    private final boolean dismissOnBackPress;
    private final boolean dismissOnClickOutside;
    private final boolean excludeFromSystemGesture;
    private final int flags;
    private final boolean inheritSecurePolicy;
    private final boolean usePlatformDefaultWidth;
    private final IBinder windowToken;
    private final int windowType;

    public PopupProperties(int flags, boolean inheritSecurePolicy, boolean dismissOnBackPress, boolean dismissOnClickOutside, boolean excludeFromSystemGesture, boolean usePlatformDefaultWidth, int windowType, IBinder windowToken) {
        this.flags = flags;
        this.inheritSecurePolicy = inheritSecurePolicy;
        this.dismissOnBackPress = dismissOnBackPress;
        this.dismissOnClickOutside = dismissOnClickOutside;
        this.excludeFromSystemGesture = excludeFromSystemGesture;
        this.usePlatformDefaultWidth = usePlatformDefaultWidth;
        this.windowType = windowType;
        this.windowToken = windowToken;
    }

    public /* synthetic */ PopupProperties(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i2, IBinder iBinder, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i3 & 2) != 0 ? true : z, (i3 & 4) != 0 ? true : z2, (i3 & 8) != 0 ? true : z3, (i3 & 16) != 0 ? true : z4, (i3 & 32) != 0 ? false : z5, (i3 & 64) != 0 ? PointerIconCompat.TYPE_HAND : i2, (i3 & 128) != 0 ? null : iBinder);
    }

    /* JADX INFO: renamed from: getFlags$ui, reason: from getter */
    public final int getFlags() {
        return this.flags;
    }

    /* JADX INFO: renamed from: getInheritSecurePolicy$ui, reason: from getter */
    public final boolean getInheritSecurePolicy() {
        return this.inheritSecurePolicy;
    }

    public final boolean getDismissOnBackPress() {
        return this.dismissOnBackPress;
    }

    public final boolean getDismissOnClickOutside() {
        return this.dismissOnClickOutside;
    }

    public final boolean getExcludeFromSystemGesture() {
        return this.excludeFromSystemGesture;
    }

    public final boolean getUsePlatformDefaultWidth() {
        return this.usePlatformDefaultWidth;
    }

    public final int getWindowType() {
        return this.windowType;
    }

    public final IBinder getWindowToken() {
        return this.windowToken;
    }

    public /* synthetic */ PopupProperties(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? true : z4, (i & 16) != 0 ? false : z5);
    }

    public PopupProperties(boolean focusable, boolean dismissOnBackPress, boolean dismissOnClickOutside, boolean clippingEnabled, boolean usePlatformDefaultWidth) {
        this(focusable, dismissOnBackPress, dismissOnClickOutside, SecureFlagPolicy.Inherit, true, clippingEnabled, usePlatformDefaultWidth, PointerIconCompat.TYPE_HAND, (IBinder) null);
    }

    public /* synthetic */ PopupProperties(boolean z, boolean z2, boolean z3, SecureFlagPolicy secureFlagPolicy, boolean z4, boolean z5, boolean z6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy, (i & 16) != 0 ? true : z4, (i & 32) != 0 ? true : z5, (i & 64) != 0 ? false : z6);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ PopupProperties(boolean focusable, boolean dismissOnBackPress, boolean dismissOnClickOutside, SecureFlagPolicy securePolicy, boolean excludeFromSystemGesture, boolean clippingEnabled, boolean usePlatformDefaultWidth) {
        this(focusable, dismissOnBackPress, dismissOnClickOutside, securePolicy, excludeFromSystemGesture, clippingEnabled, usePlatformDefaultWidth, PointerIconCompat.TYPE_HAND, (IBinder) null);
    }

    public /* synthetic */ PopupProperties(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? true : z, (i2 & 4) != 0 ? true : z2, (i2 & 8) != 0 ? true : z3, (i2 & 16) != 0 ? true : z4, (i2 & 32) != 0 ? false : z5);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ PopupProperties(int flags, boolean inheritSecurePolicy, boolean dismissOnBackPress, boolean dismissOnClickOutside, boolean excludeFromSystemGesture, boolean usePlatformDefaultWidth) {
        this(flags, inheritSecurePolicy, dismissOnBackPress, dismissOnClickOutside, excludeFromSystemGesture, usePlatformDefaultWidth, PointerIconCompat.TYPE_HAND, (IBinder) null);
    }

    public /* synthetic */ PopupProperties(boolean z, boolean z2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? true : z4);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    public /* synthetic */ PopupProperties(boolean focusable, boolean dismissOnBackPress, boolean dismissOnClickOutside, boolean clippingEnabled) {
        this(focusable, dismissOnBackPress, dismissOnClickOutside, SecureFlagPolicy.Inherit, true, clippingEnabled);
    }

    public /* synthetic */ PopupProperties(boolean z, boolean z2, boolean z3, SecureFlagPolicy secureFlagPolicy, boolean z4, boolean z5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2, (i & 4) != 0 ? true : z3, (i & 8) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy, (i & 16) != 0 ? true : z4, (i & 32) != 0 ? true : z5);
    }

    public PopupProperties(boolean focusable, boolean dismissOnBackPress, boolean dismissOnClickOutside, SecureFlagPolicy securePolicy, boolean excludeFromSystemGesture, boolean clippingEnabled) {
        this(focusable, dismissOnBackPress, dismissOnClickOutside, securePolicy, excludeFromSystemGesture, clippingEnabled, false, 0, null, 384, null);
    }

    public /* synthetic */ PopupProperties(boolean z, boolean z2, boolean z3, SecureFlagPolicy secureFlagPolicy, boolean z4, boolean z5, boolean z6, int i, IBinder iBinder, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? false : z, (i2 & 2) != 0 ? true : z2, (i2 & 4) != 0 ? true : z3, (i2 & 8) != 0 ? SecureFlagPolicy.Inherit : secureFlagPolicy, (i2 & 16) != 0 ? true : z4, (i2 & 32) != 0 ? true : z5, (i2 & 64) != 0 ? false : z6, (i2 & 128) != 0 ? PointerIconCompat.TYPE_HAND : i, (i2 & 256) != 0 ? null : iBinder);
    }

    public PopupProperties(boolean focusable, boolean dismissOnBackPress, boolean dismissOnClickOutside, SecureFlagPolicy securePolicy, boolean excludeFromSystemGesture, boolean clippingEnabled, boolean usePlatformDefaultWidth, int windowType, IBinder windowToken) {
        this(AndroidPopup_androidKt.createFlags(focusable, securePolicy, clippingEnabled), securePolicy == SecureFlagPolicy.Inherit, dismissOnBackPress, dismissOnClickOutside, excludeFromSystemGesture, usePlatformDefaultWidth, windowType, windowToken);
    }

    public final boolean getFocusable() {
        return (this.flags & 8) == 0;
    }

    public final SecureFlagPolicy getSecurePolicy() {
        if (this.inheritSecurePolicy) {
            return SecureFlagPolicy.Inherit;
        }
        if ((this.flags & 8192) == 0) {
            return SecureFlagPolicy.SecureOff;
        }
        return SecureFlagPolicy.SecureOn;
    }

    public final boolean getClippingEnabled() {
        return (this.flags & 512) == 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof PopupProperties) && this.flags == ((PopupProperties) other).flags && this.inheritSecurePolicy == ((PopupProperties) other).inheritSecurePolicy && this.dismissOnBackPress == ((PopupProperties) other).dismissOnBackPress && this.dismissOnClickOutside == ((PopupProperties) other).dismissOnClickOutside && this.excludeFromSystemGesture == ((PopupProperties) other).excludeFromSystemGesture && this.usePlatformDefaultWidth == ((PopupProperties) other).usePlatformDefaultWidth && this.windowType == ((PopupProperties) other).windowType && Intrinsics.areEqual(this.windowToken, ((PopupProperties) other).windowToken);
    }

    public int hashCode() {
        int result = this.flags;
        int result2 = ((((((((((((result * 31) + Boolean.hashCode(this.inheritSecurePolicy)) * 31) + Boolean.hashCode(this.dismissOnBackPress)) * 31) + Boolean.hashCode(this.dismissOnClickOutside)) * 31) + Boolean.hashCode(this.excludeFromSystemGesture)) * 31) + Boolean.hashCode(this.usePlatformDefaultWidth)) * 31) + this.windowType) * 31;
        IBinder iBinder = this.windowToken;
        return result2 + (iBinder != null ? iBinder.hashCode() : 0);
    }
}
