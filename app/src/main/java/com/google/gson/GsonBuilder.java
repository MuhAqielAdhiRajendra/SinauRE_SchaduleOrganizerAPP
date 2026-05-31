package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.NumberTypeAdapter;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.internal.sql.SqlTypesSupport;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes13.dex */
public final class GsonBuilder {
    private static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
    private static final boolean DEFAULT_ESCAPE_HTML = true;
    private static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
    private static final boolean DEFAULT_SERIALIZE_NULLS = false;
    private static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
    private static final boolean DEFAULT_USE_JDK_UNSAFE = true;
    boolean complexMapKeySerialization;
    String datePattern;
    int dateStyle;
    boolean escapeHtmlChars;
    Excluder excluder;
    final List<TypeAdapterFactory> factories;
    FieldNamingStrategy fieldNamingPolicy;
    FormattingStyle formattingStyle;
    boolean generateNonExecutableJson;
    final List<TypeAdapterFactory> hierarchyFactories;
    final Map<Type, InstanceCreator<?>> instanceCreators;
    LongSerializationPolicy longSerializationPolicy;
    ToNumberStrategy numberToNumberStrategy;
    ToNumberStrategy objectToNumberStrategy;
    final ArrayDeque<ReflectionAccessFilter> reflectionFilters;
    boolean serializeNulls;
    boolean serializeSpecialFloatingPointValues;
    Strictness strictness;
    int timeStyle;
    boolean useJdkUnsafe;
    private static final Strictness DEFAULT_STRICTNESS = null;
    private static final FormattingStyle DEFAULT_FORMATTING_STYLE = FormattingStyle.COMPACT;
    private static final String DEFAULT_DATE_PATTERN = null;
    private static final FieldNamingStrategy DEFAULT_FIELD_NAMING_STRATEGY = FieldNamingPolicy.IDENTITY;
    private static final ToNumberStrategy DEFAULT_OBJECT_TO_NUMBER_STRATEGY = ToNumberPolicy.DOUBLE;
    private static final ToNumberStrategy DEFAULT_NUMBER_TO_NUMBER_STRATEGY = ToNumberPolicy.LAZILY_PARSED_NUMBER;
    static final ConstructorConstructor DEFAULT_CONSTRUCTOR_CONSTRUCTOR = new ConstructorConstructor(Collections.emptyMap(), true, Collections.emptyList());
    static final JsonAdapterAnnotationTypeAdapterFactory DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY = new JsonAdapterAnnotationTypeAdapterFactory(DEFAULT_CONSTRUCTOR_CONSTRUCTOR);
    static final GsonBuilder DEFAULT = new GsonBuilder();
    static final List<TypeAdapterFactory> DEFAULT_TYPE_ADAPTER_FACTORIES = DEFAULT.createFactories(DEFAULT_CONSTRUCTOR_CONSTRUCTOR, DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY);

    public GsonBuilder() {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = DEFAULT_FIELD_NAMING_STRATEGY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.datePattern = DEFAULT_DATE_PATTERN;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.formattingStyle = DEFAULT_FORMATTING_STYLE;
        this.generateNonExecutableJson = false;
        this.strictness = DEFAULT_STRICTNESS;
        this.useJdkUnsafe = true;
        this.objectToNumberStrategy = DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        this.numberToNumberStrategy = DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        this.reflectionFilters = new ArrayDeque<>();
    }

