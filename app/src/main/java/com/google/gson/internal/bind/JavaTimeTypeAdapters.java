package com.google.gson.internal.bind;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/* JADX INFO: loaded from: classes13.dex */
final class JavaTimeTypeAdapters implements TypeAdapters.FactorySupplier {
    private static final TypeAdapter<Duration> DURATION = new TypeAdapters.IntegerFieldsTypeAdapter<Duration>("seconds", "nanos") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Duration create(long[] values) {
            return Duration.ofSeconds(values[0], values[1]);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(Duration duration) {
            return new long[]{duration.getSeconds(), duration.getNano()};
        }
    };
    private static final TypeAdapter<Instant> INSTANT = new TypeAdapters.IntegerFieldsTypeAdapter<Instant>("seconds", "nanos") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.2
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Instant create(long[] values) {
            return Instant.ofEpochSecond(values[0], values[1]);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(Instant instant) {
            return new long[]{instant.getEpochSecond(), instant.getNano()};
        }
    };
    private static final TypeAdapter<LocalDate> LOCAL_DATE = new TypeAdapters.IntegerFieldsTypeAdapter<LocalDate>("year", "month", "day") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.3
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public LocalDate create(long[] values) {
            return LocalDate.of(Math.toIntExact(values[0]), Math.toIntExact(values[1]), Math.toIntExact(values[2]));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(LocalDate localDate) {
            return new long[]{localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth()};
        }
    };
    public static final TypeAdapter<LocalTime> LOCAL_TIME = new TypeAdapters.IntegerFieldsTypeAdapter<LocalTime>("hour", "minute", "second", "nano") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.4
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public LocalTime create(long[] values) {
            return LocalTime.of(Math.toIntExact(values[0]), Math.toIntExact(values[1]), Math.toIntExact(values[2]), Math.toIntExact(values[3]));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(LocalTime localTime) {
            return new long[]{localTime.getHour(), localTime.getMinute(), localTime.getSecond(), localTime.getNano()};
        }
    };
    private static final TypeAdapter<MonthDay> MONTH_DAY = new TypeAdapters.IntegerFieldsTypeAdapter<MonthDay>("month", "day") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.6
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public MonthDay create(long[] values) {
            return MonthDay.of(Math.toIntExact(values[0]), Math.toIntExact(values[1]));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(MonthDay monthDay) {
            return new long[]{monthDay.getMonthValue(), monthDay.getDayOfMonth()};
        }
    };
    private static final TypeAdapter<Period> PERIOD = new TypeAdapters.IntegerFieldsTypeAdapter<Period>("years", "months", "days") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.9
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Period create(long[] values) {
            return Period.of(Math.toIntExact(values[0]), Math.toIntExact(values[1]), Math.toIntExact(values[2]));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(Period period) {
            return new long[]{period.getYears(), period.getMonths(), period.getDays()};
        }
    };
    private static final TypeAdapter<Year> YEAR = new TypeAdapters.IntegerFieldsTypeAdapter<Year>("year") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.10
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public Year create(long[] values) {
            return Year.of(Math.toIntExact(values[0]));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(Year year) {
            return new long[]{year.getValue()};
        }
    };
    private static final TypeAdapter<YearMonth> YEAR_MONTH = new TypeAdapters.IntegerFieldsTypeAdapter<YearMonth>("year", "month") { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.11
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public YearMonth create(long[] values) {
            return YearMonth.of(Math.toIntExact(values[0]), Math.toIntExact(values[1]));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.gson.internal.bind.TypeAdapters.IntegerFieldsTypeAdapter
        public long[] integerValues(YearMonth yearMonth) {
            return new long[]{yearMonth.getYear(), yearMonth.getMonthValue()};
        }
    };
    private static final TypeAdapter<ZoneId> ZONE_ID = new TypeAdapter<ZoneId>() { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.12
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
        @Override // com.google.gson.TypeAdapter
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.time.ZoneId read(com.google.gson.stream.JsonReader r6) throws java.io.IOException {
            /*
                r5 = this;
                r6.beginObject()
                r0 = 0
                r1 = 0
            L5:
                com.google.gson.stream.JsonToken r2 = r6.peek()
                com.google.gson.stream.JsonToken r3 = com.google.gson.stream.JsonToken.END_OBJECT
                if (r2 == r3) goto L44
                java.lang.String r2 = r6.nextName()
                int r3 = r2.hashCode()
                switch(r3) {
                    case -1769827685: goto L23;
                    case 3355: goto L19;
                    default: goto L18;
                }
            L18:
                goto L2d
            L19:
                java.lang.String r3 = "id"
                boolean r3 = r2.equals(r3)
                if (r3 == 0) goto L18
                r3 = 0
                goto L2e
            L23:
                java.lang.String r3 = "totalSeconds"
                boolean r3 = r2.equals(r3)
                if (r3 == 0) goto L18
                r3 = 1
                goto L2e
            L2d:
                r3 = -1
            L2e:
                switch(r3) {
                    case 0: goto L3e;
                    case 1: goto L35;
                    default: goto L31;
                }
            L31:
                r6.skipValue()
                goto L43
            L35:
                int r3 = r6.nextInt()
                java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
                goto L43
            L3e:
                java.lang.String r0 = r6.nextString()
            L43:
                goto L5
            L44:
                r6.endObject()
                if (r0 == 0) goto L4e
                java.time.ZoneId r2 = java.time.ZoneId.of(r0)
                return r2
            L4e:
                if (r1 == 0) goto L59
                int r2 = r1.intValue()
                java.time.ZoneOffset r2 = java.time.ZoneOffset.ofTotalSeconds(r2)
                return r2
            L59:
                com.google.gson.JsonSyntaxException r2 = new com.google.gson.JsonSyntaxException
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Missing id or totalSeconds field; at path "
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r4 = r6.getPreviousPath()
                java.lang.StringBuilder r3 = r3.append(r4)
                java.lang.String r3 = r3.toString()
                r2.<init>(r3)
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JavaTimeTypeAdapters.AnonymousClass12.read(com.google.gson.stream.JsonReader):java.time.ZoneId");
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter out, ZoneId value) throws IOException {
            if (value instanceof ZoneOffset) {
                out.beginObject();
                out.name("totalSeconds");
                out.value(((ZoneOffset) value).getTotalSeconds());
                out.endObject();
                return;
            }
            out.beginObject();
            out.name("id");
            out.value(value.getId());
            out.endObject();
        }
    }.nullSafe();
    static final TypeAdapterFactory JAVA_TIME_FACTORY = new TypeAdapterFactory() { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.14
        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Class<? super T> rawType = typeToken.getRawType();
            if (!rawType.getName().startsWith("java.time.")) {
                return null;
            }
            Object objZonedDateTime = null;
            if (rawType == Duration.class) {
                objZonedDateTime = JavaTimeTypeAdapters.DURATION;
            } else if (rawType == Instant.class) {
                objZonedDateTime = JavaTimeTypeAdapters.INSTANT;
            } else if (rawType == LocalDate.class) {
                objZonedDateTime = JavaTimeTypeAdapters.LOCAL_DATE;
            } else if (rawType == LocalTime.class) {
                objZonedDateTime = JavaTimeTypeAdapters.LOCAL_TIME;
            } else if (rawType == LocalDateTime.class) {
                objZonedDateTime = JavaTimeTypeAdapters.localDateTime(gson);
            } else if (rawType == MonthDay.class) {
                objZonedDateTime = JavaTimeTypeAdapters.MONTH_DAY;
            } else if (rawType == OffsetDateTime.class) {
                objZonedDateTime = JavaTimeTypeAdapters.offsetDateTime(gson);
            } else if (rawType == OffsetTime.class) {
                objZonedDateTime = JavaTimeTypeAdapters.offsetTime(gson);
            } else if (rawType == Period.class) {
                objZonedDateTime = JavaTimeTypeAdapters.PERIOD;
            } else if (rawType == Year.class) {
                objZonedDateTime = JavaTimeTypeAdapters.YEAR;
            } else if (rawType == YearMonth.class) {
                objZonedDateTime = JavaTimeTypeAdapters.YEAR_MONTH;
            } else if (rawType == ZoneId.class || rawType == ZoneOffset.class) {
                objZonedDateTime = JavaTimeTypeAdapters.ZONE_ID;
            } else if (rawType == ZonedDateTime.class) {
                objZonedDateTime = JavaTimeTypeAdapters.zonedDateTime(gson);
            }
            return (TypeAdapter<T>) objZonedDateTime;
        }
    };

    JavaTimeTypeAdapters() {
    }

    @Override // com.google.gson.internal.bind.TypeAdapters.FactorySupplier
    public TypeAdapterFactory get() {
        return JAVA_TIME_FACTORY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TypeAdapter<LocalDateTime> localDateTime(Gson gson) {
        final TypeAdapter<LocalDate> localDateAdapter = gson.getAdapter(LocalDate.class);
        final TypeAdapter<LocalTime> localTimeAdapter = gson.getAdapter(LocalTime.class);
        return new TypeAdapter<LocalDateTime>() { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.5
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
            @Override // com.google.gson.TypeAdapter
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.time.LocalDateTime read(com.google.gson.stream.JsonReader r7) throws java.io.IOException {
                /*
                    r6 = this;
                    r0 = 0
                    r1 = 0
                    r7.beginObject()
                L5:
                    com.google.gson.stream.JsonToken r2 = r7.peek()
                    com.google.gson.stream.JsonToken r3 = com.google.gson.stream.JsonToken.END_OBJECT
                    java.lang.String r4 = "time"
                    java.lang.String r5 = "date"
                    if (r2 == r3) goto L4a
                    java.lang.String r2 = r7.nextName()
                    int r3 = r2.hashCode()
                    switch(r3) {
                        case 3076014: goto L25;
                        case 3560141: goto L1d;
                        default: goto L1c;
                    }
                L1c:
                    goto L2d
                L1d:
                    boolean r3 = r2.equals(r4)
                    if (r3 == 0) goto L1c
                    r3 = 1
                    goto L2e
                L25:
                    boolean r3 = r2.equals(r5)
                    if (r3 == 0) goto L1c
                    r3 = 0
                    goto L2e
                L2d:
                    r3 = -1
                L2e:
                    switch(r3) {
                        case 0: goto L3f;
                        case 1: goto L35;
                        default: goto L31;
                    }
                L31:
                    r7.skipValue()
                    goto L49
                L35:
                    com.google.gson.TypeAdapter r3 = r2
                    java.lang.Object r3 = r3.read(r7)
                    r1 = r3
                    java.time.LocalTime r1 = (java.time.LocalTime) r1
                    goto L49
                L3f:
                    com.google.gson.TypeAdapter r3 = r1
                    java.lang.Object r3 = r3.read(r7)
                    r0 = r3
                    java.time.LocalDate r0 = (java.time.LocalDate) r0
                L49:
                    goto L5
                L4a:
                    r7.endObject()
                    java.lang.Object r2 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r0, r5, r7)
                    java.time.LocalDate r2 = (java.time.LocalDate) r2
                    java.lang.Object r3 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r1, r4, r7)
                    java.time.LocalTime r3 = (java.time.LocalTime) r3
                    java.time.LocalDateTime r2 = java.time.LocalDateTime.of(r2, r3)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JavaTimeTypeAdapters.AnonymousClass5.read(com.google.gson.stream.JsonReader):java.time.LocalDateTime");
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, LocalDateTime value) throws IOException {
                out.beginObject();
                out.name("date");
                localDateAdapter.write(out, value.toLocalDate());
                out.name("time");
                localTimeAdapter.write(out, value.toLocalTime());
                out.endObject();
            }
        }.nullSafe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TypeAdapter<OffsetDateTime> offsetDateTime(Gson gson) {
        final TypeAdapter<LocalDateTime> localDateTimeAdapter = localDateTime(gson);
        final TypeAdapter<ZoneOffset> zoneOffsetAdapter = gson.getAdapter(ZoneOffset.class);
        return new TypeAdapter<OffsetDateTime>() { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.7
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
            @Override // com.google.gson.TypeAdapter
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.time.OffsetDateTime read(com.google.gson.stream.JsonReader r7) throws java.io.IOException {
                /*
                    r6 = this;
                    r7.beginObject()
                    r0 = 0
                    r1 = 0
                L5:
                    com.google.gson.stream.JsonToken r2 = r7.peek()
                    com.google.gson.stream.JsonToken r3 = com.google.gson.stream.JsonToken.END_OBJECT
                    java.lang.String r4 = "offset"
                    java.lang.String r5 = "dateTime"
                    if (r2 == r3) goto L4a
                    java.lang.String r2 = r7.nextName()
                    int r3 = r2.hashCode()
                    switch(r3) {
                        case -1019779949: goto L25;
                        case 1792749467: goto L1d;
                        default: goto L1c;
                    }
                L1c:
                    goto L2d
                L1d:
                    boolean r3 = r2.equals(r5)
                    if (r3 == 0) goto L1c
                    r3 = 0
                    goto L2e
                L25:
                    boolean r3 = r2.equals(r4)
                    if (r3 == 0) goto L1c
                    r3 = 1
                    goto L2e
                L2d:
                    r3 = -1
                L2e:
                    switch(r3) {
                        case 0: goto L3f;
                        case 1: goto L35;
                        default: goto L31;
                    }
                L31:
                    r7.skipValue()
                    goto L49
                L35:
                    com.google.gson.TypeAdapter r3 = r2
                    java.lang.Object r3 = r3.read(r7)
                    r1 = r3
                    java.time.ZoneOffset r1 = (java.time.ZoneOffset) r1
                    goto L49
                L3f:
                    com.google.gson.TypeAdapter r3 = r1
                    java.lang.Object r3 = r3.read(r7)
                    r0 = r3
                    java.time.LocalDateTime r0 = (java.time.LocalDateTime) r0
                L49:
                    goto L5
                L4a:
                    r7.endObject()
                    java.lang.Object r2 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r0, r5, r7)
                    java.time.LocalDateTime r2 = (java.time.LocalDateTime) r2
                    java.lang.Object r3 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r1, r4, r7)
                    java.time.ZoneOffset r3 = (java.time.ZoneOffset) r3
                    java.time.OffsetDateTime r2 = java.time.OffsetDateTime.of(r2, r3)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JavaTimeTypeAdapters.AnonymousClass7.read(com.google.gson.stream.JsonReader):java.time.OffsetDateTime");
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, OffsetDateTime value) throws IOException {
                out.beginObject();
                out.name("dateTime");
                localDateTimeAdapter.write(out, value.toLocalDateTime());
                out.name(TypedValues.CycleType.S_WAVE_OFFSET);
                zoneOffsetAdapter.write(out, value.getOffset());
                out.endObject();
            }
        }.nullSafe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TypeAdapter<OffsetTime> offsetTime(Gson gson) {
        final TypeAdapter<LocalTime> localTimeAdapter = gson.getAdapter(LocalTime.class);
        final TypeAdapter<ZoneOffset> zoneOffsetAdapter = gson.getAdapter(ZoneOffset.class);
        return new TypeAdapter<OffsetTime>() { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.8
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:14:0x002d  */
            @Override // com.google.gson.TypeAdapter
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.time.OffsetTime read(com.google.gson.stream.JsonReader r7) throws java.io.IOException {
                /*
                    r6 = this;
                    r7.beginObject()
                    r0 = 0
                    r1 = 0
                L5:
                    com.google.gson.stream.JsonToken r2 = r7.peek()
                    com.google.gson.stream.JsonToken r3 = com.google.gson.stream.JsonToken.END_OBJECT
                    java.lang.String r4 = "offset"
                    java.lang.String r5 = "time"
                    if (r2 == r3) goto L4a
                    java.lang.String r2 = r7.nextName()
                    int r3 = r2.hashCode()
                    switch(r3) {
                        case -1019779949: goto L25;
                        case 3560141: goto L1d;
                        default: goto L1c;
                    }
                L1c:
                    goto L2d
                L1d:
                    boolean r3 = r2.equals(r5)
                    if (r3 == 0) goto L1c
                    r3 = 0
                    goto L2e
                L25:
                    boolean r3 = r2.equals(r4)
                    if (r3 == 0) goto L1c
                    r3 = 1
                    goto L2e
                L2d:
                    r3 = -1
                L2e:
                    switch(r3) {
                        case 0: goto L3f;
                        case 1: goto L35;
                        default: goto L31;
                    }
                L31:
                    r7.skipValue()
                    goto L49
                L35:
                    com.google.gson.TypeAdapter r3 = r2
                    java.lang.Object r3 = r3.read(r7)
                    r1 = r3
                    java.time.ZoneOffset r1 = (java.time.ZoneOffset) r1
                    goto L49
                L3f:
                    com.google.gson.TypeAdapter r3 = r1
                    java.lang.Object r3 = r3.read(r7)
                    r0 = r3
                    java.time.LocalTime r0 = (java.time.LocalTime) r0
                L49:
                    goto L5
                L4a:
                    r7.endObject()
                    java.lang.Object r2 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r0, r5, r7)
                    java.time.LocalTime r2 = (java.time.LocalTime) r2
                    java.lang.Object r3 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r1, r4, r7)
                    java.time.ZoneOffset r3 = (java.time.ZoneOffset) r3
                    java.time.OffsetTime r2 = java.time.OffsetTime.of(r2, r3)
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JavaTimeTypeAdapters.AnonymousClass8.read(com.google.gson.stream.JsonReader):java.time.OffsetTime");
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, OffsetTime value) throws IOException {
                out.beginObject();
                out.name("time");
                localTimeAdapter.write(out, value.toLocalTime());
                out.name(TypedValues.CycleType.S_WAVE_OFFSET);
                zoneOffsetAdapter.write(out, value.getOffset());
                out.endObject();
            }
        }.nullSafe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TypeAdapter<ZonedDateTime> zonedDateTime(Gson gson) {
        final TypeAdapter<LocalDateTime> localDateTimeAdapter = localDateTime(gson);
        final TypeAdapter<ZoneOffset> zoneOffsetAdapter = gson.getAdapter(ZoneOffset.class);
        final TypeAdapter<ZoneId> zoneIdAdapter = gson.getAdapter(ZoneId.class);
        return new TypeAdapter<ZonedDateTime>() { // from class: com.google.gson.internal.bind.JavaTimeTypeAdapters.13
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0038  */
            @Override // com.google.gson.TypeAdapter
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.time.ZonedDateTime read(com.google.gson.stream.JsonReader r9) throws java.io.IOException {
                /*
                    r8 = this;
                    r9.beginObject()
                    r0 = 0
                    r1 = 0
                    r2 = 0
                L6:
                    com.google.gson.stream.JsonToken r3 = r9.peek()
                    com.google.gson.stream.JsonToken r4 = com.google.gson.stream.JsonToken.END_OBJECT
                    java.lang.String r5 = "zone"
                    java.lang.String r6 = "offset"
                    java.lang.String r7 = "dateTime"
                    if (r3 == r4) goto L5f
                    java.lang.String r3 = r9.nextName()
                    int r4 = r3.hashCode()
                    switch(r4) {
                        case -1019779949: goto L30;
                        case 3744684: goto L28;
                        case 1792749467: goto L20;
                        default: goto L1f;
                    }
                L1f:
                    goto L38
                L20:
                    boolean r4 = r3.equals(r7)
                    if (r4 == 0) goto L1f
                    r4 = 0
                    goto L39
                L28:
                    boolean r4 = r3.equals(r5)
                    if (r4 == 0) goto L1f
                    r4 = 2
                    goto L39
                L30:
                    boolean r4 = r3.equals(r6)
                    if (r4 == 0) goto L1f
                    r4 = 1
                    goto L39
                L38:
                    r4 = -1
                L39:
                    switch(r4) {
                        case 0: goto L54;
                        case 1: goto L4a;
                        case 2: goto L40;
                        default: goto L3c;
                    }
                L3c:
                    r9.skipValue()
                    goto L5e
                L40:
                    com.google.gson.TypeAdapter r4 = r3
                    java.lang.Object r4 = r4.read(r9)
                    r2 = r4
                    java.time.ZoneId r2 = (java.time.ZoneId) r2
                    goto L5e
                L4a:
                    com.google.gson.TypeAdapter r4 = r2
                    java.lang.Object r4 = r4.read(r9)
                    r1 = r4
                    java.time.ZoneOffset r1 = (java.time.ZoneOffset) r1
                    goto L5e
                L54:
                    com.google.gson.TypeAdapter r4 = r1
                    java.lang.Object r4 = r4.read(r9)
                    r0 = r4
                    java.time.LocalDateTime r0 = (java.time.LocalDateTime) r0
                L5e:
                    goto L6
                L5f:
                    r9.endObject()
                    java.lang.Object r3 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r0, r7, r9)
                    java.time.LocalDateTime r3 = (java.time.LocalDateTime) r3
                    java.lang.Object r4 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r1, r6, r9)
                    java.time.ZoneOffset r4 = (java.time.ZoneOffset) r4
                    java.lang.Object r5 = com.google.gson.internal.bind.JavaTimeTypeAdapters.access$000(r2, r5, r9)
                    java.time.ZoneId r5 = (java.time.ZoneId) r5
                    java.time.ZonedDateTime r3 = java.time.ZonedDateTime.ofInstant(r3, r4, r5)
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.bind.JavaTimeTypeAdapters.AnonymousClass13.read(com.google.gson.stream.JsonReader):java.time.ZonedDateTime");
            }

            @Override // com.google.gson.TypeAdapter
            public void write(JsonWriter out, ZonedDateTime value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.beginObject();
                out.name("dateTime");
                localDateTimeAdapter.write(out, value.toLocalDateTime());
                out.name(TypedValues.CycleType.S_WAVE_OFFSET);
                zoneOffsetAdapter.write(out, value.getOffset());
                out.name("zone");
                zoneIdAdapter.write(out, value.getZone());
                out.endObject();
            }
        }.nullSafe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T requireNonNullField(T field, String fieldName, JsonReader reader) {
        if (field == null) {
            throw new JsonSyntaxException("Missing " + fieldName + " field; at path " + reader.getPreviousPath());
        }
        return field;
    }
}
