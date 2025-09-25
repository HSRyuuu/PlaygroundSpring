package com.hsryuuu.base.java.mock.generator;


import com.hsryuuu.base.java.mock.util.MockContext;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ListValueGenerator implements MockValueGenerator {

    private final List<MockValueGenerator> generators;
    private final int listSize;
    private static final int DEFAULT_LIST_SIZE = 2;

    public ListValueGenerator(List<MockValueGenerator> generators) {
        this(generators, DEFAULT_LIST_SIZE);
    }

    public ListValueGenerator(List<MockValueGenerator> generators, int listSize) {
        this.generators = generators;
        this.listSize = listSize;
    }

    @Override
    public boolean supports(Class<?> rawType, Type genericType) {
        return List.class.isAssignableFrom(rawType) && genericType instanceof ParameterizedType;
    }

    @Override
    public Object generate(Class<?> rawType, Type genericType, MockContext context) {
        if (!(genericType instanceof ParameterizedType pt)) return List.of();

        Type elementType = pt.getActualTypeArguments()[0];
        if (!(elementType instanceof Class<?> elementClass)) return List.of();

        List<Object> list = new ArrayList<>();
        for (int i = 0; i < listSize; i++) {
            MockValueGenerator generator = this.findGenerator(elementClass, elementType);
            list.add(generator.generate(elementClass, elementType, context));
        }
        return list;
    }

    private MockValueGenerator findGenerator(Class<?> rawType, Type genericType) {
        return generators.stream()
                .filter(g -> g.supports(rawType, genericType))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("No generator found for " + rawType));
    }
}