    GsonBuilder(Gson gson) {
        this.excluder = Excluder.DEFAULT;
        this.longSerializationPolicy = LongSerializationPolicy.DEFAULT;
        this.fieldNamingPolicy = DEFAULT_FIELD_NAMING_STRATEGY;
        this.instanceCreators = new HashMap();
        this.factories = new ArrayList();
        this.hierarchyFactories = new ArrayList();
        this.serializeNulls = false;
        this.datePattern = DEFAULT_DATE_PATTERN;
        this.dateStyle = 2;
        this.timeStyle = 2;
        this.complexMapKeySerialization = false;
        this.serializeSpecialFloatingPointValues = false;
        this.escapeHtmlChars = true;
        this.formattingStyle = DEFAULT_FORMATTING_STYLE;
        this.generateNonExecutableJson = false;
        this.strictness = DEFAULT_STRICTNESS;
        this.useJdkUnsafe = true;
        this.objectToNumberStrategy = DEFAULT_OBJECT_TO_NUMBER_STRATEGY;
        this.numberToNumberStrategy = DEFAULT_NUMBER_TO_NUMBER_STRATEGY;
        this.reflectionFilters = new ArrayDeque<>();
        this.excluder = gson.excluder;
        this.fieldNamingPolicy = gson.fieldNamingStrategy;
        this.instanceCreators.putAll(gson.instanceCreators);
        this.serializeNulls = gson.serializeNulls;
        this.complexMapKeySerialization = gson.complexMapKeySerialization;
        this.generateNonExecutableJson = gson.generateNonExecutableJson;
        this.escapeHtmlChars = gson.htmlSafe;
        this.formattingStyle = gson.formattingStyle;
        this.strictness = gson.strictness;
        this.serializeSpecialFloatingPointValues = gson.serializeSpecialFloatingPointValues;
        this.longSerializationPolicy = gson.longSerializationPolicy;
        this.datePattern = gson.datePattern;
        this.dateStyle = gson.dateStyle;
        this.timeStyle = gson.timeStyle;
        this.factories.addAll(gson.builderFactories);
        this.hierarchyFactories.addAll(gson.builderHierarchyFactories);
        this.useJdkUnsafe = gson.useJdkUnsafe;
        this.objectToNumberStrategy = gson.objectToNumberStrategy;
        this.numberToNumberStrategy = gson.numberToNumberStrategy;
        this.reflectionFilters.addAll(gson.reflectionFilters);
    }

    public GsonBuilder setVersion(double version) {
        if (Double.isNaN(version) || version < 0.0d) {
            throw new IllegalArgumentException("Invalid version: " + version);
        }
        this.excluder = this.excluder.withVersion(version);
        return this;
    }

    public GsonBuilder excludeFieldsWithModifiers(int... modifiers) {
        Objects.requireNonNull(modifiers);
        this.excluder = this.excluder.withModifiers(modifiers);
        return this;
    }

    public GsonBuilder generateNonExecutableJson() {
        this.generateNonExecutableJson = true;
        return this;
    }

    public GsonBuilder excludeFieldsWithoutExposeAnnotation() {
        this.excluder = this.excluder.excludeFieldsWithoutExposeAnnotation();
        return this;
    }

    public GsonBuilder serializeNulls() {
        this.serializeNulls = true;
        return this;
    }

    public GsonBuilder enableComplexMapKeySerialization() {
        this.complexMapKeySerialization = true;
        return this;
    }

    public GsonBuilder disableInnerClassSerialization() {
        this.excluder = this.excluder.disableInnerClassSerialization();
        return this;
    }

    public GsonBuilder setLongSerializationPolicy(LongSerializationPolicy serializationPolicy) {
        this.longSerializationPolicy = (LongSerializationPolicy) Objects.requireNonNull(serializationPolicy);
        return this;
    }

    public GsonBuilder setFieldNamingPolicy(FieldNamingPolicy namingConvention) {
        return setFieldNamingStrategy(namingConvention);
    }

    public GsonBuilder setFieldNamingStrategy(FieldNamingStrategy fieldNamingStrategy) {
        this.fieldNamingPolicy = (FieldNamingStrategy) Objects.requireNonNull(fieldNamingStrategy);
        return this;
    }

    public GsonBuilder setObjectToNumberStrategy(ToNumberStrategy objectToNumberStrategy) {
        this.objectToNumberStrategy = (ToNumberStrategy) Objects.requireNonNull(objectToNumberStrategy);
        return this;
    }

    public GsonBuilder setNumberToNumberStrategy(ToNumberStrategy numberToNumberStrategy) {
        this.numberToNumberStrategy = (ToNumberStrategy) Objects.requireNonNull(numberToNumberStrategy);
        return this;
    }

