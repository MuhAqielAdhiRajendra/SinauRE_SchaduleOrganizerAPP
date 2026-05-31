package androidx.compose.runtime.internal;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.FunctionN;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ComposableLambdaN.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\rJ\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0003H\u0002J(\u0010\u001b\u001a\u0004\u0018\u00010\r2\u0016\u0010\u001c\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\r0\u001d\"\u0004\u0018\u00010\rH\u0096\u0002¢\u0006\u0002\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Landroidx/compose/runtime/internal/ComposableLambdaNImpl;", "Landroidx/compose/runtime/internal/ComposableLambdaN;", "key", "", "tracked", "", "arity", "<init>", "(IZI)V", "getKey", "()I", "getArity", "_block", "", "scope", "Landroidx/compose/runtime/RecomposeScope;", "scopes", "", "trackWrite", "", "trackRead", "composer", "Landroidx/compose/runtime/Composer;", "update", "block", "realParamCount", "params", "invoke", "args", "", "([Ljava/lang/Object;)Ljava/lang/Object;", "runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ComposableLambdaNImpl implements ComposableLambdaN {
    public static final int $stable = 0;
    private Object _block;
    private final int arity;
    private final int key;
    private RecomposeScope scope;
    private List<RecomposeScope> scopes;
    private final boolean tracked;

    public ComposableLambdaNImpl(int key, boolean tracked, int arity) {
        this.key = key;
        this.tracked = tracked;
        this.arity = arity;
    }

    public final int getKey() {
        return this.key;
    }

    @Override // kotlin.jvm.functions.FunctionN, kotlin.jvm.internal.FunctionBase
    public int getArity() {
        return this.arity;
    }

    private final void trackWrite() {
        if (this.tracked) {
            RecomposeScope scope = this.scope;
            if (scope != null) {
                scope.invalidate();
                this.scope = null;
            }
            List<RecomposeScope> list = this.scopes;
            if (list != null) {
                int size = list.size();
                for (int index = 0; index < size; index++) {
                    RecomposeScope item = list.get(index);
                    item.invalidate();
                }
                list.clear();
            }
        }
    }

    private final void trackRead(Composer composer) {
        RecomposeScope scope;
        if (this.tracked && (scope = composer.getRecomposeScope()) != null) {
            composer.recordUsed(scope);
            RecomposeScope lastScope = this.scope;
            if (ComposableLambdaKt.replacableWith(lastScope, scope)) {
                this.scope = scope;
                return;
            }
            List<RecomposeScope> list = this.scopes;
            if (list == null) {
                List newScopes = new ArrayList();
                this.scopes = newScopes;
                newScopes.add(scope);
                return;
            }
            int size = list.size();
            for (int index = 0; index < size; index++) {
                RecomposeScope scopeAtIndex = list.get(index);
                if (ComposableLambdaKt.replacableWith(scopeAtIndex, scope)) {
                    list.set(index, scope);
                    return;
                }
            }
            list.add(scope);
        }
    }

    public final void update(Object block) {
        if (!Intrinsics.areEqual(block, this._block)) {
            boolean oldBlockNull = this._block == null;
            Intrinsics.checkNotNull(block, "null cannot be cast to non-null type kotlin.jvm.functions.FunctionN<*>");
            this._block = (FunctionN) block;
            if (!oldBlockNull) {
                trackWrite();
            }
        }
    }

    private final int realParamCount(int params) {
        int realParams = params - 1;
        int realParams2 = realParams - 1;
        for (int changedParams = 1; changedParams * 10 < realParams2; changedParams++) {
            realParams2--;
        }
        return realParams2;
    }

    @Override // kotlin.jvm.functions.FunctionN
    public Object invoke(final Object... args) {
        final int realParams = realParamCount(args.length);
        Object obj = args[realParams];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.Composer");
        Collection $this$toTypedArray$iv = ArraysKt.slice(args, RangesKt.until(0, args.length - 1));
        Object[] allArgsButLast = $this$toTypedArray$iv.toArray(new Object[0]);
        Object obj2 = args[args.length - 1];
        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
        int lastChanged = ((Integer) obj2).intValue();
        Composer c = ((Composer) obj).startRestartGroup(this.key);
        trackRead(c);
        int dirty = (c.changed(this) ? ComposableLambdaKt.differentBits(realParams) : ComposableLambdaKt.sameBits(realParams)) | lastChanged;
        Object obj3 = this._block;
        Intrinsics.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.jvm.functions.FunctionN<*>");
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        spreadBuilder.addSpread(allArgsButLast);
        spreadBuilder.add(Integer.valueOf(dirty));
        Object result = ((FunctionN) obj3).invoke(spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = c.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaNImpl$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return ComposableLambdaNImpl.invoke$lambda$0(args, realParams, this, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
        return result;
    }

    static final Unit invoke$lambda$0(Object[] $args, int $realParams, ComposableLambdaNImpl this$0, Composer nc, int i) {
        Collection $this$toTypedArray$iv = ArraysKt.slice($args, RangesKt.until(0, $realParams));
        Object[] params = $this$toTypedArray$iv.toArray(new Object[0]);
        Object obj = $args[$realParams + 1];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Int");
        int changed = RecomposeScopeImplKt.updateChangedFlags(((Integer) obj).intValue());
        int length = ($args.length - $realParams) - 2;
        Object[] changedN = new Object[length];
        for (int i2 = 0; i2 < length; i2++) {
            Object obj2 = $args[$realParams + 2 + i2];
            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Int");
            changedN[i2] = Integer.valueOf(RecomposeScopeImplKt.updateChangedFlags(((Integer) obj2).intValue()));
        }
        SpreadBuilder spreadBuilder = new SpreadBuilder(4);
        spreadBuilder.addSpread(params);
        spreadBuilder.add(nc);
        spreadBuilder.add(Integer.valueOf(changed | 1));
        spreadBuilder.addSpread(changedN);
        this$0.invoke(spreadBuilder.toArray(new Object[spreadBuilder.size()]));
        return Unit.INSTANCE;
    }
}
