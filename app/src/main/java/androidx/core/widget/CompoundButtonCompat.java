package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: classes12.dex */
public final class CompoundButtonCompat {
    private CompoundButtonCompat() {
    }

    public static void setButtonTintList(CompoundButton button, ColorStateList tint) {
        button.setButtonTintList(tint);
    }

    public static ColorStateList getButtonTintList(CompoundButton button) {
        return button.getButtonTintList();
    }

    public static void setButtonTintMode(CompoundButton button, PorterDuff.Mode tintMode) {
        button.setButtonTintMode(tintMode);
    }

    public static PorterDuff.Mode getButtonTintMode(CompoundButton button) {
        return button.getButtonTintMode();
    }

    public static Drawable getButtonDrawable(CompoundButton button) {
        return button.getButtonDrawable();
    }
}
