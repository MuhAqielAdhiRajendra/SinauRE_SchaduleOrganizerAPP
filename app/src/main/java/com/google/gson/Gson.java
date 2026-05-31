package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.SerializationDelegatingTypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes13.dex */
public final class Gson {
    private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
    final List<TypeAdapterFactory> builderFactories;
    final List<TypeAdapterFactory> builderHierarchyFactories;
    final boolean complexMapKeySerialization;
    private final ConstructorConstructor constructorConstructor;
    final String datePattern;
    final int dateStyle;
    final Excluder excluder;
    final List<TypeAdapterFactory> factories;
    final FieldNamingStrategy fieldNamingStrategy;
    final FormattingStyle formattingStyle;
    final boolean generateNonExecutableJson;
    final boolean htmlSafe;
    final Map<Type, InstanceCreator<?>> instanceCreators;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
    final LongSerializationPolicy longSerializationPolicy;
    final ToNumberStrategy numberToNumberStrategy;
    final ToNumberStrategy objectToNumberStrategy;
    final List<ReflectionAccessFilter> reflectionFilters;
    final boolean serializeNulls;
    final boolean serializeSpecialFloatingPointValues;
    final Strictness strictness;
    private final ThreadLocal<Map<TypeToken<?>, TypeAdapter<?>>> threadLocalAdapterResults;
    final int timeStyle;
    private final ConcurrentMap<TypeToken<?>, TypeAdapter<?>> typeTokenCache;
    final boolean useJdkUnsafe;

    public Gson() {
        this(GsonBuilder.DEFAULT);
    }

