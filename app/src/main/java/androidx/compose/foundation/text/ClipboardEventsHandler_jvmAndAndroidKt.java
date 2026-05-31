package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.text.AnnotatedString;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: ClipboardEventsHandler.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aP\u0010\u0000\u001a\u00020\u00012\u0014\b\u0006\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0010\b\u0006\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00072\u0010\b\u0006\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u00072\u0006\u0010\t\u001a\u00020\u0001H\u0081\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"rememberClipboardEventsHandler", "", "onPaste", "Lkotlin/Function1;", "Landroidx/compose/ui/text/AnnotatedString;", "", "onCopy", "Lkotlin/Function0;", "onCut", "isEnabled", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;II)Z", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ClipboardEventsHandler_jvmAndAndroidKt {
    public static final boolean rememberClipboardEventsHandler(Function1<? super AnnotatedString, Unit> function1, Function0<AnnotatedString> function0, Function0<AnnotatedString> function02, boolean isEnabled, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, 1264411026, "CC(rememberClipboardEventsHandler)N(onPaste,onCopy,onCut,isEnabled):ClipboardEventsHandler.jvmAndAndroid.kt#423gt5");
        if ((i & 1) != 0) {
            Function1 onPaste = AnonymousClass1.INSTANCE;
        }
        if ((i & 2) != 0) {
            Function0 onCopy = new Function0() { // from class: androidx.compose.foundation.text.ClipboardEventsHandler_jvmAndAndroidKt.rememberClipboardEventsHandler.2
                @Override // kotlin.jvm.functions.Function0
                public final Void invoke() {
                    return null;
                }
            };
        }
        if ((i & 4) != 0) {
            Function0 onCut = AnonymousClass3.INSTANCE;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return false;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.ClipboardEventsHandler_jvmAndAndroidKt$rememberClipboardEventsHandler$1, reason: invalid class name */
    /* JADX INFO: compiled from: ClipboardEventsHandler.jvmAndAndroid.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function1<AnnotatedString, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(AnnotatedString annotatedString) {
            invoke2(annotatedString);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(AnnotatedString it) {
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.ClipboardEventsHandler_jvmAndAndroidKt$rememberClipboardEventsHandler$3, reason: invalid class name */
    /* JADX INFO: compiled from: ClipboardEventsHandler.jvmAndAndroid.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class AnonymousClass3 implements Function0 {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        @Override // kotlin.jvm.functions.Function0
        public final Void invoke() {
            return null;
        }
    }
}
