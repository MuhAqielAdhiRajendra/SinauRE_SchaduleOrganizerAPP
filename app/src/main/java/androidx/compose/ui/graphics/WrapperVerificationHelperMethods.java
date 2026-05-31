package androidx.compose.ui.graphics;

import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidPaint.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/ui/graphics/WrapperVerificationHelperMethods;", "", "<init>", "()V", "setBlendMode", "", "paint", "Landroid/graphics/Paint;", "mode", "Landroidx/compose/ui/graphics/BlendMode;", "setBlendMode-GB0RdKg", "(Landroid/graphics/Paint;I)V", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class WrapperVerificationHelperMethods {
    public static final int $stable = 0;
    public static final WrapperVerificationHelperMethods INSTANCE = new WrapperVerificationHelperMethods();

    private WrapperVerificationHelperMethods() {
    }

    /* JADX INFO: renamed from: setBlendMode-GB0RdKg, reason: not valid java name */
    public final void m5739setBlendModeGB0RdKg(android.graphics.Paint paint, int mode) {
        paint.setBlendMode(AndroidBlendMode_androidKt.m5160toAndroidBlendModes9anfk8(mode));
    }
}