    public GsonBuilder setExclusionStrategies(ExclusionStrategy... strategies) {
        Objects.requireNonNull(strategies);
        for (ExclusionStrategy strategy : strategies) {
            this.excluder = this.excluder.withExclusionStrategy(strategy, true, true);
        }
        return this;
    }

    public GsonBuilder addSerializationExclusionStrategy(ExclusionStrategy strategy) {
        Objects.requireNonNull(strategy);
        this.excluder = this.excluder.withExclusionStrategy(strategy, true, false);
        return this;
    }

    public GsonBuilder addDeserializationExclusionStrategy(ExclusionStrategy strategy) {
        Objects.requireNonNull(strategy);
        this.excluder = this.excluder.withExclusionStrategy(strategy, false, true);
        return this;
    }

    public GsonBuilder setPrettyPrinting() {
        return setFormattingStyle(FormattingStyle.PRETTY);
    }

    public GsonBuilder setFormattingStyle(FormattingStyle formattingStyle) {
        this.formattingStyle = (FormattingStyle) Objects.requireNonNull(formattingStyle);
        return this;
    }

    @Deprecated
    public GsonBuilder setLenient() {
        return setStrictness(Strictness.LENIENT);
    }

    public GsonBuilder setStrictness(Strictness strictness) {
        this.strictness = (Strictness) Objects.requireNonNull(strictness);
        return this;
    }

    public GsonBuilder disableHtmlEscaping() {
        this.escapeHtmlChars = false;
        return this;
    }

    public GsonBuilder setDateFormat(String pattern) {
        if (pattern != null) {
            try {
                new SimpleDateFormat(pattern);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("The date pattern '" + pattern + "' is not valid", e);
            }
        }
        this.datePattern = pattern;
        return this;
    }

    @Deprecated
    public GsonBuilder setDateFormat(int dateStyle) {
        this.dateStyle = checkDateFormatStyle(dateStyle);
        this.datePattern = null;
        return this;
    }

    public GsonBuilder setDateFormat(int dateStyle, int timeStyle) {
        this.dateStyle = checkDateFormatStyle(dateStyle);
        this.timeStyle = checkDateFormatStyle(timeStyle);
        this.datePattern = null;
        return this;
    }

    private static int checkDateFormatStyle(int style) {
        if (style < 0 || style > 3) {
            throw new IllegalArgumentException("Invalid style: " + style);
        }
        return style;
    }

