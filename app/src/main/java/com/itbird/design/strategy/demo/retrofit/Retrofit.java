package com.itbird.design.strategy.demo.retrofit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itbird on 2022/6/28
 */
public class Retrofit {
    private static List<Converter.Factory> converterFactories = new ArrayList<>();
    /** Add converter factory for serialization and deserialization of objects. */
    public static void addConverterFactory(Converter.Factory factory) {
        converterFactories.add(factory);
    }
}
