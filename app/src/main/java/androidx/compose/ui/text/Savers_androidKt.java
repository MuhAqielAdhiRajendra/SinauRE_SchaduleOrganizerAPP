package androidx.compose.ui.text;

import androidx.compose.runtime.saveable.Saver;
import androidx.compose.runtime.saveable.SaverKt;
import androidx.compose.runtime.saveable.SaverScope;
import androidx.compose.ui.text.EmojiSupportMatch;
import androidx.compose.ui.text.PlatformParagraphStyle;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextMotion;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Savers.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\n\" \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u000f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0010\"\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0014\"\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0018\"\u001a\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Saver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/PlatformParagraphStyle;", "", "Landroidx/compose/ui/text/PlatformParagraphStyle$Companion;", "getSaver", "(Landroidx/compose/ui/text/PlatformParagraphStyle$Companion;)Landroidx/compose/runtime/saveable/Saver;", "PlatformParagraphStyleSaver", "Landroidx/compose/ui/text/EmojiSupportMatch;", "Landroidx/compose/ui/text/EmojiSupportMatch$Companion;", "(Landroidx/compose/ui/text/EmojiSupportMatch$Companion;)Landroidx/compose/runtime/saveable/Saver;", "emojiSupportMatchSaver", "getEmojiSupportMatchSaver", "()Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/ui/text/style/LineBreak;", "Landroidx/compose/ui/text/style/LineBreak$Companion;", "(Landroidx/compose/ui/text/style/LineBreak$Companion;)Landroidx/compose/runtime/saveable/Saver;", "LineBreakSaver", "Landroidx/compose/ui/text/style/TextMotion;", "Landroidx/compose/ui/text/style/TextMotion$Companion;", "(Landroidx/compose/ui/text/style/TextMotion$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextMotionSaver", "Landroidx/compose/ui/text/style/TextMotion$Linearity;", "Landroidx/compose/ui/text/style/TextMotion$Linearity$Companion;", "(Landroidx/compose/ui/text/style/TextMotion$Linearity$Companion;)Landroidx/compose/runtime/saveable/Saver;", "TextMotionLinearitySaver", "ui-text"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class Savers_androidKt {
    private static final Saver<PlatformParagraphStyle, Object> PlatformParagraphStyleSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            PlatformParagraphStyle platformParagraphStyle = (PlatformParagraphStyle) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(Boolean.valueOf(platformParagraphStyle.getIncludeFontPadding())), SaversKt.save(EmojiSupportMatch.m7420boximpl(platformParagraphStyle.getEmojiSupportMatch()), Savers_androidKt.getSaver(EmojiSupportMatch.INSTANCE), (SaverScope) obj));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Savers_androidKt.PlatformParagraphStyleSaver$lambda$1(obj);
        }
    });
    private static final Saver<EmojiSupportMatch, Object> emojiSupportMatchSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((EmojiSupportMatch) obj2).getValue());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Savers_androidKt.emojiSupportMatchSaver$lambda$1(obj);
        }
    });
    private static final Saver<LineBreak, Object> LineBreakSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((LineBreak) obj2).getMask());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Savers_androidKt.LineBreakSaver$lambda$1(obj);
        }
    });
    private static final Saver<TextMotion, Object> TextMotionSaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            TextMotion textMotion = (TextMotion) obj2;
            return CollectionsKt.arrayListOf(SaversKt.save(TextMotion.Linearity.m8038boximpl(textMotion.getLinearity()), Savers_androidKt.getSaver(TextMotion.Linearity.INSTANCE), (SaverScope) obj), SaversKt.save(Boolean.valueOf(textMotion.getSubpixelTextPositioning())));
        }
    }, new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Savers_androidKt.TextMotionSaver$lambda$1(obj);
        }
    });
    private static final Saver<TextMotion.Linearity, Object> TextMotionLinearitySaver = SaverKt.Saver(new Function2() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Integer.valueOf(((TextMotion.Linearity) obj2).getValue());
        }
    }, new Function1() { // from class: androidx.compose.ui.text.Savers_androidKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return Savers_androidKt.TextMotionLinearitySaver$lambda$1(obj);
        }
    });

    public static final Saver<PlatformParagraphStyle, Object> getSaver(PlatformParagraphStyle.Companion $this$Saver) {
        return PlatformParagraphStyleSaver;
    }

    static final PlatformParagraphStyle PlatformParagraphStyleSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Boolean bool = value$iv != null ? (Boolean) value$iv : null;
        Intrinsics.checkNotNull(bool);
        boolean zBooleanValue = bool.booleanValue();
        Object value$iv2 = list.get(1);
        Saver<EmojiSupportMatch, Object> saver = getSaver(EmojiSupportMatch.INSTANCE);
        EmojiSupportMatch emojiSupportMatchRestore = ((!Intrinsics.areEqual(value$iv2, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv2 != null) ? saver.restore(value$iv2) : null;
        Intrinsics.checkNotNull(emojiSupportMatchRestore);
        return new PlatformParagraphStyle(emojiSupportMatchRestore.getValue(), zBooleanValue, (DefaultConstructorMarker) null);
    }

    public static final Saver<EmojiSupportMatch, Object> getSaver(EmojiSupportMatch.Companion $this$Saver) {
        return emojiSupportMatchSaver;
    }

    public static final Saver<EmojiSupportMatch, Object> getEmojiSupportMatchSaver() {
        return emojiSupportMatchSaver;
    }

    static final EmojiSupportMatch emojiSupportMatchSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return EmojiSupportMatch.m7420boximpl(EmojiSupportMatch.m7421constructorimpl(((Integer) it).intValue()));
    }

    public static final Saver<LineBreak, Object> getSaver(LineBreak.Companion $this$Saver) {
        return LineBreakSaver;
    }

    static final LineBreak LineBreakSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return LineBreak.m7901boximpl(LineBreak.m7902constructorimpl(((Integer) it).intValue()));
    }

    public static final Saver<TextMotion, Object> getSaver(TextMotion.Companion $this$Saver) {
        return TextMotionSaver;
    }

    static final TextMotion TextMotionSaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        List list = (List) it;
        Object value$iv = list.get(0);
        Saver<TextMotion.Linearity, Object> saver = getSaver(TextMotion.Linearity.INSTANCE);
        TextMotion.Linearity linearityRestore = ((!Intrinsics.areEqual(value$iv, (Object) false) || (saver instanceof NonNullValueClassSaver)) && value$iv != null) ? saver.restore(value$iv) : null;
        Intrinsics.checkNotNull(linearityRestore);
        int value = linearityRestore.getValue();
        Object value$iv2 = list.get(1);
        Boolean bool = value$iv2 != null ? (Boolean) value$iv2 : null;
        Intrinsics.checkNotNull(bool);
        return new TextMotion(value, bool.booleanValue(), null);
    }

    private static final Saver<TextMotion.Linearity, Object> getSaver(TextMotion.Linearity.Companion $this$Saver) {
        return TextMotionLinearitySaver;
    }

    static final TextMotion.Linearity TextMotionLinearitySaver$lambda$1(Object it) {
        Intrinsics.checkNotNull(it, "null cannot be cast to non-null type kotlin.Int");
        return TextMotion.Linearity.m8038boximpl(TextMotion.Linearity.m8039constructorimpl(((Integer) it).intValue()));
    }
}
