package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: DateRangeInput.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
final class DateRangeInputKt$DateRangeInputContent$2$5 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ String $endRangeText;
    final /* synthetic */ String $pattern;

    DateRangeInputKt$DateRangeInputContent$2$5(String str, String str2) {
        this.$endRangeText = str;
        this.$pattern = str2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C106@4693L50,104@4603L159:DateRangeInput.kt#uh7d8r");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-882370893, $changed, -1, "androidx.compose.material3.DateRangeInputContent.<anonymous>.<anonymous> (DateRangeInput.kt:104)");
        }
        String str = this.$endRangeText;
        Modifier.Companion companion = Modifier.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, -1849867355, "CC(remember):DateRangeInput.kt#9igjgp");
        boolean invalid$iv = $composer.changed(this.$endRangeText) | $composer.changed(this.$pattern);
        final String str2 = this.$endRangeText;
        final String str3 = this.$pattern;
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function1() { // from class: androidx.compose.material3.DateRangeInputKt$DateRangeInputContent$2$5$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return DateRangeInputKt$DateRangeInputContent$2$5.invoke$lambda$1$lambda$0(str2, str3, (SemanticsPropertyReceiver) obj);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TextKt.m3157TextNvy7gAk(str, SemanticsModifierKt.semantics$default(companion, false, (Function1) it$iv, 1, null), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 0, 0, 262140);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    static final Unit invoke$lambda$1$lambda$0(String $endRangeText, String $pattern, SemanticsPropertyReceiver $this$semantics) {
        SemanticsPropertiesKt.setContentDescription($this$semantics, $endRangeText + ", " + $pattern);
        return Unit.INSTANCE;
    }
}
