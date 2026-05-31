package androidx.compose.foundation.internal;

import android.os.Parcel;
import android.util.Base64;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;

/* JADX INFO: compiled from: ClipboardUtils.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0010J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015J\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b¢\u0006\u0004\b\u001c\u0010\u0019J\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u001e¢\u0006\u0004\b\u001f\u0010 J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010!\u001a\u00020\"J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010#\u001a\u00020$J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010'\u001a\u00020(J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010)\u001a\u00020*J\u000e\u0010\n\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,J\u0015\u0010\n\u001a\u00020\u00072\u0006\u0010-\u001a\u00020.¢\u0006\u0004\b/\u0010\u0010J\u000e\u0010\n\u001a\u00020\u00072\u0006\u00100\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Landroidx/compose/foundation/internal/EncodeHelper;", "", "<init>", "()V", "parcel", "Landroid/os/Parcel;", "reset", "", "encodedString", "", "encode", "spanStyle", "Landroidx/compose/ui/text/SpanStyle;", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "encode-8_81llA", "(J)V", "textUnit", "Landroidx/compose/ui/unit/TextUnit;", "encode--R2X_6o", "fontWeight", "Landroidx/compose/ui/text/font/FontWeight;", "fontStyle", "Landroidx/compose/ui/text/font/FontStyle;", "encode-nzbMABs", "(I)V", "fontSynthesis", "Landroidx/compose/ui/text/font/FontSynthesis;", "encode-6p3vJLY", "baselineShift", "Landroidx/compose/ui/text/style/BaselineShift;", "encode-4Dl_Bck", "(F)V", "textGeometricTransform", "Landroidx/compose/ui/text/style/TextGeometricTransform;", "textDecoration", "Landroidx/compose/ui/text/style/TextDecoration;", "shadow", "Landroidx/compose/ui/graphics/Shadow;", "byte", "", "int", "", TypedValues.Custom.S_FLOAT, "", "uLong", "Lkotlin/ULong;", "encode-VKZWuLQ", TypedValues.Custom.S_STRING, "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class EncodeHelper {
    public static final int $stable = 8;
    private Parcel parcel = Parcel.obtain();

    public final void reset() {
        this.parcel.recycle();
        this.parcel = Parcel.obtain();
    }

    public final String encodedString() {
        byte[] bytes = this.parcel.marshall();
        return Base64.encodeToString(bytes, 0);
    }

    public final void encode(SpanStyle spanStyle) {
        if (!Color.m5314equalsimpl0(spanStyle.m7514getColor0d7_KjU(), Color.INSTANCE.m5349getUnspecified0d7_KjU())) {
            encode((byte) 1);
            m691encode8_81llA(spanStyle.m7514getColor0d7_KjU());
        }
        if (!TextUnit.m8341equalsimpl0(spanStyle.getFontSize(), TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE())) {
            encode((byte) 2);
            m688encodeR2X_6o(spanStyle.getFontSize());
        }
        FontWeight it = spanStyle.getFontWeight();
        if (it != null) {
            encode((byte) 3);
            encode(it);
        }
        FontStyle fontStyle = spanStyle.getFontStyle();
        if (fontStyle != null) {
            int it2 = fontStyle.m7688unboximpl();
            encode((byte) 4);
            m693encodenzbMABs(it2);
        }
        FontSynthesis fontSynthesis = spanStyle.getFontSynthesis();
        if (fontSynthesis != null) {
            int it3 = fontSynthesis.m7701unboximpl();
            encode((byte) 5);
            m690encode6p3vJLY(it3);
        }
        String it4 = spanStyle.getFontFeatureSettings();
        if (it4 != null) {
            encode((byte) 6);
            encode(it4);
        }
        if (!TextUnit.m8341equalsimpl0(spanStyle.getLetterSpacing(), TextUnit.INSTANCE.m8355getUnspecifiedXSAIIZE())) {
            encode((byte) 7);
            m688encodeR2X_6o(spanStyle.getLetterSpacing());
        }
        BaselineShift baselineShift = spanStyle.getBaselineShift();
        if (baselineShift != null) {
            float it5 = baselineShift.m7871unboximpl();
            encode((byte) 8);
            m689encode4Dl_Bck(it5);
        }
        TextGeometricTransform it6 = spanStyle.getTextGeometricTransform();
        if (it6 != null) {
            encode((byte) 9);
            encode(it6);
        }
        if (!Color.m5314equalsimpl0(spanStyle.getBackground(), Color.INSTANCE.m5349getUnspecified0d7_KjU())) {
            encode((byte) 10);
            m691encode8_81llA(spanStyle.getBackground());
        }
        TextDecoration it7 = spanStyle.getTextDecoration();
        if (it7 != null) {
            encode((byte) 11);
            encode(it7);
        }
        Shadow it8 = spanStyle.getShadow();
        if (it8 != null) {
            encode((byte) 12);
            encode(it8);
        }
    }

    /* JADX INFO: renamed from: encode-8_81llA, reason: not valid java name */
    public final void m691encode8_81llA(long color) {
        m692encodeVKZWuLQ(color);
    }

    /* JADX INFO: renamed from: encode--R2X_6o, reason: not valid java name */
    public final void m688encodeR2X_6o(long textUnit) {
        long jM8343getTypeUIouoOA = TextUnit.m8343getTypeUIouoOA(textUnit);
        byte typeCode = 0;
        if (!TextUnitType.m8372equalsimpl0(jM8343getTypeUIouoOA, TextUnitType.INSTANCE.m8378getUnspecifiedUIouoOA())) {
            if (TextUnitType.m8372equalsimpl0(jM8343getTypeUIouoOA, TextUnitType.INSTANCE.m8377getSpUIouoOA())) {
                typeCode = 1;
            } else if (TextUnitType.m8372equalsimpl0(jM8343getTypeUIouoOA, TextUnitType.INSTANCE.m8376getEmUIouoOA())) {
                typeCode = 2;
            }
        }
        encode(typeCode);
        if (!TextUnitType.m8372equalsimpl0(TextUnit.m8343getTypeUIouoOA(textUnit), TextUnitType.INSTANCE.m8378getUnspecifiedUIouoOA())) {
            encode(TextUnit.m8344getValueimpl(textUnit));
        }
    }

    public final void encode(FontWeight fontWeight) {
        encode(fontWeight.getWeight());
    }

    /* JADX INFO: renamed from: encode-nzbMABs, reason: not valid java name */
    public final void m693encodenzbMABs(int fontStyle) {
        byte b = 0;
        if (!FontStyle.m7685equalsimpl0(fontStyle, FontStyle.INSTANCE.m7692getNormal_LCdwA()) && FontStyle.m7685equalsimpl0(fontStyle, FontStyle.INSTANCE.m7691getItalic_LCdwA())) {
            b = 1;
        }
        encode(b);
    }

    /* JADX INFO: renamed from: encode-6p3vJLY, reason: not valid java name */
    public final void m690encode6p3vJLY(int fontSynthesis) {
        byte value = 0;
        if (!FontSynthesis.m7696equalsimpl0(fontSynthesis, FontSynthesis.INSTANCE.m7703getNoneGVVA2EU())) {
            if (FontSynthesis.m7696equalsimpl0(fontSynthesis, FontSynthesis.INSTANCE.m7702getAllGVVA2EU())) {
                value = 1;
            } else if (FontSynthesis.m7696equalsimpl0(fontSynthesis, FontSynthesis.INSTANCE.m7705getWeightGVVA2EU())) {
                value = 2;
            } else if (FontSynthesis.m7696equalsimpl0(fontSynthesis, FontSynthesis.INSTANCE.m7704getStyleGVVA2EU())) {
                value = 3;
            }
        }
        encode(value);
    }

    /* JADX INFO: renamed from: encode-4Dl_Bck, reason: not valid java name */
    public final void m689encode4Dl_Bck(float baselineShift) {
        encode(baselineShift);
    }

    public final void encode(TextGeometricTransform textGeometricTransform) {
        encode(textGeometricTransform.getScaleX());
        encode(textGeometricTransform.getSkewX());
    }

    public final void encode(TextDecoration textDecoration) {
        encode(textDecoration.getMask());
    }

    public final void encode(Shadow shadow) {
        m691encode8_81llA(shadow.getColor());
        long arg0$iv = shadow.getOffset();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        encode(Float.intBitsToFloat(bits$iv$iv$iv));
        long arg0$iv2 = shadow.getOffset();
        int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
        encode(Float.intBitsToFloat(bits$iv$iv$iv2));
        encode(shadow.getBlurRadius());
    }

    public final void encode(byte b) {
        this.parcel.writeByte(b);
    }

    public final void encode(int i) {
        this.parcel.writeInt(i);
    }

    public final void encode(float f) {
        this.parcel.writeFloat(f);
    }

    /* JADX INFO: renamed from: encode-VKZWuLQ, reason: not valid java name */
    public final void m692encodeVKZWuLQ(long uLong) {
        this.parcel.writeLong(uLong);
    }

    public final void encode(String string) {
        this.parcel.writeString(string);
    }
}
