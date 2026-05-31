package com.google.gson;

import com.google.gson.internal.bind.TypeAdapters;

/* JADX INFO: loaded from: classes13.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: com.google.gson.LongSerializationPolicy.1
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long value) {
            if (value == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(value);
        }

        @Override // com.google.gson.LongSerializationPolicy
        TypeAdapter<Number> typeAdapter() {
            return TypeAdapters.LONG;
        }
    },
    STRING { // from class: com.google.gson.LongSerializationPolicy.2
        @Override // com.google.gson.LongSerializationPolicy
        public JsonElement serialize(Long value) {
            if (value == null) {
                return JsonNull.INSTANCE;
            }
            return new JsonPrimitive(value.toString());
        }

        @Override // com.google.gson.LongSerializationPolicy
        TypeAdapter<Number> typeAdapter() {
            return TypeAdapters.LONG_AS_STRING;
        }
    };

    public abstract JsonElement serialize(Long l);

    abstract TypeAdapter<Number> typeAdapter();
}
