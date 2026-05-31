package androidx.core.view;

import android.view.ViewStructure;

/* JADX INFO: loaded from: classes12.dex */
public class ViewStructureCompat {
    private final ViewStructure mWrappedObj;

    public static ViewStructureCompat toViewStructureCompat(ViewStructure contentCaptureSession) {
        return new ViewStructureCompat(contentCaptureSession);
    }

    public ViewStructure toViewStructure() {
        return this.mWrappedObj;
    }

    private ViewStructureCompat(ViewStructure viewStructure) {
        this.mWrappedObj = viewStructure;
    }

    public void setText(CharSequence charSequence) {
        this.mWrappedObj.setText(charSequence);
    }

    public void setClassName(String string) {
        this.mWrappedObj.setClassName(string);
    }

    public void setContentDescription(CharSequence charSequence) {
        this.mWrappedObj.setContentDescription(charSequence);
    }

    public void setDimens(int left, int top, int scrollX, int scrollY, int width, int height) {
        this.mWrappedObj.setDimens(left, top, scrollX, scrollY, width, height);
    }
}
