package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: AndroidPaint.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\t\b\u0016¢\u0006\u0004\b\u0004\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0003H\u0017R\u001a\u0010\u0002\u001a\u00020\u0003X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0005R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0016\u0010\r\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u001a8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR$\u0010\u001f\u001a\u00020 2\u0006\u0010\u001f\u001a\u00020 8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R$\u0010%\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R$\u0010+\u001a\u00020*2\u0006\u0010\u0013\u001a\u00020*8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b,\u0010'\"\u0004\b-\u0010)R$\u0010.\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b/\u0010\u0017\"\u0004\b0\u0010\u0019R$\u00102\u001a\u0002012\u0006\u0010\u0013\u001a\u0002018V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b3\u0010'\"\u0004\b4\u0010)R$\u00106\u001a\u0002052\u0006\u0010\u0013\u001a\u0002058V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b7\u0010'\"\u0004\b8\u0010)R$\u00109\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b:\u0010\u0017\"\u0004\b;\u0010\u0019R$\u0010=\u001a\u00020<2\u0006\u0010\u0013\u001a\u00020<8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b>\u0010'\"\u0004\b?\u0010)R4\u0010@\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000f2\u000e\u0010\u0013\u001a\n\u0018\u00010\u000ej\u0004\u0018\u0001`\u000f8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR(\u0010E\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00118V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR(\u0010K\u001a\u0004\u0018\u00010J2\b\u0010\u0013\u001a\u0004\u0018\u00010J@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010O¨\u0006P"}, d2 = {"Landroidx/compose/ui/graphics/AndroidPaint;", "Landroidx/compose/ui/graphics/Paint;", "internalPaint", "Landroid/graphics/Paint;", "<init>", "(Landroid/graphics/Paint;)V", "()V", "getInternalPaint$ui_graphics", "()Landroid/graphics/Paint;", "setInternalPaint$ui_graphics", "_blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "I", "internalShader", "Landroid/graphics/Shader;", "Landroidx/compose/ui/graphics/Shader;", "internalColorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "asFrameworkPaint", "value", "", "alpha", "getAlpha", "()F", "setAlpha", "(F)V", "", "isAntiAlias", "()Z", "setAntiAlias", "(Z)V", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "getColor-0d7_KjU", "()J", "setColor-8_81llA", "(J)V", "blendMode", "getBlendMode-0nO6VwU", "()I", "setBlendMode-s9anfk8", "(I)V", "Landroidx/compose/ui/graphics/PaintingStyle;", "style", "getStyle-TiuSbCo", "setStyle-k9PVt8s", "strokeWidth", "getStrokeWidth", "setStrokeWidth", "Landroidx/compose/ui/graphics/StrokeCap;", "strokeCap", "getStrokeCap-KaPHkGw", "setStrokeCap-BeK7IIE", "Landroidx/compose/ui/graphics/StrokeJoin;", "strokeJoin", "getStrokeJoin-LxFBmk8", "setStrokeJoin-Ww9F2mQ", "strokeMiterLimit", "getStrokeMiterLimit", "setStrokeMiterLimit", "Landroidx/compose/ui/graphics/FilterQuality;", "filterQuality", "getFilterQuality-f-v9h1I", "setFilterQuality-vDHp3xo", "shader", "getShader", "()Landroid/graphics/Shader;", "setShader", "(Landroid/graphics/Shader;)V", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "Landroidx/compose/ui/graphics/PathEffect;", "pathEffect", "getPathEffect", "()Landroidx/compose/ui/graphics/PathEffect;", "setPathEffect", "(Landroidx/compose/ui/graphics/PathEffect;)V", "ui-graphics"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class AndroidPaint implements Paint {
    public static final int $stable = 8;
    private int _blendMode;
    private ColorFilter internalColorFilter;
    private android.graphics.Paint internalPaint;
    private Shader internalShader;
    private PathEffect pathEffect;

    public AndroidPaint(android.graphics.Paint internalPaint) {
        this.internalPaint = internalPaint;
        this._blendMode = BlendMode.INSTANCE.m5253getSrcOver0nO6VwU();
    }

    public final android.graphics.Paint getInternalPaint$ui_graphics() {
        return this.internalPaint;
    }

    public final void setInternalPaint$ui_graphics(android.graphics.Paint paint) {
        this.internalPaint = paint;
    }

    public AndroidPaint() {
        this(AndroidPaint_androidKt.makeNativePaint());
    }

    @Override // androidx.compose.ui.graphics.Paint
    @Deprecated(message = "Use [nativePaint] extension instead", replaceWith = @ReplaceWith(expression = "nativePaint", imports = {}))
    /* JADX INFO: renamed from: asFrameworkPaint, reason: from getter */
    public android.graphics.Paint getInternalPaint() {
        return this.internalPaint;
    }

    @Override // androidx.compose.ui.graphics.Paint
    public float getAlpha() {
        return AndroidPaint_androidKt.getNativeAlpha(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public void setAlpha(float value) {
        AndroidPaint_androidKt.setNativeAlpha(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public boolean isAntiAlias() {
        return AndroidPaint_androidKt.getNativeAntiAlias(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public void setAntiAlias(boolean value) {
        AndroidPaint_androidKt.setNativeAntiAlias(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getColor-0d7_KjU, reason: not valid java name */
    public long mo5183getColor0d7_KjU() {
        return AndroidPaint_androidKt.getNativeColor(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: setColor-8_81llA, reason: not valid java name */
    public void mo5189setColor8_81llA(long color) {
        AndroidPaint_androidKt.m5195setNativeColor4WTKRHQ(this.internalPaint, color);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getBlendMode-0nO6VwU, reason: not valid java name and from getter */
    public int get_blendMode() {
        return this._blendMode;
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: setBlendMode-s9anfk8, reason: not valid java name */
    public void mo5188setBlendModes9anfk8(int value) {
        if (!BlendMode.m5222equalsimpl0(this._blendMode, value)) {
            this._blendMode = value;
            AndroidPaint_androidKt.m5194setNativeBlendModeGB0RdKg(this.internalPaint, value);
        }
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getStyle-TiuSbCo, reason: not valid java name */
    public int mo5187getStyleTiuSbCo() {
        return AndroidPaint_androidKt.getNativeStyle(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: setStyle-k9PVt8s, reason: not valid java name */
    public void mo5193setStylek9PVt8s(int value) {
        AndroidPaint_androidKt.m5199setNativeStyle5YerkU(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public float getStrokeWidth() {
        return AndroidPaint_androidKt.getNativeStrokeWidth(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public void setStrokeWidth(float value) {
        AndroidPaint_androidKt.setNativeStrokeWidth(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getStrokeCap-KaPHkGw, reason: not valid java name */
    public int mo5185getStrokeCapKaPHkGw() {
        return AndroidPaint_androidKt.getNativeStrokeCap(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: setStrokeCap-BeK7IIE, reason: not valid java name */
    public void mo5191setStrokeCapBeK7IIE(int value) {
        AndroidPaint_androidKt.m5197setNativeStrokeCapCSYIeUk(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getStrokeJoin-LxFBmk8, reason: not valid java name */
    public int mo5186getStrokeJoinLxFBmk8() {
        return AndroidPaint_androidKt.getNativeStrokeJoin(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: setStrokeJoin-Ww9F2mQ, reason: not valid java name */
    public void mo5192setStrokeJoinWw9F2mQ(int value) {
        AndroidPaint_androidKt.m5198setNativeStrokeJoinkLtJ_vA(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public float getStrokeMiterLimit() {
        return AndroidPaint_androidKt.getNativeStrokeMiterLimit(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public void setStrokeMiterLimit(float value) {
        AndroidPaint_androidKt.setNativeStrokeMiterLimit(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getFilterQuality-f-v9h1I, reason: not valid java name */
    public int mo5184getFilterQualityfv9h1I() {
        return AndroidPaint_androidKt.getNativeFilterQuality(this.internalPaint);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: setFilterQuality-vDHp3xo, reason: not valid java name */
    public void mo5190setFilterQualityvDHp3xo(int value) {
        AndroidPaint_androidKt.m5196setNativeFilterQuality50PEsBU(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getShader, reason: from getter */
    public Shader getInternalShader() {
        return this.internalShader;
    }

    @Override // androidx.compose.ui.graphics.Paint
    public void setShader(Shader value) {
        this.internalShader = value;
        AndroidPaint_androidKt.setNativeShader(this.internalPaint, this.internalShader);
    }

    @Override // androidx.compose.ui.graphics.Paint
    /* JADX INFO: renamed from: getColorFilter, reason: from getter */
    public ColorFilter getInternalColorFilter() {
        return this.internalColorFilter;
    }

    @Override // androidx.compose.ui.graphics.Paint
    public void setColorFilter(ColorFilter value) {
        this.internalColorFilter = value;
        AndroidPaint_androidKt.setNativeColorFilter(this.internalPaint, value);
    }

    @Override // androidx.compose.ui.graphics.Paint
    public PathEffect getPathEffect() {
        return this.pathEffect;
    }

    @Override // androidx.compose.ui.graphics.Paint
    public void setPathEffect(PathEffect value) {
        AndroidPaint_androidKt.setNativePathEffect(this.internalPaint, value);
        this.pathEffect = value;
    }
}
