package com.google.gson.internal.bind;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.LazilyParsedNumber;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes13.dex */
public class JsonElementTypeAdapter extends TypeAdapter<JsonElement> {
    public static final JsonElementTypeAdapter ADAPTER = new JsonElementTypeAdapter();

    private JsonElementTypeAdapter() {
    }

    private JsonElement tryBeginNesting(JsonReader in, JsonToken peeked) throws IOException {
        switch (peeked) {
            case BEGIN_ARRAY:
                in.beginArray();
                return new JsonArray();
            case BEGIN_OBJECT:
                in.beginObject();
                return new JsonObject();
            default:
                return null;
        }
    }

    private JsonElement readTerminal(JsonReader in, JsonToken peeked) throws IOException {
        switch (peeked) {
            case STRING:
                return new JsonPrimitive(in.nextString());
            case NUMBER:
                String number = in.nextString();
                return new JsonPrimitive(new LazilyParsedNumber(number));
            case BOOLEAN:
                return new JsonPrimitive(Boolean.valueOf(in.nextBoolean()));
            case NULL:
                in.nextNull();
                return JsonNull.INSTANCE;
            default:
                throw new IllegalStateException("Unexpected token: " + peeked);
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    public JsonElement read(JsonReader in) throws IOException {
        if (in instanceof JsonTreeReader) {
            return ((JsonTreeReader) in).nextJsonElement();
        }
        JsonToken peeked = in.peek();
        JsonElement current = tryBeginNesting(in, peeked);
        if (current == null) {
            return readTerminal(in, peeked);
        }
        Deque<JsonElement> stack = new ArrayDeque<>();
        while (true) {
            if (in.hasNext()) {
                String name = null;
                if (current instanceof JsonObject) {
                    name = in.nextName();
                }
                JsonToken peeked2 = in.peek();
                JsonElement value = tryBeginNesting(in, peeked2);
                boolean isNesting = value != null;
                if (value == null) {
                    value = readTerminal(in, peeked2);
                }
                if (current instanceof JsonArray) {
                    ((JsonArray) current).add(value);
                } else {
                    ((JsonObject) current).add(name, value);
                }
                if (isNesting) {
                    stack.addLast(current);
                    current = value;
                }
            } else {
                if (current instanceof JsonArray) {
                    in.endArray();
                } else {
                    in.endObject();
                }
                if (stack.isEmpty()) {
                    return current;
                }
                current = stack.removeLast();
            }
        }
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter out, JsonElement value) throws IOException {
        if (value == null || value.isJsonNull()) {
            out.nullValue();
            return;
        }
        if (value.isJsonPrimitive()) {
            JsonPrimitive primitive = value.getAsJsonPrimitive();
            if (primitive.isNumber()) {
                out.value(primitive.getAsNumber());
                return;
            } else if (primitive.isBoolean()) {
                out.value(primitive.getAsBoolean());
                return;
            } else {
                out.value(primitive.getAsString());
                return;
            }
        }
        if (value.isJsonArray()) {
            out.beginArray();
            Iterator<JsonElement> it = value.getAsJsonArray().iterator();
            while (it.hasNext()) {
                write(out, it.next());
            }
            out.endArray();
            return;
        }
        if (value.isJsonObject()) {
            out.beginObject();
            for (Map.Entry<String, JsonElement> e : value.getAsJsonObject().entrySet()) {
                out.name(e.getKey());
                write(out, e.getValue());
            }
            out.endObject();
            return;
        }
        throw new IllegalArgumentException("Couldn't write " + value.getClass());
    }
}
