package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.widget.CheckedTextView;
import androidx.annotation.ReplaceWith;

/* JADX INFO: loaded from: classes12.dex */
public final class CheckedTextViewCompat {
    private CheckedTextViewCompat() {
    }

    public static void setCheckMarkTintList(CheckedTextView textView, ColorStateList tint) {
        textView.setCheckMarkTintList(tint);
    }

    public static ColorStateList getCheckMarkTintList(CheckedTextView textView) {
        return textView.getCheckMarkTintList();
    }

    public static void setCheckMarkTintMode(CheckedTextView textView, PorterDuff.Mode tintMode) {
        textView.setCheckMarkTintMode(tintMode);
    }

    public static PorterDuff.Mode getCheckMarkTintMode(CheckedTextView textView) {
        return textView.getCheckMarkTintMode();
    }

    @ReplaceWith(expression = "textView.getCheckMarkDrawable()")
    @Deprecated
    public static Drawable getCheckMarkDrawable(CheckedTextView textView) {
        return textView.getCheckMarkDrawable();
    }
}
