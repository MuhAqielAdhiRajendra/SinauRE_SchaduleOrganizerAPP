package androidx.compose.runtime;

import androidx.compose.runtime.composer.DebugStringFormattable;
import androidx.compose.runtime.composer.RememberManager;
import androidx.compose.runtime.tooling.CompositionErrorContextImpl;
import kotlin.Metadata;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J.\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\u0006\u0010\u0011\u001a\u00020\u0010¨\u0006\u0012"}, d2 = {"Landroidx/compose/runtime/Changes;", "Landroidx/compose/runtime/composer/DebugStringFormattable;", "<init>", "()V", "clear", "", "execute", "slotStorage", "Landroidx/compose/runtime/SlotStorage;", "applier", "Landroidx/compose/runtime/Applier;", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "errorContext", "Landroidx/compose/runtime/tooling/CompositionErrorContextImpl;", "isEmpty", "", "isNotEmpty", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class Changes extends DebugStringFormattable {
    public static final int $stable = 0;

    public abstract void clear();

    public abstract void execute(SlotStorage slotStorage, Applier<?> applier, RememberManager rememberManager, CompositionErrorContextImpl errorContext);

    public abstract boolean isEmpty();

    public final boolean isNotEmpty() {
        return !isEmpty();
    }
}
