package androidx.core.view.accessibility;

import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.LocaleList;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;
import androidx.core.os.LocaleListCompat;

/* JADX INFO: loaded from: classes12.dex */
public class AccessibilityWindowInfoCompat {
    public static final int TYPE_ACCESSIBILITY_OVERLAY = 4;
    public static final int TYPE_APPLICATION = 1;
    public static final int TYPE_INPUT_METHOD = 2;
    public static final int TYPE_MAGNIFICATION_OVERLAY = 6;
    public static final int TYPE_SPLIT_SCREEN_DIVIDER = 5;
    public static final int TYPE_SYSTEM = 3;
    private final AccessibilityWindowInfo mInfo;

    static AccessibilityWindowInfoCompat wrapNonNullInstance(AccessibilityWindowInfo object) {
        if (object != null) {
            return new AccessibilityWindowInfoCompat(object);
        }
        return null;
    }

    public AccessibilityWindowInfoCompat() {
        if (Build.VERSION.SDK_INT >= 30) {
            this.mInfo = Api30Impl.instantiateAccessibilityWindowInfo();
        } else {
            this.mInfo = null;
        }
    }

    private AccessibilityWindowInfoCompat(AccessibilityWindowInfo info) {
        this.mInfo = info;
    }

    public int getType() {
        return this.mInfo.getType();
    }

    public int getLayer() {
        return this.mInfo.getLayer();
    }

    public AccessibilityNodeInfoCompat getRoot() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(this.mInfo.getRoot());
    }

    public AccessibilityNodeInfoCompat getRoot(int prefetchingStrategy) {
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getRoot(this.mInfo, prefetchingStrategy);
        }
        return getRoot();
    }

    public boolean isInPictureInPictureMode() {
        if (Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.isInPictureInPictureMode(this.mInfo);
        }
        return false;
    }

    public AccessibilityWindowInfoCompat getParent() {
        return wrapNonNullInstance(this.mInfo.getParent());
    }

    public int getId() {
        return this.mInfo.getId();
    }

    public void getRegionInScreen(Region outRegion) {
        if (Build.VERSION.SDK_INT >= 33) {
            Api33Impl.getRegionInScreen(this.mInfo, outRegion);
            return;
        }
        Rect outBounds = new Rect();
        this.mInfo.getBoundsInScreen(outBounds);
        outRegion.set(outBounds);
    }

    public void getBoundsInScreen(Rect outBounds) {
        this.mInfo.getBoundsInScreen(outBounds);
    }

    public boolean isActive() {
        return this.mInfo.isActive();
    }

    public boolean isFocused() {
        return this.mInfo.isFocused();
    }

    public boolean isAccessibilityFocused() {
        return this.mInfo.isAccessibilityFocused();
    }

    public int getChildCount() {
        return this.mInfo.getChildCount();
    }

    public AccessibilityWindowInfoCompat getChild(int index) {
        return wrapNonNullInstance(this.mInfo.getChild(index));
    }

    public int getDisplayId() {
        if (Build.VERSION.SDK_INT >= 33) {
            return Api33Impl.getDisplayId(this.mInfo);
        }
        return 0;
    }

    public long getTransitionTimeMillis() {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api34Impl.getTransitionTimeMillis(this.mInfo);
        }
        return 0L;
    }

    public LocaleListCompat getLocales() {
        if (Build.VERSION.SDK_INT >= 34) {
            return LocaleListCompat.wrap(Api34Impl.getLocales(this.mInfo));
        }
        return LocaleListCompat.getEmptyLocaleList();
    }

    public CharSequence getTitle() {
        return Api24Impl.getTitle(this.mInfo);
    }

    public AccessibilityNodeInfoCompat getAnchor() {
        return AccessibilityNodeInfoCompat.wrapNonNullInstance(Api24Impl.getAnchor(this.mInfo));
    }

    public static AccessibilityWindowInfoCompat obtain() {
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain());
    }

    public static AccessibilityWindowInfoCompat obtain(AccessibilityWindowInfoCompat info) {
        if (info == null) {
            return null;
        }
        return wrapNonNullInstance(AccessibilityWindowInfo.obtain(info.mInfo));
    }

    @Deprecated
    public void recycle() {
    }

    public AccessibilityWindowInfo unwrap() {
        return this.mInfo;
    }

    public int hashCode() {
        if (this.mInfo == null) {
            return 0;
        }
        return this.mInfo.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccessibilityWindowInfoCompat)) {
            return false;
        }
        AccessibilityWindowInfoCompat other = (AccessibilityWindowInfoCompat) obj;
        if (this.mInfo == null) {
            if (other.mInfo == null) {
                return true;
            }
            return false;
        }
        return this.mInfo.equals(other.mInfo);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        Rect bounds = new Rect();
        getBoundsInScreen(bounds);
        builder.append("AccessibilityWindowInfo[");
        builder.append("id=").append(getId());
        builder.append(", type=").append(typeToString(getType()));
        builder.append(", layer=").append(getLayer());
        builder.append(", bounds=").append(bounds);
        builder.append(", focused=").append(isFocused());
        builder.append(", active=").append(isActive());
        builder.append(", hasParent=").append(getParent() != null);
        builder.append(", hasChildren=").append(getChildCount() > 0);
        builder.append(", transitionTime=").append(getTransitionTimeMillis());
        builder.append(", locales=").append(getLocales());
        builder.append(']');
        return builder.toString();
    }

    private static String typeToString(int type) {
        switch (type) {
            case 1:
                return "TYPE_APPLICATION";
            case 2:
                return "TYPE_INPUT_METHOD";
            case 3:
                return "TYPE_SYSTEM";
            case 4:
                return "TYPE_ACCESSIBILITY_OVERLAY";
            default:
                return "<UNKNOWN>";
        }
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        static AccessibilityNodeInfo getAnchor(AccessibilityWindowInfo info) {
            return info.getAnchor();
        }

        static CharSequence getTitle(AccessibilityWindowInfo info) {
            return info.getTitle();
        }
    }

    private static class Api26Impl {
        private Api26Impl() {
        }

        static boolean isInPictureInPictureMode(AccessibilityWindowInfo info) {
            return info.isInPictureInPictureMode();
        }
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static AccessibilityWindowInfo instantiateAccessibilityWindowInfo() {
            return new AccessibilityWindowInfo();
        }
    }

    private static class Api33Impl {
        private Api33Impl() {
        }

        static int getDisplayId(AccessibilityWindowInfo info) {
            return info.getDisplayId();
        }

        static void getRegionInScreen(AccessibilityWindowInfo info, Region outRegion) {
            info.getRegionInScreen(outRegion);
        }

        public static AccessibilityNodeInfoCompat getRoot(Object info, int prefetchingStrategy) {
            return AccessibilityNodeInfoCompat.wrapNonNullInstance(((AccessibilityWindowInfo) info).getRoot(prefetchingStrategy));
        }
    }

    private static class Api34Impl {
        private Api34Impl() {
        }

        public static long getTransitionTimeMillis(AccessibilityWindowInfo info) {
            return info.getTransitionTimeMillis();
        }

        static LocaleList getLocales(AccessibilityWindowInfo info) {
            return info.getLocales();
        }
    }
}
