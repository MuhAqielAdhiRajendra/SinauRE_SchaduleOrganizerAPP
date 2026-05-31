package androidx.compose.ui.tooling;

import androidx.compose.ui.tooling.data.Group;
import androidx.compose.ui.tooling.preview.PreviewParameterProvider;
import androidx.compose.ui.tooling.preview.PreviewWrapperProvider;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* JADX INFO: compiled from: PreviewUtils.android.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001a\u0010\u0000\u001a\u0010\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u0002\u0018\u00010\u0001*\u00020\u0003H\u0000\u001a\u0016\u0010\u0004\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0001*\u00020\u0003H\u0000\u001a3\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0014\u0010\t\u001a\u0010\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u0002\u0018\u00010\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\u0010\f\u001a\u001a\u0010\r\u001a\u00020\u00052\u0010\u0010\u000e\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u0001H\u0000\u001a\u0014\u0010\u000f\u001a\u0004\u0018\u00010\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0002\u001a\"\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00150\u0014H\u0000\u001a&\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u0017*\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00150\u0014H\u0000\u001a4\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u0019\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0002\u0010\u001a\u001a\u00020\u0015H\u0002\u001a)\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007*\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u001c2\u0006\u0010\u001d\u001a\u00020\u000bH\u0002¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"asPreviewProviderClass", "Ljava/lang/Class;", "Landroidx/compose/ui/tooling/preview/PreviewParameterProvider;", "", "asPreviewWrapperProviderClass", "Landroidx/compose/ui/tooling/preview/PreviewWrapperProvider;", "getPreviewProviderParameters", "", "", "parameterProviderClass", "parameterProviderIndex", "", "(Ljava/lang/Class;I)[Ljava/lang/Object;", "instantiatePreviewWrapperProvider", "previewWrapperProvider", "unwrapIfInline", "classToCheck", "firstOrNull", "Landroidx/compose/ui/tooling/data/Group;", "predicate", "Lkotlin/Function1;", "", "findAll", "", "findGroupsThatMatchPredicate", "root", "findOnlyFirst", "toArray", "Lkotlin/sequences/Sequence;", "size", "(Lkotlin/sequences/Sequence;I)[Ljava/lang/Object;", "ui-tooling"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class PreviewUtils_androidKt {
    public static final Class<? extends PreviewParameterProvider<?>> asPreviewProviderClass(String $this$asPreviewProviderClass) {
        try {
            Class cls = Class.forName($this$asPreviewProviderClass);
            if (cls instanceof Class) {
                return cls;
            }
            return null;
        } catch (ClassNotFoundException e) {
            PreviewLogger.INSTANCE.logError$ui_tooling("Unable to find PreviewProvider '" + $this$asPreviewProviderClass + '\'', e);
            return null;
        }
    }

    public static final Class<? extends PreviewWrapperProvider> asPreviewWrapperProviderClass(String $this$asPreviewWrapperProviderClass) {
        try {
            Class cls = Class.forName($this$asPreviewWrapperProviderClass);
            if (cls instanceof Class) {
                return cls;
            }
            return null;
        } catch (ClassNotFoundException e) {
            PreviewLogger.INSTANCE.logError$ui_tooling("Unable to find PreviewWrapperProvider '" + $this$asPreviewWrapperProviderClass + '\'', e);
            return null;
        }
    }

    public static final Object[] getPreviewProviderParameters(Class<? extends PreviewParameterProvider<?>> cls, int parameterProviderIndex) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        if (cls != null) {
            try {
                Constructor<?>[] constructors = cls.getConstructors();
                Constructor<?> constructor = null;
                boolean found$iv = false;
                int length = constructors.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        Constructor<?> constructor2 = constructors[i];
                        if (constructor2.getParameterTypes().length == 0) {
                            if (found$iv) {
                                constructor = null;
                                break;
                            }
                            constructor = constructor2;
                            found$iv = true;
                        }
                        i++;
                    } else if (!found$iv) {
                        constructor = null;
                    }
                }
                if (constructor != null) {
                    constructor.setAccessible(true);
                    Object objNewInstance = constructor.newInstance(new Object[0]);
                    Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type androidx.compose.ui.tooling.preview.PreviewParameterProvider<*>");
                    PreviewParameterProvider params = (PreviewParameterProvider) objNewInstance;
                    if (parameterProviderIndex < 0) {
                        return toArray(params.getValues(), params.getCount());
                    }
                    Iterable $this$map$iv = CollectionsKt.listOf(SequencesKt.elementAt(params.getValues(), parameterProviderIndex));
                    Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
                    for (Object item$iv$iv : $this$map$iv) {
                        destination$iv$iv.add(unwrapIfInline(item$iv$iv));
                    }
                    Collection $this$toTypedArray$iv = (List) destination$iv$iv;
                    return $this$toTypedArray$iv.toArray(new Object[0]);
                }
                throw new IllegalArgumentException("PreviewParameterProvider constructor can not have parameters");
            } catch (KotlinReflectionNotSupportedError e) {
                throw new IllegalStateException("Deploying Compose Previews with PreviewParameterProvider arguments requires adding a dependency to the kotlin-reflect library.\nConsider adding 'debugImplementation \"org.jetbrains.kotlin:kotlin-reflect:$kotlin_version\"' to the module's build.gradle.");
            }
        }
        return new Object[0];
    }

    public static final PreviewWrapperProvider instantiatePreviewWrapperProvider(Class<? extends PreviewWrapperProvider> cls) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<?>[] constructors;
        if (cls != null && (constructors = cls.getConstructors()) != null) {
            Constructor<?> constructor = null;
            boolean found$iv = false;
            int length = constructors.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Constructor<?> constructor2 = constructors[i];
                    if (constructor2.getParameterTypes().length == 0) {
                        if (found$iv) {
                            constructor = null;
                            break;
                        }
                        constructor = constructor2;
                        found$iv = true;
                    }
                    i++;
                } else if (!found$iv) {
                    constructor = null;
                }
            }
            if (constructor != null) {
                constructor.setAccessible(true);
                Object objNewInstance = constructor.newInstance(new Object[0]);
                Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type androidx.compose.ui.tooling.preview.PreviewWrapperProvider");
                return (PreviewWrapperProvider) objNewInstance;
            }
        }
        throw new IllegalArgumentException("PreviewWrapperProvider constructor can not have parameters");
    }

    private static final Object unwrapIfInline(Object classToCheck) throws NoSuchFieldException {
        boolean z;
        if (classToCheck != null) {
            Object[] $this$any$iv = classToCheck.getClass().getAnnotations();
            int length = $this$any$iv.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Object element$iv = $this$any$iv[i];
                    if (element$iv instanceof JvmInline) {
                        z = true;
                        break;
                    }
                    i++;
                } else {
                    z = false;
                    break;
                }
            }
            if (z) {
                for (Field field : classToCheck.getClass().getDeclaredFields()) {
                    if (field.getType().isPrimitive()) {
                        String fieldName = field.getName();
                        Field it = classToCheck.getClass().getDeclaredField(fieldName);
                        it.setAccessible(true);
                        return it.get(classToCheck);
                    }
                }
                throw new NoSuchElementException("Array contains no element matching the predicate.");
            }
        }
        return classToCheck;
    }

    public static final Group firstOrNull(Group $this$firstOrNull, Function1<? super Group, Boolean> function1) {
        return (Group) CollectionsKt.firstOrNull((List) findGroupsThatMatchPredicate($this$firstOrNull, function1, true));
    }

    public static final List<Group> findAll(Group $this$findAll, Function1<? super Group, Boolean> function1) {
        return findGroupsThatMatchPredicate$default($this$findAll, function1, false, 4, null);
    }

    static /* synthetic */ List findGroupsThatMatchPredicate$default(Group group, Function1 function1, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return findGroupsThatMatchPredicate(group, function1, z);
    }

    private static final List<Group> findGroupsThatMatchPredicate(Group root, Function1<? super Group, Boolean> function1, boolean findOnlyFirst) {
        List result = new ArrayList();
        List stack = CollectionsKt.mutableListOf(root);
        while (!stack.isEmpty()) {
            Group current = (Group) CollectionsKt.removeLast(stack);
            if (function1.invoke(current).booleanValue()) {
                if (findOnlyFirst) {
                    return CollectionsKt.listOf(current);
                }
                result.add(current);
            }
            stack.addAll(current.getChildren());
        }
        return result;
    }

    private static final Object[] toArray(Sequence<? extends Object> sequence, int size) {
        Iterator<? extends Object> it = sequence.iterator();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            objArr[i] = it.next();
        }
        return objArr;
    }
}
