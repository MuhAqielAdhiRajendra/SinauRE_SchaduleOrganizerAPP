package androidx.compose.ui.tooling.data;

import androidx.autofill.HintConstants;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.CompositionGroup;
import androidx.compose.runtime.tooling.LocationSourceInformation;
import androidx.compose.runtime.tooling.ParameterSourceInformation;
import androidx.compose.runtime.tooling.SourceInformation;
import androidx.compose.runtime.tooling.SourceInformationKt;
import androidx.compose.ui.geometry.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LayoutInfo;
import androidx.compose.ui.layout.ModifierInfo;
import androidx.compose.ui.unit.IntRect;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: SlotTree.jvmAndAndroid.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0002\u001a\u0016\u0010\t\u001a\u00020\n*\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0003\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001aK\u0010\u0010\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u0011*\u00020\u00122&\u0010\u0013\u001a\"\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u00142\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001ay\u0010\u001a\u001a\u0004\u0018\u0001H\u0011\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u001b*\u00020\u001222\u0010\u001c\u001a.\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u001d2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001a\b\u0002\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001b0 0\u001fH\u0000¢\u0006\u0002\u0010!\u001a\u001e\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0016*\u00020\u000b2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007\u001a\f\u0010$\u001a\u00020\n*\u00020\u0012H\u0007\u001a\u0014\u0010%\u001a\u00020\u0001*\u00020\u00012\u0006\u0010&\u001a\u00020\u0001H\u0000\u001a\u0014\u0010'\u001a\u0004\u0018\u00010\u00072\b\u0010(\u001a\u0004\u0018\u00010)H\u0003\u001a(\u00103\u001a\b\u0012\u0004\u0012\u00020#0\u00162\u000e\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0\u00162\b\u00105\u001a\u0004\u0018\u00010\u0005H\u0003\u001a2\u00106\u001a\b\u0012\u0004\u0012\u00020#0\u00162\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00162\u0006\u00109\u001a\u00020)2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u0016H\u0002\u001a2\u0010<\u001a\b\u0012\u0004\u0012\u00020#0\u00162\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\u00162\u0006\u00109\u001a\u00020)2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u0016H\u0002\u001a:\u0010=\u001a\u00020#2\u0006\u0010>\u001a\u0002082\u0006\u00109\u001a\u00020)2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020@2\u0006\u0010B\u001a\u00020@2\b\u0010:\u001a\u0004\u0018\u00010;H\u0003\u001a)\u0010C\u001a\b\u0012\u0004\u0012\u0002080\u00162\f\u00107\u001a\b\u0012\u0004\u0012\u0002080D2\u0006\u0010E\u001a\u00020FH\u0002¢\u0006\u0002\u0010G\u001a\u001a\u0010Q\u001a\u0004\u0018\u000108*\u0006\u0012\u0002\b\u00030R2\u0006\u0010S\u001a\u00020\u0007H\u0002\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010,\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010-\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010.\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010/\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00100\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00101\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u00102\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010H\u001a\u00020@X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010I\u001a\u00020@X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010J\u001a\u00020@X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010K\u001a\u00020@X\u0082T¢\u0006\u0002\n\u0000\" \u0010L\u001a\u0004\u0018\u00010\u0007*\u00020\n8FX\u0087\u0004¢\u0006\f\u0012\u0004\bM\u0010N\u001a\u0004\bO\u0010P¨\u0006T"}, d2 = {"emptyBox", "Landroidx/compose/ui/unit/IntRect;", "getEmptyBox", "()Landroidx/compose/ui/unit/IntRect;", "sourceInformationContextOf", "Landroidx/compose/ui/tooling/data/SourceInformationContext;", "information", "", "parent", "getGroup", "Landroidx/compose/ui/tooling/data/Group;", "Landroidx/compose/runtime/tooling/CompositionGroup;", "parentContext", "boundsOfLayoutNode", "node", "Landroidx/compose/ui/layout/LayoutInfo;", "mapTree", "T", "Landroidx/compose/runtime/tooling/CompositionData;", "factory", "Lkotlin/Function3;", "Landroidx/compose/ui/tooling/data/SourceContext;", "", "cache", "Landroidx/compose/ui/tooling/data/ContextCache;", "(Landroidx/compose/runtime/tooling/CompositionData;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/tooling/data/ContextCache;)Ljava/lang/Object;", "mapTreeWithStitching", "R", "createNode", "Lkotlin/Function4;", "childrenToAdd", "", "", "(Landroidx/compose/runtime/tooling/CompositionData;Lkotlin/jvm/functions/Function4;Landroidx/compose/ui/tooling/data/ContextCache;Ljava/util/Map;)Ljava/lang/Object;", "findParameters", "Landroidx/compose/ui/tooling/data/ParameterInformation;", "asTree", "union", "other", "keyPosition", "key", "", "indyLambdaRegex", "Lkotlin/text/Regex;", "legacyLambdaRegex", "parameterPrefix", "internalFieldPrefix", "defaultFieldName", "changedFieldName", "jacocoDataField", "recomposeScopeNameSuffix", "extractParameterInfo", "data", "context", "extractFromIndyLambdaFields", "fields", "Ljava/lang/reflect/Field;", "block", "metadata", "Landroidx/compose/runtime/tooling/ParameterSourceInformation;", "extractFromLegacyFields", "buildParameterInfo", "field", "index", "", "defaults", "changed", "filterParameterFields", "", "isIndyLambda", "", "([Ljava/lang/reflect/Field;Z)Ljava/util/List;", "BITS_PER_SLOT", "SLOT_MASK", "STATIC_BITS", "STABLE_BITS", "position", "getPosition$annotations", "(Landroidx/compose/ui/tooling/data/Group;)V", "getPosition", "(Landroidx/compose/ui/tooling/data/Group;)Ljava/lang/String;", "accessibleField", "Ljava/lang/Class;", HintConstants.AUTOFILL_HINT_NAME, "ui-tooling-data"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class SlotTreeKt {
    private static final int BITS_PER_SLOT = 3;
    private static final int SLOT_MASK = 7;
    private static final int STABLE_BITS = 4;
    private static final int STATIC_BITS = 3;
    private static final String changedFieldName = "$$changed";
    private static final String defaultFieldName = "$$default";
    private static final String internalFieldPrefix = "$$";
    private static final String jacocoDataField = "$jacoco";
    private static final String parameterPrefix = "$";
    private static final String recomposeScopeNameSuffix = ".RecomposeScopeImpl";
    private static final IntRect emptyBox = new IntRect(0, 0, 0, 0);
    private static final Regex indyLambdaRegex = new Regex("^f\\$\\d+$");
    private static final Regex legacyLambdaRegex = new Regex("^\\$([^$]+)$|\\$\\$.*?\\$-([^$]+)\\$\\d+$");

    public static /* synthetic */ void getPosition$annotations(Group group) {
    }

    public static final IntRect getEmptyBox() {
        return emptyBox;
    }

    static /* synthetic */ SourceInformationContext sourceInformationContextOf$default(String str, SourceInformationContext sourceInformationContext, int i, Object obj) {
        if ((i & 2) != 0) {
            sourceInformationContext = null;
        }
        return sourceInformationContextOf(str, sourceInformationContext);
    }

    private static final SourceInformationContext sourceInformationContextOf(String information, SourceInformationContext parent) {
        String str;
        int $i$f$indexOfFirst;
        SourceInformation parsedInfo = SourceInformationKt.parseSourceInformation(information);
        Integer numValueOf = null;
        if (parsedInfo == null) {
            return null;
        }
        String functionName = parsedInfo.getFunctionName();
        String sourceFile = parsedInfo.getSourceFile();
        if (sourceFile != null) {
            str = sourceFile;
        } else if (parent != null) {
            sourceFile = parent.getSourceFile();
            str = sourceFile;
        } else {
            str = null;
        }
        if (parsedInfo.getSourceFile() != null) {
            String packageHash = parsedInfo.getPackageHash();
            if (packageHash != null) {
                numValueOf = StringsKt.toIntOrNull(packageHash, 36);
            }
        } else if (parent != null) {
            numValueOf = Integer.valueOf(parent.getPackageHash());
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : -1;
        List<LocationSourceInformation> locations = parsedInfo.getLocations();
        int index$iv = 0;
        Iterator<LocationSourceInformation> it = parsedInfo.getLocations().iterator();
        while (true) {
            if (it.hasNext()) {
                Object item$iv = it.next();
                LocationSourceInformation it2 = (LocationSourceInformation) item$iv;
                if (it2.getIsRepeatable()) {
                    $i$f$indexOfFirst = index$iv;
                    break;
                }
                index$iv++;
            } else {
                $i$f$indexOfFirst = -1;
                break;
            }
        }
        return new SourceInformationContext(functionName, str, iIntValue, locations, $i$f$indexOfFirst, parsedInfo.getParameters(), parsedInfo.getIsCall(), parsedInfo.getIsInline());
    }

    private static final Group getGroup(CompositionGroup $this$getGroup, SourceInformationContext parentContext) {
        IntRect box;
        Object key = $this$getGroup.getKey();
        String it = $this$getGroup.getSourceInfo();
        SourceInformationContext context = it != null ? sourceInformationContextOf(it, parentContext) : null;
        Object node = $this$getGroup.getNode();
        List data = new ArrayList();
        List children = new ArrayList();
        CollectionsKt.addAll(data, $this$getGroup.getData());
        for (CompositionGroup child : $this$getGroup.getCompositionGroups()) {
            children.add(getGroup(child, context));
        }
        List<ModifierInfo> modifierInfo = node instanceof LayoutInfo ? ((LayoutInfo) node).getModifierInfo() : CollectionsKt.emptyList();
        if (node instanceof LayoutInfo) {
            box = boundsOfLayoutNode((LayoutInfo) node);
        } else if (children.isEmpty()) {
            box = emptyBox;
        } else {
            List $this$map$iv = children;
            Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
            for (Object item$iv$iv : $this$map$iv) {
                Group g = (Group) item$iv$iv;
                destination$iv$iv.add(g.getBox());
            }
            Iterable $this$reduce$iv = (List) destination$iv$iv;
            Iterator iterator$iv = $this$reduce$iv.iterator();
            if (!iterator$iv.hasNext()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object accumulator$iv = iterator$iv.next();
            while (iterator$iv.hasNext()) {
                IntRect box2 = (IntRect) iterator$iv.next();
                IntRect acc = (IntRect) accumulator$iv;
                accumulator$iv = union(box2, acc);
            }
            box = (IntRect) accumulator$iv;
        }
        SourceLocation location = (!(context != null && context.getIsCall()) || parentContext == null) ? null : parentContext.nextSourceLocation();
        if (node != null) {
            return new NodeGroup(key, node, box, data, modifierInfo, children);
        }
        Object node2 = null;
        String name = context != null ? context.getName() : null;
        String name2 = context != null ? context.getName() : null;
        if (!(name2 == null || name2.length() == 0) && (box.getBottom() - box.getTop() > 0 || box.getRight() - box.getLeft() > 0)) {
            node2 = $this$getGroup.getIdentity();
        }
        return new CallGroup(key, name, box, location, node2, extractParameterInfo(data, context), data, children, context != null && context.getIsInline());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect boundsOfLayoutNode(LayoutInfo node) {
        LayoutCoordinates coordinates = node.getCoordinates();
        if (!node.isAttached() || !coordinates.isAttached()) {
            return new IntRect(0, 0, node.getWidth(), node.getHeight());
        }
        long position = LayoutCoordinatesKt.positionInWindow(coordinates);
        long v$iv = 9223372034707292159L & position;
        if (!(((InlineClassHelperKt.DualLoadedSignificand + v$iv) & (-9223372034707292160L)) == 0)) {
            return new IntRect(0, 0, node.getWidth(), node.getHeight());
        }
        long size = coordinates.mo6791getSizeYbymL2g();
        int bits$iv$iv$iv = (int) (position >> 32);
        int left = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv));
        int bits$iv$iv$iv2 = (int) (position & 4294967295L);
        int top = MathKt.roundToInt(Float.intBitsToFloat(bits$iv$iv$iv2));
        int right = ((int) (size >> 32)) + left;
        int bottom = ((int) (size & 4294967295L)) + top;
        return new IntRect(left, top, right, bottom);
    }

    public static /* synthetic */ Object mapTree$default(CompositionData compositionData, Function3 function3, ContextCache contextCache, int i, Object obj) {
        if ((i & 2) != 0) {
            contextCache = new ContextCache();
        }
        return mapTree(compositionData, function3, contextCache);
    }

    public static final <T> T mapTree(CompositionData compositionData, final Function3<? super CompositionGroup, ? super SourceContext, ? super List<? extends T>, ? extends T> function3, ContextCache contextCache) {
        CompositionGroup compositionGroup = (CompositionGroup) CollectionsKt.firstOrNull(compositionData.getCompositionGroups());
        if (compositionGroup == null) {
            return null;
        }
        CompositionCallStack compositionCallStack = new CompositionCallStack(new Function4() { // from class: androidx.compose.ui.tooling.data.SlotTreeKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return function3.invoke((CompositionGroup) obj, (SourceContext) obj2, (List) obj3);
            }
        }, contextCache.getContexts$ui_tooling_data(), null, 4, null);
        ArrayList arrayList = new ArrayList();
        compositionCallStack.convert(compositionGroup, 0, arrayList);
        return (T) CollectionsKt.firstOrNull((List) arrayList);
    }

    public static /* synthetic */ Object mapTreeWithStitching$default(CompositionData compositionData, Function4 function4, ContextCache contextCache, Map map, int i, Object obj) {
        if ((i & 2) != 0) {
            contextCache = new ContextCache();
        }
        if ((i & 4) != 0) {
            map = new LinkedHashMap();
        }
        return mapTreeWithStitching(compositionData, function4, contextCache, map);
    }

    public static final <T, R> T mapTreeWithStitching(CompositionData compositionData, Function4<? super CompositionGroup, ? super SourceContext, ? super List<? extends T>, ? super List<? extends R>, ? extends T> function4, ContextCache contextCache, Map<CompositionGroup, List<R>> map) {
        CompositionGroup compositionGroup = (CompositionGroup) CollectionsKt.firstOrNull(compositionData.getCompositionGroups());
        if (compositionGroup == null) {
            return null;
        }
        CompositionCallStack compositionCallStack = new CompositionCallStack(function4, contextCache.getContexts$ui_tooling_data(), map);
        ArrayList arrayList = new ArrayList();
        compositionCallStack.convert(compositionGroup, 0, arrayList);
        return (T) CollectionsKt.firstOrNull((List) arrayList);
    }

    public static /* synthetic */ List findParameters$default(CompositionGroup compositionGroup, ContextCache contextCache, int i, Object obj) {
        if ((i & 1) != 0) {
            contextCache = null;
        }
        return findParameters(compositionGroup, contextCache);
    }

    public static final List<ParameterInformation> findParameters(CompositionGroup $this$findParameters, ContextCache cache) {
        Object answer$iv;
        String information = $this$findParameters.getSourceInfo();
        if (information == null) {
            return CollectionsKt.emptyList();
        }
        SourceInformationContext context = null;
        if (cache == null) {
            context = sourceInformationContextOf$default(information, null, 2, null);
        } else {
            Map<String, Object> contexts$ui_tooling_data = cache.getContexts$ui_tooling_data();
            Object value$iv = contexts$ui_tooling_data.get(information);
            if (value$iv == null) {
                answer$iv = sourceInformationContextOf$default(information, null, 2, null);
                contexts$ui_tooling_data.put(information, answer$iv);
            } else {
                answer$iv = value$iv;
            }
            if (answer$iv instanceof SourceInformationContext) {
                context = (SourceInformationContext) answer$iv;
            }
        }
        List data = new ArrayList();
        CollectionsKt.addAll(data, $this$findParameters.getData());
        return extractParameterInfo(data, context);
    }

    public static final Group asTree(CompositionData $this$asTree) {
        Group group;
        CompositionGroup compositionGroup = (CompositionGroup) CollectionsKt.firstOrNull($this$asTree.getCompositionGroups());
        return (compositionGroup == null || (group = getGroup(compositionGroup, null)) == null) ? EmptyGroup.INSTANCE : group;
    }

    public static final IntRect union(IntRect $this$union, IntRect other) {
        if (Intrinsics.areEqual($this$union, emptyBox)) {
            return other;
        }
        if (Intrinsics.areEqual(other, emptyBox)) {
            return $this$union;
        }
        return new IntRect(Math.min($this$union.getLeft(), other.getLeft()), Math.min($this$union.getTop(), other.getTop()), Math.max($this$union.getRight(), other.getRight()), Math.max($this$union.getBottom(), other.getBottom()));
    }

    private static final String keyPosition(Object key) {
        if (key instanceof String) {
            return (String) key;
        }
        if (!(key instanceof JoinedKey)) {
            return null;
        }
        String strKeyPosition = keyPosition(((JoinedKey) key).getLeft());
        return strKeyPosition == null ? keyPosition(((JoinedKey) key).getRight()) : strKeyPosition;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<ParameterInformation> extractParameterInfo(List<? extends Object> list, SourceInformationContext context) throws IllegalAccessException {
        Object element$iv;
        Object block;
        List<ParameterInformation> listExtractFromLegacyFields;
        List<? extends Object> $this$firstOrNull$iv = list;
        Iterator it = $this$firstOrNull$iv.iterator();
        while (true) {
            if (it.hasNext()) {
                element$iv = it.next();
                if (element$iv != null && StringsKt.endsWith$default(element$iv.getClass().getName(), recomposeScopeNameSuffix, false, 2, (Object) null)) {
                    break;
                }
            } else {
                element$iv = null;
                break;
            }
        }
        if (element$iv == null) {
            return CollectionsKt.emptyList();
        }
        Field fieldAccessibleField = accessibleField(element$iv.getClass(), "block");
        if (fieldAccessibleField == null || (block = fieldAccessibleField.get(element$iv)) == null) {
            return CollectionsKt.emptyList();
        }
        List<ParameterSourceInformation> parameters = context != null ? context.getParameters() : null;
        if (parameters == null) {
            parameters = CollectionsKt.emptyList();
        }
        Class<?> cls = block.getClass();
        try {
            List<Field> listFilterParameterFields = filterParameterFields(cls.getDeclaredFields(), true);
            if (!listFilterParameterFields.isEmpty()) {
                listExtractFromLegacyFields = extractFromIndyLambdaFields(listFilterParameterFields, block, parameters);
            } else {
                listExtractFromLegacyFields = extractFromLegacyFields(filterParameterFields(cls.getDeclaredFields(), false), block, parameters);
            }
            return listExtractFromLegacyFields;
        } catch (Exception e) {
            return CollectionsKt.emptyList();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0054  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final java.util.List<androidx.compose.ui.tooling.data.ParameterInformation> extractFromIndyLambdaFields(java.util.List<java.lang.reflect.Field> r27, java.lang.Object r28, java.util.List<androidx.compose.runtime.tooling.ParameterSourceInformation> r29) {
        /*
            Method dump skipped, instruction units count: 299
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.tooling.data.SlotTreeKt.extractFromIndyLambdaFields(java.util.List, java.lang.Object, java.util.List):java.util.List");
    }

    private static final List<ParameterInformation> extractFromLegacyFields(List<Field> list, Object block, List<ParameterSourceInformation> list2) throws IllegalAccessException {
        int index;
        Field field;
        ParameterInformation parameterInformationBuildParameterInfo;
        Object element$iv;
        Class<?> cls = block.getClass();
        Field fieldAccessibleField = accessibleField(cls, defaultFieldName);
        Object obj = fieldAccessibleField != null ? fieldAccessibleField.get(block) : null;
        Integer num = obj instanceof Integer ? (Integer) obj : null;
        int defaults = num != null ? num.intValue() : 0;
        Field fieldAccessibleField2 = accessibleField(cls, changedFieldName);
        Object obj2 = fieldAccessibleField2 != null ? fieldAccessibleField2.get(block) : null;
        Integer num2 = obj2 instanceof Integer ? (Integer) obj2 : null;
        int changed = num2 != null ? num2.intValue() : 0;
        List<Field> $this$sortedBy$iv = list;
        List listSortedWith = CollectionsKt.sortedWith($this$sortedBy$iv, new Comparator() { // from class: androidx.compose.ui.tooling.data.SlotTreeKt$extractFromLegacyFields$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                Field it = (Field) t;
                Field it2 = (Field) t2;
                return ComparisonsKt.compareValues(SlotTreeKt.extractFromLegacyFields$extractedName(it), SlotTreeKt.extractFromLegacyFields$extractedName(it2));
            }
        });
        List<Field> $this$mapIndexedNotNull$iv = list;
        Collection destination$iv$iv = new ArrayList();
        int index$iv$iv$iv = 0;
        for (Object item$iv$iv$iv : $this$mapIndexedNotNull$iv) {
            int index$iv$iv$iv2 = index$iv$iv$iv + 1;
            if (index$iv$iv$iv < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int index$iv$iv = index$iv$iv$iv;
            int index2 = index$iv$iv;
            ParameterSourceInformation parameterSourceInformation = (ParameterSourceInformation) CollectionsKt.getOrNull(list2, index2);
            if (parameterSourceInformation == null) {
                parameterSourceInformation = new ParameterSourceInformation(index2, null, null, 6, null);
            }
            int sortedIndex = parameterSourceInformation.getSortedIndex();
            if (sortedIndex >= list.size()) {
                parameterInformationBuildParameterInfo = null;
            } else {
                if (parameterSourceInformation.getName() != null) {
                    List<Field> $this$firstOrNull$iv = list;
                    Iterator it = $this$firstOrNull$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            index = index2;
                            element$iv = null;
                            break;
                        }
                        element$iv = it.next();
                        Field it2 = (Field) element$iv;
                        index = index2;
                        if (Intrinsics.areEqual(parameterSourceInformation.getName(), extractFromLegacyFields$extractedName(it2))) {
                            break;
                        }
                        index2 = index;
                    }
                    field = (Field) element$iv;
                } else {
                    index = index2;
                    field = null;
                }
                if (field == null) {
                    field = (Field) listSortedWith.get(sortedIndex);
                }
                if (parameterSourceInformation.getName() == null) {
                    parameterSourceInformation = new ParameterSourceInformation(sortedIndex, extractFromLegacyFields$extractedName(field), parameterSourceInformation.getInlineClass());
                }
                parameterInformationBuildParameterInfo = buildParameterInfo(field, block, index, defaults, changed, parameterSourceInformation);
            }
            if (parameterInformationBuildParameterInfo != null) {
                destination$iv$iv.add(parameterInformationBuildParameterInfo);
            }
            index$iv$iv$iv = index$iv$iv$iv2;
        }
        return (List) destination$iv$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String extractFromLegacyFields$extractedName(Field $this$extractFromLegacyFields_u24extractedName) {
        MatchGroup matchGroup;
        MatchResult matchResultFind$default = Regex.find$default(legacyLambdaRegex, $this$extractFromLegacyFields_u24extractedName.getName(), 0, 2, null);
        MatchGroupCollection extractedGroups = matchResultFind$default != null ? matchResultFind$default.getGroups() : null;
        if (extractedGroups == null || (matchGroup = extractedGroups.get(1)) == null) {
            matchGroup = extractedGroups != null ? extractedGroups.get(2) : null;
        }
        if (matchGroup != null) {
            return matchGroup.getValue();
        }
        return null;
    }

    private static final ParameterInformation buildParameterInfo(Field field, Object block, int index, int defaults, int changed, ParameterSourceInformation metadata) throws IllegalAccessException {
        String strSubstring;
        field.setAccessible(true);
        Object value = field.get(block);
        boolean fromDefault = ((1 << index) & defaults) != 0;
        int changedOffset = (index * 3) + 1;
        int parameterChanged = ((7 << changedOffset) & changed) >> changedOffset;
        boolean z = (parameterChanged & 3) == 3;
        boolean compared = (parameterChanged & 3) == 0;
        boolean stable = (parameterChanged & 4) == 0;
        if (metadata == null || (strSubstring = metadata.getName()) == null) {
            strSubstring = field.getName().substring(1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
        }
        return new ParameterInformation(strSubstring, value, fromDefault, z, compared && !fromDefault, metadata != null ? metadata.getInlineClass() : null, stable);
    }

    private static final List<Field> filterParameterFields(Field[] fields, boolean isIndyLambda) {
        boolean validPrefix;
        Collection destination$iv$iv = new ArrayList();
        for (Field field : fields) {
            String name = field.getName();
            if (isIndyLambda) {
                validPrefix = indyLambdaRegex.matches(name);
            } else {
                validPrefix = legacyLambdaRegex.matches(name);
            }
            if (validPrefix && !StringsKt.startsWith$default(name, jacocoDataField, false, 2, (Object) null)) {
                destination$iv$iv.add(field);
            }
        }
        return (List) destination$iv$iv;
    }

    public static final String getPosition(Group $this$position) {
        return keyPosition($this$position.getKey());
    }

    private static final Field accessibleField(Class<?> cls, String name) {
        Field field;
        Field[] declaredFields = cls.getDeclaredFields();
        int length = declaredFields.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                field = null;
                break;
            }
            field = declaredFields[i];
            if (Intrinsics.areEqual(field.getName(), name)) {
                break;
            }
            i++;
        }
        if (field == null) {
            return null;
        }
        Field $this$accessibleField_u24lambda_u241 = field;
        $this$accessibleField_u24lambda_u241.setAccessible(true);
        return field;
    }
}
