package androidx.compose.runtime;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectIntMap;
import androidx.compose.runtime.composer.gapbuffer.GapAnchor;
import androidx.compose.runtime.composer.gapbuffer.SlotTable;
import androidx.compose.runtime.composer.gapbuffer.SlotWriter;
import androidx.compose.runtime.tooling.IdentifiableRecomposeScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RecomposeScopeImpl.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u0000 f2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001fB\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020;J\u0010\u0010?\u001a\u00020@2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0014J\u0006\u0010A\u001a\u00020<J\u000e\u0010B\u001a\u00020<2\u0006\u0010\u0004\u001a\u00020\u0005J\b\u0010C\u001a\u00020<H\u0016J\"\u0010D\u001a\u00020<2\u0018\u00109\u001a\u0014\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020<0:H\u0016J\u000e\u0010T\u001a\u00020<2\u0006\u0010U\u001a\u00020\fJ\u0006\u0010V\u001a\u00020<J\u000e\u0010W\u001a\u00020\u001a2\u0006\u0010X\u001a\u00020\u0014J\u001c\u0010Y\u001a\u00020<2\n\u0010X\u001a\u0006\u0012\u0002\b\u00030J2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0014J\u0010\u0010[\u001a\u00020\u001a2\b\u0010\\\u001a\u0004\u0018\u00010\u0014J*\u0010]\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030J2\u0018\u0010^\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030J\u0012\u0006\u0012\u0004\u0018\u00010\u00140IH\u0002J\u0006\u0010_\u001a\u00020<J\u001c\u0010`\u001a\u0010\u0012\u0004\u0012\u00020b\u0012\u0004\u0012\u00020<\u0018\u00010a2\u0006\u0010U\u001a\u00020\fJ\u0011\u0010c\u001a\u00020\u001a2\u0006\u0010d\u001a\u00020\fH\u0082\bJ\u0019\u0010e\u001a\u00020<2\u0006\u0010d\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u001aH\u0082\bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0007R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00148VX\u0097\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001cR$\u0010 \u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b!\u0010\u001c\"\u0004\b\"\u0010#R$\u0010$\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b%\u0010\u001c\"\u0004\b&\u0010#R$\u0010'\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010\u001c\"\u0004\b)\u0010#R$\u0010*\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b+\u0010\u001c\"\u0004\b,\u0010#R$\u0010-\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b.\u0010\u001c\"\u0004\b/\u0010#R$\u00100\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b1\u0010\u001c\"\u0004\b2\u0010#R$\u00103\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b4\u0010\u001c\"\u0004\b5\u0010#R$\u00106\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u0010\u001c\"\u0004\b8\u0010#R\"\u00109\u001a\u0016\u0012\u0004\u0012\u00020;\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020<\u0018\u00010:X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010F\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010GX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010H\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u00030J\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010IX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010K\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8B@BX\u0082\u000e¢\u0006\f\u001a\u0004\bL\u0010\u001c\"\u0004\bM\u0010#R$\u0010N\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\bO\u0010\u001c\"\u0004\bP\u0010#R$\u0010Q\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a8@@BX\u0080\u000e¢\u0006\f\u001a\u0004\bR\u0010\u001c\"\u0004\bS\u0010#R\u0011\u0010Z\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\bZ\u0010\u001c¨\u0006g"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "Landroidx/compose/runtime/tooling/IdentifiableRecomposeScope;", "owner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "<init>", "(Landroidx/compose/runtime/RecomposeScopeOwner;)V", "getOwner$runtime", "()Landroidx/compose/runtime/RecomposeScopeOwner;", "setOwner$runtime", "flags", "", "anchor", "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "identity", "", "getIdentity$annotations", "()V", "getIdentity", "()Ljava/lang/Object;", "valid", "", "getValid", "()Z", "canRecompose", "getCanRecompose", "value", "used", "getUsed", "setUsed", "(Z)V", "reusing", "getReusing", "setReusing", "resetReusing", "getResetReusing", "setResetReusing", "paused", "getPaused", "setPaused", "resuming", "getResuming", "setResuming", "defaultsInScope", "getDefaultsInScope", "setDefaultsInScope", "defaultsInvalid", "getDefaultsInvalid", "setDefaultsInvalid", "requiresRecompose", "getRequiresRecompose", "setRequiresRecompose", "block", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "compose", "composer", "invalidateForResult", "Landroidx/compose/runtime/InvalidationResult;", "release", "adoptedBy", "invalidate", "updateScope", "currentToken", "trackedInstances", "Landroidx/collection/MutableObjectIntMap;", "trackedDependencies", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/DerivedState;", "rereading", "getRereading", "setRereading", "forcedRecompose", "getForcedRecompose", "setForcedRecompose", "skipped", "getSkipped$runtime", "setSkipped", "start", "token", "scopeSkipped", "recordRead", "instance", "recordDerivedStateValue", "isConditional", "isInvalidFor", "instances", "checkDerivedStateChanged", "dependencies", "rereadTrackedInstances", "end", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "getFlag", "flag", "setFlag", "Companion", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope, IdentifiableRecomposeScope {
    private Anchor anchor;
    private Function2<? super Composer, ? super Integer, Unit> block;
    private int currentToken;
    private int flags;
    private RecomposeScopeOwner owner;
    private MutableScatterMap<DerivedState<?>, Object> trackedDependencies;
    private MutableObjectIntMap<Object> trackedInstances;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public static /* synthetic */ void getIdentity$annotations() {
    }

    public RecomposeScopeImpl(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    /* JADX INFO: renamed from: getOwner$runtime, reason: from getter */
    public final RecomposeScopeOwner getOwner() {
        return this.owner;
    }

    public final void setOwner$runtime(RecomposeScopeOwner recomposeScopeOwner) {
        this.owner = recomposeScopeOwner;
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    @Override // androidx.compose.runtime.tooling.IdentifiableRecomposeScope
    public Object getIdentity() {
        return this.anchor;
    }

    public final boolean getValid() {
        if (this.owner == null) {
            return false;
        }
        Anchor anchor = this.anchor;
        return anchor != null ? anchor.getValid() : false;
    }

    public final boolean getCanRecompose() {
        return this.block != null;
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final void setUsed(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 1;
        } else {
            i = (~1) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getReusing() {
        return (this.flags & 128) != 0;
    }

    public final void setReusing(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 128;
        } else {
            i = (~128) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getResetReusing() {
        return (this.flags & 1024) != 0;
    }

    public final void setResetReusing(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 1024;
        } else {
            i = (~1024) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getPaused() {
        return (this.flags & 256) != 0;
    }

    public final void setPaused(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 256;
        } else {
            i = (~256) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getResuming() {
        return (this.flags & 512) != 0;
    }

    public final void setResuming(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 512;
        } else {
            i = (~512) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final void setDefaultsInScope(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 2;
        } else {
            i = (~2) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final void setDefaultsInvalid(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 4;
        } else {
            i = (~4) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final void setRequiresRecompose(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 8;
        } else {
            i = (~8) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final void compose(Composer composer) {
        Function2<? super Composer, ? super Integer, Unit> function2 = this.block;
        if (function2 == null) {
            throw new IllegalStateException("Invalid restart scope".toString());
        }
        function2.invoke(composer, 1);
    }

    public final InvalidationResult invalidateForResult(Object value) {
        InvalidationResult invalidationResultInvalidate;
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        return (recomposeScopeOwner == null || (invalidationResultInvalidate = recomposeScopeOwner.invalidate(this, value)) == null) ? InvalidationResult.IGNORED : invalidationResultInvalidate;
    }

    public final void release() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.recomposeScopeReleased(this);
        }
        this.owner = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
        this.block = null;
    }

    public final void adoptedBy(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.invalidate(this, null);
        }
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(Function2<? super Composer, ? super Integer, Unit> block) {
        this.block = block;
    }

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 32;
        } else {
            i = (~32) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getForcedRecompose() {
        return (this.flags & 64) != 0;
    }

    public final void setForcedRecompose(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 64;
        } else {
            i = (~64) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final boolean getSkipped$runtime() {
        return (this.flags & 16) != 0;
    }

    private final void setSkipped(boolean value) {
        int i;
        int existingFlags$iv = this.flags;
        if (value) {
            i = existingFlags$iv | 16;
        } else {
            i = (~16) & existingFlags$iv;
        }
        this.flags = i;
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    public final void scopeSkipped() {
        if (!getReusing()) {
            setSkipped(true);
        }
    }

    public final boolean recordRead(Object instance) {
        int i = 0;
        if (getRereading()) {
            return false;
        }
        MutableObjectIntMap<Object> mutableObjectIntMap = this.trackedInstances;
        int i2 = 1;
        if (mutableObjectIntMap == null) {
            mutableObjectIntMap = new MutableObjectIntMap<>(i, i2, null);
            this.trackedInstances = mutableObjectIntMap;
        }
        int token = mutableObjectIntMap.put(instance, this.currentToken, -1);
        return token == this.currentToken;
    }

    public final void recordDerivedStateValue(DerivedState<?> instance, Object value) {
        MutableScatterMap<DerivedState<?>, Object> mutableScatterMap = this.trackedDependencies;
        if (mutableScatterMap == null) {
            mutableScatterMap = new MutableScatterMap<>(0, 1, null);
            this.trackedDependencies = mutableScatterMap;
        }
        mutableScatterMap.set(instance, value);
    }

    public final boolean isConditional() {
        return this.trackedDependencies != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ac A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean isInvalidFor(java.lang.Object r28) {
        /*
            Method dump skipped, instruction units count: 218
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.isInvalidFor(java.lang.Object):boolean");
    }

    private final boolean checkDerivedStateChanged(DerivedState<?> derivedState, MutableScatterMap<DerivedState<?>, Object> mutableScatterMap) {
        Intrinsics.checkNotNull(derivedState, "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>");
        SnapshotMutationPolicy<?> policy = derivedState.getPolicy();
        if (policy == null) {
            policy = SnapshotStateKt.structuralEqualityPolicy();
        }
        return !policy.equivalent(derivedState.getCurrentRecord().getCurrentValue(), mutableScatterMap.get(derivedState));
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0081  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void rereadTrackedInstances() throws java.lang.Throwable {
        /*
            r25 = this;
            r1 = r25
            androidx.compose.runtime.RecomposeScopeOwner r0 = r1.owner
            if (r0 == 0) goto La4
            r2 = r0
            r3 = 0
            androidx.collection.MutableObjectIntMap<java.lang.Object> r0 = r1.trackedInstances
            if (r0 == 0) goto La2
            r4 = r0
            r5 = 0
            r0 = 1
            r1.setRereading(r0)
            r7 = r4
            androidx.collection.ObjectIntMap r7 = (androidx.collection.ObjectIntMap) r7     // Catch: java.lang.Throwable -> L9c
            r8 = 0
            java.lang.Object[] r9 = r7.keys     // Catch: java.lang.Throwable -> L9c
            int[] r10 = r7.values     // Catch: java.lang.Throwable -> L9c
            r11 = r7
            r12 = 0
            long[] r13 = r11.metadata     // Catch: java.lang.Throwable -> L9c
            int r14 = r13.length     // Catch: java.lang.Throwable -> L9c
            int r14 = r14 + (-2)
            r15 = 0
            if (r15 > r14) goto L8f
        L25:
            r16 = r13[r15]     // Catch: java.lang.Throwable -> L8b
            r18 = r16
            r20 = 0
            r0 = r18
            r19 = r7
            long r6 = ~r0     // Catch: java.lang.Throwable -> L8b
            r21 = 7
            long r6 = r6 << r21
            long r6 = r6 & r0
            r21 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r0 = r6 & r21
            int r0 = (r0 > r21 ? 1 : (r0 == r21 ? 0 : -1))
            if (r0 == 0) goto L81
            int r0 = r15 - r14
            int r0 = ~r0     // Catch: java.lang.Throwable -> L8b
            int r0 = r0 >>> 31
            r1 = 8
            int r0 = 8 - r0
            r6 = 0
        L4a:
            if (r6 >= r0) goto L7d
            r21 = 255(0xff, double:1.26E-321)
            long r21 = r16 & r21
            r7 = 0
            r23 = 128(0x80, double:6.3E-322)
            int r20 = (r21 > r23 ? 1 : (r21 == r23 ? 0 : -1))
            if (r20 >= 0) goto L59
            r7 = 1
            goto L5a
        L59:
            r7 = 0
        L5a:
            if (r7 == 0) goto L74
            int r7 = r15 << 3
            int r7 = r7 + r6
            r20 = r7
            r21 = 0
            r22 = r9[r20]     // Catch: java.lang.Throwable -> L8b
            r23 = r10[r20]     // Catch: java.lang.Throwable -> L8b
            r23 = r22
            r22 = 0
            r24 = r1
            r1 = r23
            r2.recordReadOf(r1)     // Catch: java.lang.Throwable -> L8b
            goto L76
        L74:
            r24 = r1
        L76:
            long r16 = r16 >> r24
            int r6 = r6 + 1
            r1 = r24
            goto L4a
        L7d:
            r24 = r1
            if (r0 != r1) goto L92
        L81:
            if (r15 == r14) goto L91
            int r15 = r15 + 1
            r0 = 1
            r1 = r25
            r7 = r19
            goto L25
        L8b:
            r0 = move-exception
            r1 = r25
            goto L9d
        L8f:
            r19 = r7
        L91:
        L92:
            r6 = 0
            r1 = r25
            r1.setRereading(r6)
            goto La2
        L9c:
            r0 = move-exception
        L9d:
            r6 = 0
            r1.setRereading(r6)
            throw r0
        La2:
        La4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.rereadTrackedInstances():void");
    }

    public final Function1<Composition, Unit> end(final int token) {
        int i;
        final MutableObjectIntMap<Object> mutableObjectIntMap = this.trackedInstances;
        if (mutableObjectIntMap == null) {
            return null;
        }
        int i2 = 0;
        if (!getSkipped$runtime()) {
            MutableObjectIntMap<Object> this_$iv = mutableObjectIntMap;
            int $i$f$any = 0;
            Object[] k$iv$iv = this_$iv.keys;
            int[] v$iv$iv = this_$iv.values;
            long[] m$iv$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            boolean z = false;
            if (0 <= lastIndex$iv$iv$iv) {
                loop0: while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                    int i3 = i2;
                    ObjectIntMap this_$iv2 = this_$iv;
                    int $i$f$any2 = $i$f$any;
                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                        if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                            break;
                        }
                        i$iv$iv$iv++;
                        this_$iv = this_$iv2;
                        $i$f$any = $i$f$any2;
                        i2 = i3;
                    } else {
                        int i4 = 8;
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                        int j$iv$iv$iv = 0;
                        while (true) {
                            if (j$iv$iv$iv >= bitCount$iv$iv$iv) {
                                if (bitCount$iv$iv$iv != i4) {
                                    break;
                                }
                            } else {
                                long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                                if ($i$f$isFull != 0) {
                                    int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                    Object obj = k$iv$iv[index$iv$iv$iv];
                                    int value$iv = v$iv$iv[index$iv$iv$iv];
                                    i = i4;
                                    int instanceToken = value$iv != token ? 1 : 0;
                                    if (instanceToken != 0) {
                                        z = true;
                                        break loop0;
                                    }
                                } else {
                                    i = i4;
                                }
                                slot$iv$iv$iv >>= i;
                                j$iv$iv$iv++;
                                i4 = i;
                            }
                        }
                    }
                }
            }
            if (z) {
                return new Function1() { // from class: androidx.compose.runtime.RecomposeScopeImpl$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return RecomposeScopeImpl.end$lambda$0$1(this.f$0, token, mutableObjectIntMap, (Composition) obj2);
                    }
                };
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Unit end$lambda$0$1(androidx.compose.runtime.RecomposeScopeImpl r22, int r23, androidx.collection.MutableObjectIntMap r24, androidx.compose.runtime.Composition r25) {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.end$lambda$0$1(androidx.compose.runtime.RecomposeScopeImpl, int, androidx.collection.MutableObjectIntMap, androidx.compose.runtime.Composition):kotlin.Unit");
    }

    private final boolean getFlag(int flag) {
        return (this.flags & flag) != 0;
    }

    private final void setFlag(int flag, boolean value) {
        int i;
        int existingFlags = this.flags;
        if (value) {
            i = existingFlags | flag;
        } else {
            i = (~flag) & existingFlags;
        }
        this.flags = i;
    }

    /* JADX INFO: compiled from: RecomposeScopeImpl.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0002\b\rJ#\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u00102\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0000¢\u0006\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl$Companion;", "", "<init>", "()V", "adoptAnchoredScopes", "", "slots", "Landroidx/compose/runtime/composer/gapbuffer/SlotWriter;", "anchors", "", "Landroidx/compose/runtime/composer/gapbuffer/GapAnchor;", "newOwner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "adoptAnchoredScopes$runtime", "hasAnchoredRecomposeScopes", "", "Landroidx/compose/runtime/composer/gapbuffer/SlotTable;", "hasAnchoredRecomposeScopes$runtime", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void adoptAnchoredScopes$runtime(SlotWriter slots, List<GapAnchor> anchors, RecomposeScopeOwner newOwner) {
            if (anchors.isEmpty()) {
                return;
            }
            int size = anchors.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = anchors.get(index$iv);
                GapAnchor anchor = (GapAnchor) item$iv;
                Object objSlot = slots.slot(anchor, 0);
                RecomposeScopeImpl recomposeScope = objSlot instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) objSlot : null;
                if (recomposeScope != null) {
                    recomposeScope.adoptedBy(newOwner);
                }
            }
        }

        public final boolean hasAnchoredRecomposeScopes$runtime(SlotTable slots, List<GapAnchor> anchors) {
            boolean z;
            if (anchors.isEmpty()) {
                return false;
            }
            int index$iv$iv = 0;
            int size = anchors.size();
            while (true) {
                if (index$iv$iv < size) {
                    Object item$iv$iv = anchors.get(index$iv$iv);
                    GapAnchor it = (GapAnchor) item$iv$iv;
                    if (slots.ownsAnchor(it) && (slots.slot$runtime(slots.anchorIndex(it), 0) instanceof RecomposeScopeImpl)) {
                        z = true;
                        break;
                    }
                    index$iv$iv++;
                } else {
                    z = false;
                    break;
                }
            }
            return z;
        }
    }
}
