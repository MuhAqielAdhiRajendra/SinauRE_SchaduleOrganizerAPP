package com.google.gson;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public interface FieldNamingStrategy {
    String translateName(Field field);

    default List<String> alternateNames(Field f) {
        return Collections.emptyList();
    }
}
