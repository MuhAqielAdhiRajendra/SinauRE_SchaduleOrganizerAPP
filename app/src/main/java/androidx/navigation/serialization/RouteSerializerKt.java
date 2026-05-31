package androidx.navigation.serialization;

import androidx.autofill.HintConstants;
import androidx.navigation.NamedNavArgument;
import androidx.navigation.NamedNavArgumentKt;
import androidx.navigation.NavArgumentBuilder;
import androidx.navigation.NavType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.descriptors.ContextAwareKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.StructureKind;

/* JADX INFO: compiled from: RouteSerializer.kt */
/* JADX INFO: loaded from: classes13.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\u001a>\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00052\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0001H\u0000\u001a8\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005H\u0007\u001a;\u0010\f\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\r2\u0006\u0010\u000e\u001a\u0002H\u00022\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00070\u0005H\u0007¢\u0006\u0002\u0010\u000f\u001a&\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002\u001a.\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0007*\u00020\u00152\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0005H\u0002\u001a\u0018\u0010\u0016\u001a\u00020\u0017\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a\u008c\u0001\u0010\u0018\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\b\u0002\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u00052S\u0010\u0019\u001aO\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0007¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00110\u001aH\u0003¢\u0006\u0002\b \u001a\u008e\u0001\u0010\u0018\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001a\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00070\u00052S\u0010\u0019\u001aO\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u0001¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u001b\u0012\u0019\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0007¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020\u00110\u001aH\u0003¢\u0006\u0002\b!\u001a(\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u00012\u0006\u0010%\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002\u001a\f\u0010&\u001a\u00020'*\u00020\u0015H\u0000¨\u0006("}, d2 = {"generateRoutePattern", "", "T", "Lkotlinx/serialization/KSerializer;", "typeMap", "", "Lkotlin/reflect/KType;", "Landroidx/navigation/NavType;", "path", "generateNavArguments", "", "Landroidx/navigation/NamedNavArgument;", "generateRouteWithArgs", "", "route", "(Ljava/lang/Object;Ljava/util/Map;)Ljava/lang/String;", "assertNotAbstractClass", "", "handler", "Lkotlin/Function0;", "computeNavType", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "generateHashCode", "", "forEachIndexed", "operation", "Lkotlin/Function3;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "argName", "navType", "forEachIndexedKType", "forEachIndexedName", "unknownNavTypeErrorMessage", "fieldName", "fieldType", "className", "isValueClass", "", "navigation-common_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class RouteSerializerKt {
    public static /* synthetic */ String generateRoutePattern$default(KSerializer kSerializer, Map map, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        if ((i & 2) != 0) {
            str = null;
        }
        return generateRoutePattern(kSerializer, map, str);
    }

    public static final <T> String generateRoutePattern(final KSerializer<T> kSerializer, Map<KType, ? extends NavType<?>> typeMap, String path) {
        final RouteBuilder builder;
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        Intrinsics.checkNotNullParameter(typeMap, "typeMap");
        assertNotAbstractClass(kSerializer, new Function0() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RouteSerializerKt.generateRoutePattern$lambda$0(kSerializer);
            }
        });
        if (path != null) {
            builder = new RouteBuilder(path, kSerializer);
        } else {
            builder = new RouteBuilder(kSerializer);
        }
        forEachIndexedKType(kSerializer, typeMap, new Function3() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return RouteSerializerKt.generateRoutePattern$lambda$1(builder, ((Integer) obj).intValue(), (String) obj2, (NavType) obj3);
            }
        });
        return builder.build();
    }

    static final Unit generateRoutePattern$lambda$0(KSerializer $this_generateRoutePattern) {
        StringBuilder sbAppend = new StringBuilder().append("Cannot generate route pattern from polymorphic class ");
        KClass<?> capturedKClass = ContextAwareKt.getCapturedKClass($this_generateRoutePattern.getDescriptor());
        throw new IllegalArgumentException(sbAppend.append(capturedKClass != null ? capturedKClass.getSimpleName() : null).append(". Routes can only be generated from concrete classes or objects.").toString());
    }

    static final Unit generateRoutePattern$lambda$1(RouteBuilder $builder, int index, String argName, NavType navType) {
        Intrinsics.checkNotNullParameter(argName, "argName");
        Intrinsics.checkNotNullParameter(navType, "navType");
        $builder.appendPattern(index, argName, navType);
        return Unit.INSTANCE;
    }

    public static /* synthetic */ List generateNavArguments$default(KSerializer kSerializer, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        return generateNavArguments(kSerializer, map);
    }

    public static final <T> List<NamedNavArgument> generateNavArguments(final KSerializer<T> kSerializer, final Map<KType, ? extends NavType<?>> typeMap) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        Intrinsics.checkNotNullParameter(typeMap, "typeMap");
        assertNotAbstractClass(kSerializer, new Function0() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return RouteSerializerKt.generateNavArguments$lambda$2(kSerializer);
            }
        });
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        ArrayList arrayList = new ArrayList(elementsCount);
        for (int i = 0; i < elementsCount; i++) {
            final int index = i;
            final String name = kSerializer.getDescriptor().getElementName(index);
            arrayList.add(NamedNavArgumentKt.navArgument(name, new Function1() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RouteSerializerKt.generateNavArguments$lambda$4$lambda$3(kSerializer, index, typeMap, name, (NavArgumentBuilder) obj);
                }
            }));
        }
        return arrayList;
    }

    static final Unit generateNavArguments$lambda$2(KSerializer $this_generateNavArguments) {
        throw new IllegalArgumentException("Cannot generate NavArguments for polymorphic serializer " + $this_generateNavArguments + ". Arguments can only be generated from concrete classes or objects.");
    }

    static final Unit generateNavArguments$lambda$4$lambda$3(KSerializer $this_generateNavArguments, int $index, Map $typeMap, String $name, NavArgumentBuilder navArgument) {
        Intrinsics.checkNotNullParameter(navArgument, "$this$navArgument");
        SerialDescriptor element = $this_generateNavArguments.getDescriptor().getElementDescriptor($index);
        boolean isNullable = element.isNullable();
        NavType<?> navTypeComputeNavType = computeNavType(element, $typeMap);
        if (navTypeComputeNavType != null) {
            navArgument.setType(navTypeComputeNavType);
            navArgument.setNullable(isNullable);
            if ($this_generateNavArguments.getDescriptor().isElementOptional($index)) {
                navArgument.setUnknownDefaultValuePresent$navigation_common_release(true);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalArgumentException(unknownNavTypeErrorMessage($name, element.getSerialName(), $this_generateNavArguments.getDescriptor().getSerialName(), $typeMap.toString()));
    }

    public static final <T> String generateRouteWithArgs(T route, Map<String, ? extends NavType<Object>> typeMap) {
        Intrinsics.checkNotNullParameter(route, "route");
        Intrinsics.checkNotNullParameter(typeMap, "typeMap");
        KSerializer serializer = SerializersKt.serializer(Reflection.getOrCreateKotlinClass(route.getClass()));
        final Map<String, List<String>> mapEncodeToArgMap = new RouteEncoder(serializer, typeMap).encodeToArgMap(route);
        final RouteBuilder builder = new RouteBuilder(serializer);
        forEachIndexedName(serializer, typeMap, new Function3() { // from class: androidx.navigation.serialization.RouteSerializerKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return RouteSerializerKt.generateRouteWithArgs$lambda$5(mapEncodeToArgMap, builder, ((Integer) obj).intValue(), (String) obj2, (NavType) obj3);
            }
        });
        return builder.build();
    }

    static final Unit generateRouteWithArgs$lambda$5(Map $argMap, RouteBuilder $builder, int index, String argName, NavType navType) {
        Intrinsics.checkNotNullParameter(argName, "argName");
        Intrinsics.checkNotNullParameter(navType, "navType");
        Object obj = $argMap.get(argName);
        Intrinsics.checkNotNull(obj);
        $builder.appendArg(index, argName, navType, (List) obj);
        return Unit.INSTANCE;
    }

    private static final <T> void assertNotAbstractClass(KSerializer<T> kSerializer, Function0<Unit> function0) {
        if (kSerializer instanceof PolymorphicSerializer) {
            function0.invoke();
        }
    }

    private static final NavType<Object> computeNavType(SerialDescriptor $this$computeNavType, Map<KType, ? extends NavType<?>> map) {
        Object next;
        Iterator<T> it = map.keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            KType kType = (KType) next;
            if (NavTypeConverterKt.matchKType($this$computeNavType, kType)) {
                break;
            }
        }
        KType it2 = (KType) next;
        NavType<?> navType = it2 != null ? map.get(it2) : null;
        if (!(navType instanceof NavType)) {
            navType = null;
        }
        NavType<?> navType2 = navType == null ? NavTypeConverterKt.getNavType($this$computeNavType) : navType;
        if (Intrinsics.areEqual(navType2, UNKNOWN.INSTANCE)) {
            return null;
        }
        Intrinsics.checkNotNull(navType2, "null cannot be cast to non-null type androidx.navigation.NavType<kotlin.Any?>");
        return navType2;
    }

    public static final <T> int generateHashCode(KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        int hash = kSerializer.getDescriptor().getSerialName().hashCode();
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            hash = (hash * 31) + kSerializer.getDescriptor().getElementName(i).hashCode();
        }
        return hash;
    }

    static /* synthetic */ void forEachIndexedKType$default(KSerializer kSerializer, Map map, Function3 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            map = MapsKt.emptyMap();
        }
        forEachIndexedKType(kSerializer, map, function3);
    }

    private static final <T> void forEachIndexedKType(KSerializer<T> kSerializer, Map<KType, ? extends NavType<?>> map, Function3<? super Integer, ? super String, ? super NavType<Object>, Unit> function3) {
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String argName = kSerializer.getDescriptor().getElementName(i);
            NavType<Object> navTypeComputeNavType = computeNavType(kSerializer.getDescriptor().getElementDescriptor(i), map);
            if (navTypeComputeNavType == null) {
                throw new IllegalArgumentException(unknownNavTypeErrorMessage(argName, kSerializer.getDescriptor().getElementDescriptor(i).getSerialName(), kSerializer.getDescriptor().getSerialName(), map.toString()));
            }
            function3.invoke(Integer.valueOf(i), argName, navTypeComputeNavType);
        }
    }

    private static final <T> void forEachIndexedName(KSerializer<T> kSerializer, Map<String, ? extends NavType<Object>> map, Function3<? super Integer, ? super String, ? super NavType<Object>, Unit> function3) {
        int elementsCount = kSerializer.getDescriptor().getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String argName = kSerializer.getDescriptor().getElementName(i);
            NavType<Object> navType = map.get(argName);
            if (navType == null) {
                throw new IllegalStateException(("Cannot locate NavType for argument [" + argName + ']').toString());
            }
            function3.invoke(Integer.valueOf(i), argName, navType);
        }
    }

    private static final String unknownNavTypeErrorMessage(String fieldName, String fieldType, String className, String typeMap) {
        return "Route " + className + " could not find any NavType for argument " + fieldName + " of type " + fieldType + " - typeMap received was " + typeMap;
    }

    public static final boolean isValueClass(SerialDescriptor $this$isValueClass) {
        Intrinsics.checkNotNullParameter($this$isValueClass, "<this>");
        return Intrinsics.areEqual($this$isValueClass.getKind(), StructureKind.CLASS.INSTANCE) && $this$isValueClass.getIsInline() && $this$isValueClass.getElementsCount() == 1;
    }
}
