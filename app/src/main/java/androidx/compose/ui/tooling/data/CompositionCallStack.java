package androidx.compose.ui.tooling.data;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.unit.IntRect;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SlotTree.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003Bo\u00122\u0010\u0004\u001a.\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t\u0012\u001c\b\u0002\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0018\u00010\t¢\u0006\u0004\b\u000e\u0010\u000fJ$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00132\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\rJ\u0010\u0010.\u001a\u00020/2\u0006\u0010\u0016\u001a\u00020\u0006H\u0002J\b\u00100\u001a\u00020\u0006H\u0002J\u0012\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u00105\u001a\u00020\u0013H\u0002J\u0012\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u00020\nH\u0002J\u0010\u00109\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0006H\u0002R:\u0010\u0004\u001a.\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u0007\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001eR\u001e\u0010 \u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u0015@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0016\u0010#\u001a\u0004\u0018\u00010$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&R\u001a\u0010'\u001a\b\u0012\u0004\u0012\u00020(0\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0014\u0010+\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0014\u00101\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b2\u00103¨\u0006:"}, d2 = {"Landroidx/compose/ui/tooling/data/CompositionCallStack;", "T", "R", "Landroidx/compose/ui/tooling/data/SourceContext;", "createNode", "Lkotlin/Function4;", "Landroidx/compose/runtime/tooling/CompositionGroup;", "", "contexts", "", "", "", "childrenToAdd", "", "<init>", "(Lkotlin/jvm/functions/Function4;Ljava/util/Map;Ljava/util/Map;)V", "stack", "Lkotlin/collections/ArrayDeque;", "currentCallIndex", "", "convert", "Landroidx/compose/ui/unit/IntRect;", "group", "callIndex", "out", HintConstants.AUTOFILL_HINT_NAME, "getName", "()Ljava/lang/String;", "isInline", "", "()Z", "value", "bounds", "getBounds", "()Landroidx/compose/ui/unit/IntRect;", "location", "Landroidx/compose/ui/tooling/data/SourceLocation;", "getLocation", "()Landroidx/compose/ui/tooling/data/SourceLocation;", "parameters", "Landroidx/compose/ui/tooling/data/ParameterInformation;", "getParameters", "()Ljava/util/List;", "depth", "getDepth", "()I", "push", "", "pop", "current", "getCurrent", "()Landroidx/compose/runtime/tooling/CompositionGroup;", "parentGroup", "parentDepth", "contextOf", "Landroidx/compose/ui/tooling/data/SourceInformationContext;", "information", "isCall", "ui-tooling-data"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class CompositionCallStack<T, R> implements SourceContext {
    private IntRect bounds;
    private final Map<CompositionGroup, List<R>> childrenToAdd;
    private final Map<String, Object> contexts;
    private final Function4<CompositionGroup, SourceContext, List<? extends T>, List<? extends R>, T> createNode;
    private int currentCallIndex;
    private final ArrayDeque<CompositionGroup> stack;

    /* JADX WARN: Multi-variable type inference failed */
    public CompositionCallStack(Function4<? super CompositionGroup, ? super SourceContext, ? super List<? extends T>, ? super List<? extends R>, ? extends T> function4, Map<String, Object> map, Map<CompositionGroup, List<R>> map2) {
        this.createNode = function4;
        this.contexts = map;
        this.childrenToAdd = map2;
        this.stack = new ArrayDeque<>();
        this.bounds = SlotTreeKt.getEmptyBox();
    }

    public /* synthetic */ CompositionCallStack(Function4 function4, Map map, Map map2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function4, map, (i & 4) != 0 ? null : map2);
    }

    public final IntRect convert(CompositionGroup group, int callIndex, List<T> out) {
        IntRect intRectBoundsOfLayoutNode;
        List children = new ArrayList();
        IntRect emptyBox = SlotTreeKt.getEmptyBox();
        push(group);
        int childCallIndex = 0;
        for (Object element$iv : group.getCompositionGroups()) {
            CompositionGroup child = (CompositionGroup) element$iv;
            emptyBox = SlotTreeKt.union(emptyBox, convert(child, childCallIndex, children));
            if (isCall(child)) {
                childCallIndex++;
            }
        }
        Object node = group.getNode();
        List<R> listRemove = null;
        LayoutInfo it = node instanceof LayoutInfo ? (LayoutInfo) node : null;
        if (it == null || (intRectBoundsOfLayoutNode = SlotTreeKt.boundsOfLayoutNode(it)) == null) {
            intRectBoundsOfLayoutNode = emptyBox;
        }
        this.currentCallIndex = callIndex;
        this.bounds = intRectBoundsOfLayoutNode;
        Map<CompositionGroup, List<R>> map = this.childrenToAdd;
        if (map != null) {
            if (map.isEmpty()) {
                map = null;
            }
            if (map != null) {
                listRemove = map.remove(group);
            }
        }
        T tInvoke = this.createNode.invoke(group, this, children, listRemove == null ? CollectionsKt.emptyList() : listRemove);
        if (tInvoke != null) {
            out.add(tInvoke);
        }
        pop();
        return intRectBoundsOfLayoutNode;
    }

    @Override // androidx.compose.ui.tooling.data.SourceContext
    public String getName() {
        int startIndex;
        String info = getCurrent().getSourceInfo();
        if (info == null) {
            return null;
        }
        if (StringsKt.startsWith$default(info, "CC(", false, 2, (Object) null)) {
            startIndex = 3;
        } else {
            if (!StringsKt.startsWith$default(info, "C(", false, 2, (Object) null)) {
                return null;
            }
            startIndex = 2;
        }
        int endIndex = StringsKt.indexOf$default((CharSequence) info, ')', 0, false, 6, (Object) null);
        if (endIndex <= 2) {
            return null;
        }
        String strSubstring = info.substring(startIndex, endIndex);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return strSubstring;
    }

    @Override // androidx.compose.ui.tooling.data.SourceContext
    public boolean isInline() {
        String sourceInfo = getCurrent().getSourceInfo();
        return sourceInfo != null && StringsKt.startsWith$default(sourceInfo, "CC", false, 2, (Object) null);
    }

    @Override // androidx.compose.ui.tooling.data.SourceContext
    public IntRect getBounds() {
        return this.bounds;
    }

    @Override // androidx.compose.ui.tooling.data.SourceContext
    public SourceLocation getLocation() {
        String it;
        SourceInformationContext context;
        String it2;
        CompositionGroup compositionGroupParentGroup = parentGroup(1);
        if (compositionGroupParentGroup == null || (it = compositionGroupParentGroup.getSourceInfo()) == null || (context = contextOf(it)) == null) {
            return null;
        }
        SourceInformationContext parentContext = context;
        int index = 2;
        while (index < this.stack.size()) {
            if ((parentContext != null ? parentContext.getSourceFile() : null) != null) {
                break;
            }
            int index2 = index + 1;
            CompositionGroup compositionGroupParentGroup2 = parentGroup(index);
            parentContext = (compositionGroupParentGroup2 == null || (it2 = compositionGroupParentGroup2.getSourceInfo()) == null) ? null : contextOf(it2);
            index = index2;
        }
        return context.sourceLocation(this.currentCallIndex, parentContext);
    }

    @Override // androidx.compose.ui.tooling.data.SourceContext
    public List<ParameterInformation> getParameters() {
        SourceInformationContext context;
        CompositionGroup group = getCurrent();
        String it = group.getSourceInfo();
        if (it == null || (context = contextOf(it)) == null) {
            return CollectionsKt.emptyList();
        }
        List data = new ArrayList();
        CollectionsKt.addAll(data, group.getData());
        return SlotTreeKt.extractParameterInfo(data, context);
    }

    @Override // androidx.compose.ui.tooling.data.SourceContext
    public int getDepth() {
        return this.stack.size();
    }

    private final void push(CompositionGroup group) {
        this.stack.addLast(group);
    }

    private final CompositionGroup pop() {
        return this.stack.removeLast();
    }

    private final CompositionGroup getCurrent() {
        return this.stack.last();
    }

    private final CompositionGroup parentGroup(int parentDepth) {
        if (this.stack.size() > parentDepth) {
            return this.stack.get((this.stack.size() - parentDepth) - 1);
        }
        return null;
    }

    private final SourceInformationContext contextOf(String information) {
        Object answer$iv;
        Map<String, Object> map = this.contexts;
        Object value$iv = map.get(information);
        if (value$iv == null) {
            answer$iv = SlotTreeKt.sourceInformationContextOf$default(information, null, 2, null);
            map.put(information, answer$iv);
        } else {
            answer$iv = value$iv;
        }
        if (answer$iv instanceof SourceInformationContext) {
            return (SourceInformationContext) answer$iv;
        }
        return null;
    }

    private final boolean isCall(CompositionGroup group) {
        String sourceInfo = group.getSourceInfo();
        if (sourceInfo != null) {
            return StringsKt.startsWith$default(sourceInfo, "C", false, 2, (Object) null);
        }
        return false;
    }
}
