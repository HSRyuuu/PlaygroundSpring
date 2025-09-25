package com.hsryuuu.base.java.mock.generator;

import com.hsryuuu.base.java.mock.util.MockContext;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

public class ObjectValueGenerator implements MockValueGenerator{

    private final List<MockValueGenerator> generators;

    public ObjectValueGenerator(List<MockValueGenerator> generators) {
        this.generators = generators;
    }

    @Override
    public boolean supports(Class<?> rawType, Type genericType) {
        return true;
    }

    @Override
    public Object generate(Class<?> rawType, Type genericType, MockContext context) {
        if (!context.canGoDeeper()) return null;
        context.enter();

        try {
            Constructor<?> constructor = rawType.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object instance = constructor.newInstance();

            for (Field field : rawType.getDeclaredFields()) {
                field.setAccessible(true);

                Class<?> fieldType = field.getType();
                Type fieldGenericType = field.getGenericType();

                for (MockValueGenerator generator : generators) {
                    if (generator.supports(fieldType, fieldGenericType)) {
                        Object value = generator.generate(fieldType, fieldGenericType, context);
                        field.set(instance, value);
                        break;
                    }
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate: " + rawType, e);
        } finally {
            context.exit();
        }
    }
}
