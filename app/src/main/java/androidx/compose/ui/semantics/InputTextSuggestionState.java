package androidx.compose.ui.semantics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: SemanticsProperties.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0011\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\u0013\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\r"}, d2 = {"Landroidx/compose/ui/semantics/InputTextSuggestionState;", "", "isCommittedByInputMethodEditor", "", "<init>", "(Z)V", "()Z", "toString", "", "hashCode", "", "equals", "other", "ui"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class InputTextSuggestionState {
    public static final int $stable = 0;
    private final boolean isCommittedByInputMethodEditor;

    public InputTextSuggestionState() {
        this(false, 1, null);
    }

    public InputTextSuggestionState(boolean isCommittedByInputMethodEditor) {
        this.isCommittedByInputMethodEditor = isCommittedByInputMethodEditor;
    }

    public /* synthetic */ InputTextSuggestionState(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    /* JADX INFO: renamed from: isCommittedByInputMethodEditor, reason: from getter */
    public final boolean getIsCommittedByInputMethodEditor() {
        return this.isCommittedByInputMethodEditor;
    }

    public String toString() {
        return "InputTextSuggestionState(isCommittedByInputMethodEditor=" + this.isCommittedByInputMethodEditor;
    }

    public int hashCode() {
        int result = Boolean.hashCode(this.isCommittedByInputMethodEditor);
        return result * 31;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof InputTextSuggestionState) && this.isCommittedByInputMethodEditor == ((InputTextSuggestionState) other).isCommittedByInputMethodEditor;
    }
}
