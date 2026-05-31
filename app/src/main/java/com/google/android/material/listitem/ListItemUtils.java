package com.google.android.material.listitem;

import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.GravityCompat;

/* JADX INFO: loaded from: classes13.dex */
class ListItemUtils {
    private ListItemUtils() {
    }

    static boolean isRightAligned(View view) {
        int gravity = GravityCompat.END;
        if (view.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) view.getLayoutParams();
            if (lp.gravity != -1) {
                gravity = lp.gravity;
            }
        }
        int absoluteGravity = GravityCompat.getAbsoluteGravity(gravity, view.getLayoutDirection()) & 7;
        return absoluteGravity == 5;
    }
}