    public GsonBuilder registerTypeAdapter(Type type, Object typeAdapter) {
        Objects.requireNonNull(type);
        Objects.requireNonNull(typeAdapter);
        if (!(typeAdapter instanceof JsonSerializer) && !(typeAdapter instanceof JsonDeserializer) && !(typeAdapter instanceof InstanceCreator) && !(typeAdapter instanceof TypeAdapter)) {
            throw new IllegalArgumentException("Class " + typeAdapter.getClass().getName() + " does not implement any supported type adapter class or interface");
        }
        if (hasNonOverridableAdapter(type)) {
            throw new IllegalArgumentException("Cannot override built-in adapter for " + type);
        }
        if (typeAdapter instanceof InstanceCreator) {
            this.instanceCreators.put(type, (InstanceCreator) typeAdapter);
        }
        if ((typeAdapter instanceof JsonSerializer) || (typeAdapter instanceof JsonDeserializer)) {
            TypeToken<?> typeToken = TypeToken.get(type);
            this.factories.add(TreeTypeAdapter.newFactoryWithMatchRawType(typeToken, typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            TypeAdapterFactory factory = TypeAdapters.newFactory(TypeToken.get(type), (TypeAdapter) typeAdapter);
            this.factories.add(factory);
        }
        return this;
    }

    private static boolean hasNonOverridableAdapter(Type type) {
        return type == Object.class;
    }

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory factory) {
        Objects.requireNonNull(factory);
        this.factories.add(factory);
        return this;
    }

    public GsonBuilder registerTypeHierarchyAdapter(Class<?> baseType, Object typeAdapter) {
        Objects.requireNonNull(baseType);
        Objects.requireNonNull(typeAdapter);
        if (!(typeAdapter instanceof JsonSerializer) && !(typeAdapter instanceof JsonDeserializer) && !(typeAdapter instanceof TypeAdapter)) {
            throw new IllegalArgumentException("Class " + typeAdapter.getClass().getName() + " does not implement any supported type adapter class or interface");
        }
        if ((typeAdapter instanceof JsonDeserializer) || (typeAdapter instanceof JsonSerializer)) {
            this.hierarchyFactories.add(TreeTypeAdapter.newTypeHierarchyFactory(baseType, typeAdapter));
        }
        if (typeAdapter instanceof TypeAdapter) {
            TypeAdapterFactory factory = TypeAdapters.newTypeHierarchyFactory(baseType, (TypeAdapter) typeAdapter);
            this.factories.add(factory);
        }
        return this;
    }

    public GsonBuilder serializeSpecialFloatingPointValues() {
        this.serializeSpecialFloatingPointValues = true;
        return this;
    }

    public GsonBuilder disableJdkUnsafe() {
        this.useJdkUnsafe = false;
        return this;
    }

    public GsonBuilder addReflectionAccessFilter(ReflectionAccessFilter filter) {
        Objects.requireNonNull(filter);
        this.reflectionFilters.addFirst(filter);
        return this;
    }

    public Gson create() {
        return new Gson(this);
    }

    List<TypeAdapterFactory> createFactories(ConstructorConstructor constructorConstructor, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory) {
        ArrayList<TypeAdapterFactory> factories = new ArrayList<>();
        factories.add(TypeAdapters.JSON_ELEMENT_FACTORY);
        factories.add(ObjectTypeAdapter.getFactory(this.objectToNumberStrategy));
        factories.add(this.excluder);
        addUserDefinedAdapters(factories);
        addDateTypeAdapters(factories);
        factories.add(TypeAdapters.STRING_FACTORY);
        factories.add(TypeAdapters.INTEGER_FACTORY);
        factories.add(TypeAdapters.BOOLEAN_FACTORY);
        factories.add(TypeAdapters.BYTE_FACTORY);
        factories.add(TypeAdapters.SHORT_FACTORY);
        TypeAdapter<Number> longAdapter = this.longSerializationPolicy.typeAdapter();
        factories.add(TypeAdapters.newFactory(Long.TYPE, Long.class, longAdapter));
        factories.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter()));
        factories.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter()));
        factories.add(NumberTypeAdapter.getFactory(this.numberToNumberStrategy));
        factories.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
        factories.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
        factories.add(TypeAdapters.newFactory(AtomicLong.class, TypeAdapters.atomicLongAdapter(longAdapter)));
        factories.add(TypeAdapters.newFactory(AtomicLongArray.class, TypeAdapters.atomicLongArrayAdapter(longAdapter)));
        factories.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
        factories.add(TypeAdapters.CHARACTER_FACTORY);
        factories.add(TypeAdapters.STRING_BUILDER_FACTORY);
        factories.add(TypeAdapters.STRING_BUFFER_FACTORY);
        factories.add(TypeAdapters.BIG_DECIMAL_FACTORY);
        factories.add(TypeAdapters.BIG_INTEGER_FACTORY);
        factories.add(TypeAdapters.LAZILY_PARSED_NUMBER_FACTORY);
        factories.add(TypeAdapters.URL_FACTORY);
        factories.add(TypeAdapters.URI_FACTORY);
        factories.add(TypeAdapters.UUID_FACTORY);
        factories.add(TypeAdapters.CURRENCY_FACTORY);
        factories.add(TypeAdapters.LOCALE_FACTORY);
        factories.add(TypeAdapters.INET_ADDRESS_FACTORY);
        factories.add(TypeAdapters.BIT_SET_FACTORY);
        factories.add(DefaultDateTypeAdapter.DEFAULT_STYLE_FACTORY);
        factories.add(TypeAdapters.CALENDAR_FACTORY);
        TypeAdapterFactory javaTimeFactory = TypeAdapters.javaTimeTypeAdapterFactory();
        if (javaTimeFactory != null) {
            factories.add(javaTimeFactory);
        }
        factories.addAll(SqlTypesSupport.SQL_TYPE_FACTORIES);
        factories.add(ArrayTypeAdapter.FACTORY);
        factories.add(TypeAdapters.CLASS_FACTORY);
        factories.add(new CollectionTypeAdapterFactory(constructorConstructor));
        factories.add(new MapTypeAdapterFactory(constructorConstructor, this.complexMapKeySerialization));
        factories.add(jsonAdapterFactory);
        factories.add(TypeAdapters.ENUM_FACTORY);
        factories.add(new ReflectiveTypeAdapterFactory(constructorConstructor, this.fieldNamingPolicy, this.excluder, jsonAdapterFactory, newImmutableList(this.reflectionFilters)));
        factories.trimToSize();
        return Collections.unmodifiableList(factories);
    }

    static <E> List<E> newImmutableList(Collection<E> collection) {
        Object next;
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        if (collection.size() == 1) {
            if (collection instanceof List) {
                next = ((List) collection).get(0);
            } else {
                next = collection.iterator().next();
            }
            return Collections.singletonList(next);
        }
        List<E> list = Collections.unmodifiableList(Arrays.asList(collection.toArray()));
        return list;
    }

    private TypeAdapter<Number> doubleAdapter() {
        return this.serializeSpecialFloatingPointValues ? TypeAdapters.DOUBLE : TypeAdapters.DOUBLE_STRICT;
    }

    private TypeAdapter<Number> floatAdapter() {
        return this.serializeSpecialFloatingPointValues ? TypeAdapters.FLOAT : TypeAdapters.FLOAT_STRICT;
    }

    private void addUserDefinedAdapters(List<TypeAdapterFactory> all) {
        if (!this.factories.isEmpty()) {
            List<TypeAdapterFactory> reversedFactories = new ArrayList<>(this.factories);
            Collections.reverse(reversedFactories);
            all.addAll(reversedFactories);
        }
        if (!this.hierarchyFactories.isEmpty()) {
            List<TypeAdapterFactory> reversedHierarchyFactories = new ArrayList<>(this.hierarchyFactories);
            Collections.reverse(reversedHierarchyFactories);
            all.addAll(reversedHierarchyFactories);
        }
    }

    private void addDateTypeAdapters(List<TypeAdapterFactory> factories) {
        TypeAdapterFactory dateAdapterFactory;
        boolean sqlTypesSupported = SqlTypesSupport.SUPPORTS_SQL_TYPES;
        TypeAdapterFactory sqlTimestampAdapterFactory = null;
        TypeAdapterFactory sqlDateAdapterFactory = null;
        if (this.datePattern != null && !this.datePattern.trim().isEmpty()) {
            dateAdapterFactory = DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(this.datePattern);
            if (sqlTypesSupported) {
                sqlTimestampAdapterFactory = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(this.datePattern);
                sqlDateAdapterFactory = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(this.datePattern);
            }
        } else if (this.dateStyle != 2 || this.timeStyle != 2) {
            dateAdapterFactory = DefaultDateTypeAdapter.DateType.DATE.createAdapterFactory(this.dateStyle, this.timeStyle);
            if (sqlTypesSupported) {
                sqlTimestampAdapterFactory = SqlTypesSupport.TIMESTAMP_DATE_TYPE.createAdapterFactory(this.dateStyle, this.timeStyle);
                sqlDateAdapterFactory = SqlTypesSupport.DATE_DATE_TYPE.createAdapterFactory(this.dateStyle, this.timeStyle);
            }
        } else {
            return;
        }
        factories.add(dateAdapterFactory);
        if (sqlTypesSupported) {
            factories.add(sqlTimestampAdapterFactory);
            factories.add(sqlDateAdapterFactory);
        }
    }
}