    Gson(GsonBuilder builder) {
        this.threadLocalAdapterResults = new ThreadLocal<>();
        this.typeTokenCache = new ConcurrentHashMap();
        this.excluder = builder.excluder;
        this.fieldNamingStrategy = builder.fieldNamingPolicy;
        this.instanceCreators = new HashMap(builder.instanceCreators);
        this.serializeNulls = builder.serializeNulls;
        this.complexMapKeySerialization = builder.complexMapKeySerialization;
        this.generateNonExecutableJson = builder.generateNonExecutableJson;
        this.htmlSafe = builder.escapeHtmlChars;
        this.formattingStyle = builder.formattingStyle;
        this.strictness = builder.strictness;
        this.serializeSpecialFloatingPointValues = builder.serializeSpecialFloatingPointValues;
        this.useJdkUnsafe = builder.useJdkUnsafe;
        this.longSerializationPolicy = builder.longSerializationPolicy;
        this.datePattern = builder.datePattern;
        this.dateStyle = builder.dateStyle;
        this.timeStyle = builder.timeStyle;
        this.builderFactories = GsonBuilder.newImmutableList(builder.factories);
        this.builderHierarchyFactories = GsonBuilder.newImmutableList(builder.hierarchyFactories);
        this.objectToNumberStrategy = builder.objectToNumberStrategy;
        this.numberToNumberStrategy = builder.numberToNumberStrategy;
        this.reflectionFilters = GsonBuilder.newImmutableList(builder.reflectionFilters);
        if (builder == GsonBuilder.DEFAULT) {
            this.constructorConstructor = GsonBuilder.DEFAULT_CONSTRUCTOR_CONSTRUCTOR;
            this.jsonAdapterFactory = GsonBuilder.DEFAULT_JSON_ADAPTER_ANNOTATION_TYPE_ADAPTER_FACTORY;
            this.factories = GsonBuilder.DEFAULT_TYPE_ADAPTER_FACTORIES;
        } else {
            this.constructorConstructor = new ConstructorConstructor(this.instanceCreators, this.useJdkUnsafe, this.reflectionFilters);
            this.jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor);
            this.factories = builder.createFactories(this.constructorConstructor, this.jsonAdapterFactory);
        }
    }

    public GsonBuilder newBuilder() {
        return new GsonBuilder(this);
    }

    @Deprecated
    public Excluder excluder() {
        return this.excluder;
    }

    public FieldNamingStrategy fieldNamingStrategy() {
        return this.fieldNamingStrategy;
    }

    public boolean serializeNulls() {
        return this.serializeNulls;
    }

    public boolean htmlSafe() {
        return this.htmlSafe;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0054, code lost:
    
        r4.setDelegate(r3);
        r1.put(r9, r3);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <T> com.google.gson.TypeAdapter<T> getAdapter(com.google.gson.reflect.TypeToken<T> r9) {
        /*
            r8 = this;
            java.lang.String r0 = "type must not be null"
            java.util.Objects.requireNonNull(r9, r0)
            java.util.concurrent.ConcurrentMap<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>> r0 = r8.typeTokenCache
            java.lang.Object r0 = r0.get(r9)
            com.google.gson.TypeAdapter r0 = (com.google.gson.TypeAdapter) r0
            if (r0 == 0) goto L11
            r1 = r0
            return r1
        L11:
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>>> r1 = r8.threadLocalAdapterResults
            java.lang.Object r1 = r1.get()
            java.util.Map r1 = (java.util.Map) r1
            r2 = 0
            if (r1 != 0) goto L29
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            r1 = r3
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>>> r3 = r8.threadLocalAdapterResults
            r3.set(r1)
            r2 = 1
            goto L32
        L29:
            java.lang.Object r3 = r1.get(r9)
            com.google.gson.TypeAdapter r3 = (com.google.gson.TypeAdapter) r3
            if (r3 == 0) goto L32
            return r3
        L32:
            r3 = 0
            com.google.gson.Gson$FutureTypeAdapter r4 = new com.google.gson.Gson$FutureTypeAdapter     // Catch: java.lang.Throwable -> L86
            r4.<init>()     // Catch: java.lang.Throwable -> L86
            r1.put(r9, r4)     // Catch: java.lang.Throwable -> L86
            java.util.List<com.google.gson.TypeAdapterFactory> r5 = r8.factories     // Catch: java.lang.Throwable -> L86
            java.util.Iterator r5 = r5.iterator()     // Catch: java.lang.Throwable -> L86
        L41:
            boolean r6 = r5.hasNext()     // Catch: java.lang.Throwable -> L86
            if (r6 == 0) goto L5c
            java.lang.Object r6 = r5.next()     // Catch: java.lang.Throwable -> L86
            com.google.gson.TypeAdapterFactory r6 = (com.google.gson.TypeAdapterFactory) r6     // Catch: java.lang.Throwable -> L86
            com.google.gson.TypeAdapter r7 = r6.create(r8, r9)     // Catch: java.lang.Throwable -> L86
            r3 = r7
            if (r3 == 0) goto L5b
            r4.setDelegate(r3)     // Catch: java.lang.Throwable -> L86
            r1.put(r9, r3)     // Catch: java.lang.Throwable -> L86
            goto L5c
        L5b:
            goto L41
        L5c:
            if (r2 == 0) goto L63
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>>> r4 = r8.threadLocalAdapterResults
            r4.remove()
        L63:
            if (r3 == 0) goto L6d
            if (r2 == 0) goto L6c
            java.util.concurrent.ConcurrentMap<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>> r4 = r8.typeTokenCache
            r4.putAll(r1)
        L6c:
            return r3
        L6d:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "GSON (2.14.0) cannot handle "
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.StringBuilder r5 = r5.append(r9)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L86:
            r4 = move-exception
            if (r2 == 0) goto L8e
            java.lang.ThreadLocal<java.util.Map<com.google.gson.reflect.TypeToken<?>, com.google.gson.TypeAdapter<?>>> r5 = r8.threadLocalAdapterResults
            r5.remove()
        L8e:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.Gson.getAdapter(com.google.gson.reflect.TypeToken):com.google.gson.TypeAdapter");
    }

    public <T> TypeAdapter<T> getAdapter(Class<T> type) {
        return getAdapter(TypeToken.get((Class) type));
    }

    public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory skipPast, TypeToken<T> type) {
        Objects.requireNonNull(skipPast, "skipPast must not be null");
        Objects.requireNonNull(type, "type must not be null");
        if (this.jsonAdapterFactory.isClassJsonAdapterFactory(type, skipPast)) {
            skipPast = this.jsonAdapterFactory;
        }
        boolean skipPastFound = false;
        for (TypeAdapterFactory factory : this.factories) {
            if (!skipPastFound) {
                if (factory == skipPast) {
                    skipPastFound = true;
                }
            } else {
                TypeAdapter<T> candidate = factory.create(this, type);
                if (candidate != null) {
                    return candidate;
                }
            }
        }
        if (skipPastFound) {
            throw new IllegalArgumentException("GSON cannot serialize or deserialize " + type);
        }
        return getAdapter(type);
    }

    public JsonElement toJsonTree(Object src) {
        if (src == null) {
            return JsonNull.INSTANCE;
        }
        return toJsonTree(src, src.getClass());
    }

    public JsonElement toJsonTree(Object src, Type typeOfSrc) {
        JsonTreeWriter writer = new JsonTreeWriter();
        toJson(src, typeOfSrc, writer);
        return writer.get();
    }

    public String toJson(Object src) {
        if (src == null) {
            return toJson((JsonElement) JsonNull.INSTANCE);
        }
        return toJson(src, src.getClass());
    }

    public String toJson(Object src, Type typeOfSrc) {
        StringBuilder writer = new StringBuilder();
        toJson(src, typeOfSrc, writer);
        return writer.toString();
    }

    public void toJson(Object src, Appendable writer) throws JsonIOException {
        if (src != null) {
            toJson(src, src.getClass(), writer);
        } else {
            toJson((JsonElement) JsonNull.INSTANCE, writer);
        }
    }

    public void toJson(Object src, Type typeOfSrc, Appendable writer) throws JsonIOException {
        try {
            JsonWriter jsonWriter = newJsonWriter(Streams.writerForAppendable(writer));
            toJson(src, typeOfSrc, jsonWriter);
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public void toJson(Object src, Type typeOfSrc, JsonWriter writer) throws JsonIOException {
        TypeAdapter<Object> adapter = getAdapter(TypeToken.get(typeOfSrc));
        Strictness oldStrictness = writer.getStrictness();
        if (this.strictness != null) {
            writer.setStrictness(this.strictness);
        } else if (writer.getStrictness() == Strictness.LEGACY_STRICT) {
            writer.setStrictness(Strictness.LENIENT);
        }
        boolean oldHtmlSafe = writer.isHtmlSafe();
        boolean oldSerializeNulls = writer.getSerializeNulls();
        writer.setHtmlSafe(this.htmlSafe);
        writer.setSerializeNulls(this.serializeNulls);
        try {
            try {
                try {
                    adapter.write(writer, src);
                } catch (AssertionError e) {
                    throw new AssertionError("AssertionError (GSON 2.14.0): " + e.getMessage(), e);
                }
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        } finally {
            writer.setStrictness(oldStrictness);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
        }
    }

    public String toJson(JsonElement jsonElement) {
        StringBuilder writer = new StringBuilder();
        toJson(jsonElement, (Appendable) writer);
        return writer.toString();
    }

    public void toJson(JsonElement jsonElement, Appendable writer) throws JsonIOException {
        try {
            JsonWriter jsonWriter = newJsonWriter(Streams.writerForAppendable(writer));
            toJson(jsonElement, jsonWriter);
        } catch (IOException e) {
            throw new JsonIOException(e);
        }
    }

    public void toJson(JsonElement jsonElement, JsonWriter writer) throws JsonIOException {
        Strictness oldStrictness = writer.getStrictness();
        boolean oldHtmlSafe = writer.isHtmlSafe();
        boolean oldSerializeNulls = writer.getSerializeNulls();
        writer.setHtmlSafe(this.htmlSafe);
        writer.setSerializeNulls(this.serializeNulls);
        if (this.strictness != null) {
            writer.setStrictness(this.strictness);
        } else if (writer.getStrictness() == Strictness.LEGACY_STRICT) {
            writer.setStrictness(Strictness.LENIENT);
        }
        try {
            try {
                Streams.write(jsonElement, writer);
            } catch (IOException e) {
                throw new JsonIOException(e);
            } catch (AssertionError e2) {
                throw new AssertionError("AssertionError (GSON 2.14.0): " + e2.getMessage(), e2);
            }
        } finally {
            writer.setStrictness(oldStrictness);
            writer.setHtmlSafe(oldHtmlSafe);
            writer.setSerializeNulls(oldSerializeNulls);
        }
    }

    public JsonWriter newJsonWriter(Writer writer) throws IOException {
        if (this.generateNonExecutableJson) {
            writer.write(JSON_NON_EXECUTABLE_PREFIX);
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.setFormattingStyle(this.formattingStyle);
        jsonWriter.setHtmlSafe(this.htmlSafe);
        jsonWriter.setStrictness(this.strictness == null ? Strictness.LEGACY_STRICT : this.strictness);
        jsonWriter.setSerializeNulls(this.serializeNulls);
        return jsonWriter;
    }

    public JsonReader newJsonReader(Reader reader) {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setStrictness(this.strictness == null ? Strictness.LEGACY_STRICT : this.strictness);
        return jsonReader;
    }

    public <T> T fromJson(String str, Class<T> cls) throws JsonSyntaxException {
        return (T) fromJson(str, TypeToken.get((Class) cls));
    }

    public <T> T fromJson(String str, Type type) throws JsonSyntaxException {
        return (T) fromJson(str, TypeToken.get(type));
    }

    public <T> T fromJson(String str, TypeToken<T> typeToken) throws JsonSyntaxException {
        if (str == null) {
            return null;
        }
        return (T) fromJson(new StringReader(str), typeToken);
    }

    public <T> T fromJson(Reader reader, Class<T> cls) throws JsonSyntaxException, JsonIOException {
        return (T) fromJson(reader, TypeToken.get((Class) cls));
    }

    public <T> T fromJson(Reader reader, Type type) throws JsonSyntaxException, JsonIOException {
        return (T) fromJson(reader, TypeToken.get(type));
    }

    public <T> T fromJson(Reader reader, TypeToken<T> typeToken) throws JsonSyntaxException, JsonIOException {
        JsonReader jsonReaderNewJsonReader = newJsonReader(reader);
        T t = (T) fromJson(jsonReaderNewJsonReader, typeToken);
        assertFullConsumption(t, jsonReaderNewJsonReader);
        return t;
    }

    public <T> T fromJson(JsonReader jsonReader, Type type) throws JsonSyntaxException, JsonIOException {
        return (T) fromJson(jsonReader, TypeToken.get(type));
    }

    public <T> T fromJson(JsonReader reader, TypeToken<T> typeOfT) throws JsonSyntaxException, JsonIOException {
        boolean isEmpty = true;
        Strictness oldStrictness = reader.getStrictness();
        if (this.strictness != null) {
            reader.setStrictness(this.strictness);
        } else if (reader.getStrictness() == Strictness.LEGACY_STRICT) {
            reader.setStrictness(Strictness.LENIENT);
        }
        try {
            try {
                try {
                    reader.peek();
                    isEmpty = false;
                    TypeAdapter<T> typeAdapter = getAdapter(typeOfT);
                    T object = typeAdapter.read(reader);
                    Class<?> expectedTypeWrapped = Primitives.wrap(typeOfT.getRawType());
                    if (object != null && !expectedTypeWrapped.isInstance(object)) {
                        throw new ClassCastException("Type adapter '" + typeAdapter + "' returned wrong type; requested " + typeOfT.getRawType() + " but got instance of " + object.getClass() + "\nVerify that the adapter was registered for the correct type.");
                    }
                    return object;
                } catch (EOFException e) {
                    if (!isEmpty) {
                        throw new JsonSyntaxException(e);
                    }
                    reader.setStrictness(oldStrictness);
                    return null;
                } catch (AssertionError e2) {
                    throw new AssertionError("AssertionError (GSON 2.14.0): " + e2.getMessage(), e2);
                }
            } catch (IOException e3) {
                throw new JsonSyntaxException(e3);
            } catch (IllegalStateException e4) {
                throw new JsonSyntaxException(e4);
            }
        } finally {
            reader.setStrictness(oldStrictness);
        }
    }

    public <T> T fromJson(JsonElement jsonElement, Class<T> cls) throws JsonSyntaxException {
        return (T) fromJson(jsonElement, TypeToken.get((Class) cls));
    }

    public <T> T fromJson(JsonElement jsonElement, Type type) throws JsonSyntaxException {
        return (T) fromJson(jsonElement, TypeToken.get(type));
    }

    public <T> T fromJson(JsonElement jsonElement, TypeToken<T> typeToken) throws JsonSyntaxException {
        if (jsonElement == null) {
            return null;
        }
        return (T) fromJson(new JsonTreeReader(jsonElement), typeToken);
    }

    private static void assertFullConsumption(Object obj, JsonReader reader) {
        if (obj != null) {
            try {
                if (reader.peek() != JsonToken.END_DOCUMENT) {
                    throw new JsonSyntaxException("JSON document was not fully consumed.");
                }
            } catch (MalformedJsonException e) {
                throw new JsonSyntaxException(e);
            } catch (IOException e2) {
                throw new JsonIOException(e2);
            }
        }
    }

    static class FutureTypeAdapter<T> extends SerializationDelegatingTypeAdapter<T> {
        private TypeAdapter<T> delegate = null;

        FutureTypeAdapter() {
        }

        public void setDelegate(TypeAdapter<T> typeAdapter) {
            if (this.delegate != null) {
                throw new AssertionError("Delegate is already set");
            }
            this.delegate = typeAdapter;
        }

        private TypeAdapter<T> delegate() {
            TypeAdapter<T> delegate = this.delegate;
            if (delegate == null) {
                throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
            }
            return delegate;
        }

        @Override // com.google.gson.internal.bind.SerializationDelegatingTypeAdapter
        public TypeAdapter<T> getSerializationDelegate() {
            return delegate();
        }

        @Override // com.google.gson.TypeAdapter
        public T read(JsonReader in) throws IOException {
            return delegate().read(in);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, T value) throws IOException {
            delegate().write(out, value);
        }
    }

    public String toString() {
        return "{serializeNulls:" + this.serializeNulls + ",factories:" + this.factories + ",instanceCreators:" + this.constructorConstructor + "}";
    }
}
