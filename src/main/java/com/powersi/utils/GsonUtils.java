package com.powersi.utils;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonUtils {
    private static final Gson gson = new GsonBuilder().enableComplexMapKeySerialization().disableHtmlEscaping().create();

    private GsonUtils() {
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static String toJson(Object obj, Type typeOfT) {
        return gson.toJson(obj, typeOfT);
    }
}