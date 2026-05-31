package androidx.compose.ui.autofill;

import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import kotlin.Metadata;

/* JADX INFO: compiled from: AndroidAutofillManager.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"logTag", "", "isAutofillable", "", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "isRelatedToAutoCommit", "isRelatedToAutofill", "ui"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class AndroidAutofillManager_androidKt {
    private static final String logTag = "ComposeAutofillManager";

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAutofillable(SemanticsConfiguration $this$isAutofillable) {
        return $this$isAutofillable.getProps$ui().contains(SemanticsActions.INSTANCE.getOnAutofillText()) || $this$isAutofillable.getProps$ui().contains(SemanticsActions.INSTANCE.getOnFillData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRelatedToAutoCommit(SemanticsConfiguration $this$isRelatedToAutoCommit) {
        return $this$isRelatedToAutoCommit.getProps$ui().contains(SemanticsProperties.INSTANCE.getContentType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRelatedToAutofill(SemanticsConfiguration $this$isRelatedToAutofill) {
        return $this$isRelatedToAutofill.getProps$ui().contains(SemanticsActions.INSTANCE.getOnAutofillText()) || $this$isRelatedToAutofill.getProps$ui().contains(SemanticsActions.INSTANCE.getOnFillData()) || $this$isRelatedToAutofill.getProps$ui().contains(SemanticsProperties.INSTANCE.getContentType()) || $this$isRelatedToAutofill.getProps$ui().contains(SemanticsProperties.INSTANCE.getContentDataType());
    }
}
