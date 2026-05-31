package androidx.compose.runtime;

import androidx.collection.ObjectList;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.composer.RememberManager;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: Composition.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\b!\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\bH&J\b\u0010\f\u001a\u00020\bH&J\u0010\u0010\r\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\b\u0010\u000e\u001a\u00020\bH&J.\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0016H&J\u0018\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0012H&J\b\u0010\u0019\u001a\u00020\bH&J\u0018\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH&J\u0010\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u001cH&J\u0018\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020$H&J\u0018\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020$2\u0006\u0010'\u001a\u00020$H&J\b\u0010(\u001a\u00020)H&J\b\u0010*\u001a\u00020\bH'J\u0010\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010,H'R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0006¨\u0006-"}, d2 = {"Landroidx/compose/runtime/SlotStorage;", "", "<init>", "()V", "isEmpty", "", "()Z", "clear", "", "rememberManager", "Landroidx/compose/runtime/composer/RememberManager;", "collectCalledByInformation", "collectSourceInformation", "deactivateAll", "dispose", "extractNestedStates", "Landroidx/collection/ScatterMap;", "Landroidx/compose/runtime/MovableContentStateReference;", "Landroidx/compose/runtime/MovableContentState;", "applier", "Landroidx/compose/runtime/Applier;", "references", "Landroidx/collection/ObjectList;", "disposeUnusedMovableContent", "state", "invalidateAll", "invalidateGroupsWithKey", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", TypedValues.AttributesType.S_TARGET, "", "ownsRecomposeScope", "scope", "groupContainsAnchor", "group", "anchor", "Landroidx/compose/runtime/Anchor;", "inGroup", "parent", "child", "toDebugString", "", "verifyWellFormed", "getSlots", "", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public abstract class SlotStorage {
    public static final int $stable = 0;

    public abstract void clear(RememberManager rememberManager);

    public abstract void collectCalledByInformation();

    public abstract void collectSourceInformation();

    public abstract void deactivateAll(RememberManager rememberManager);

    public abstract void dispose();

    public abstract void disposeUnusedMovableContent(RememberManager rememberManager, MovableContentState state);

    public abstract ScatterMap<MovableContentStateReference, MovableContentState> extractNestedStates(Applier<?> applier, ObjectList<MovableContentStateReference> references);

    /* JADX INFO: renamed from: getSlots */
    public abstract Iterable<Object> mo4584getSlots();

    public abstract boolean groupContainsAnchor(int group, Anchor anchor);

    public abstract boolean inGroup(Anchor parent, Anchor child);

    public abstract void invalidateAll();

    public abstract List<RecomposeScopeImpl> invalidateGroupsWithKey(int target);

    public abstract boolean isEmpty();

    public abstract boolean ownsRecomposeScope(RecomposeScopeImpl scope);

    public abstract String toDebugString();

    public abstract void verifyWellFormed();
}
