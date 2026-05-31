package com.google.crypto.tink.internal;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes13.dex */
public final class JsonParser {
    private static final JsonElementTypeAdapter JSON_ELEMENT = new JsonElementTypeAdapter();

    public static boolean isValidString(String s) {
        int length = s.length();
        int i = 0;
        while (i != length) {
            char ch = s.charAt(i);
            i++;
            if (Character.isSurrogate(ch)) {
                if (Character.isLowSurrogate(ch) || i == length || !Character.isLowSurrogate(s.charAt(i))) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }

    private static final class LazilyParsedNumber extends Number {
        private final String value;

        public LazilyParsedNumber(String value) {
            this.value = value;
        }

        @Override // java.lang.Number
        public int intValue() {
            try {
                return Integer.parseInt(this.value);
            } catch (NumberFormatException e) {
                try {
                    return (int) Long.parseLong(this.value);
                } catch (NumberFormatException e2) {
                    return new BigDecimal(this.value).intValue();
                }
            }
        }

        @Override // java.lang.Number
        public long longValue() {
            try {
                return Long.parseLong(this.value);
            } catch (NumberFormatException e) {
                return new BigDecimal(this.value).longValue();
            }
        }

        @Override // java.lang.Number
        public float floatValue() {
            return Float.parseFloat(this.value);
        }

        @Override // java.lang.Number
        public double doubleValue() {
            return Double.parseDouble(this.value);
        }

        public String toString() {
            return this.value;
        }

        private Object writeReplace() throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        private void readObject(ObjectInputStream in) throws NotSerializableException {
            throw new NotSerializableException("serialization is not supported");
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof LazilyParsedNumber) {
                LazilyParsedNumber other = (LazilyParsedNumber) obj;
                return this.value.equals(other.value);
            }
            return false;
        }
    }

    private static final class JsonElementTypeAdapter extends TypeAdapter<JsonElement> {
        private static final int RECURSION_LIMIT = 100;

        private JsonElementTypeAdapter() {
        }

        @Nullable
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
                    String value = in.nextString();
                    if (!JsonParser.isValidString(value)) {
                        throw new IOException("illegal characters in string");
                    }
                    return new JsonPrimitive(value);
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
                        if (!JsonParser.isValidString(name)) {
                            throw new IOException("illegal characters in string");
                        }
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
                        if (((JsonObject) current).has(name)) {
                            throw new IOException("duplicate key: " + name);
                        }
                        ((JsonObject) current).add(name, value);
                    }
                    if (isNesting) {
                        stack.addLast(current);
                        if (stack.size() > 100) {
                            throw new IOException("too many recursions");
                        }
                        current = value;
                    } else {
                        continue;
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
        public void write(JsonWriter out, JsonElement value) {
            throw new UnsupportedOperationException("write is not supported");
        }
    }

    public static JsonElement parse(String json) throws IOException {
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(json));
            jsonReader.setLenient(false);
            return JSON_ELEMENT.read(jsonReader);
        } catch (NumberFormatException e) {
            throw new IOException(e);
        }
    }

    public static long getParsedNumberAsLongOrThrow(JsonElement element) {
        Number num = element.getAsNumber();
        if (!(num instanceof LazilyParsedNumber)) {
            throw new IllegalArgumentException("does not contain a parsed number.");
        }
        return Long.parseLong(element.getAsNumber().toString());
    }

    private JsonParser() {
    }
}
