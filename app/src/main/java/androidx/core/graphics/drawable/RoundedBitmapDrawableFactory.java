package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.InputStream;

/* JADX INFO: loaded from: classes12.dex */
public final class RoundedBitmapDrawableFactory {
    private static final String TAG = "RoundedBitmapDrawableFa";

    public static RoundedBitmapDrawable create(Resources res, Bitmap bitmap) {
        return new RoundedBitmapDrawable21(res, bitmap);
    }

    public static RoundedBitmapDrawable create(Resources res, String filepath) {
        RoundedBitmapDrawable drawable = create(res, BitmapFactory.decodeFile(filepath));
        if (drawable.getBitmap() == null) {
            Log.w(TAG, "RoundedBitmapDrawable cannot decode " + filepath);
        }
        return drawable;
    }

    public static RoundedBitmapDrawable create(Resources res, InputStream is) {
        RoundedBitmapDrawable drawable = create(res, BitmapFactory.decodeStream(is));
        if (drawable.getBitmap() == null) {
            Log.w(TAG, "RoundedBitmapDrawable cannot decode " + is);
        }
        return drawable;
    }

    private RoundedBitmapDrawableFactory() {
    }
}
