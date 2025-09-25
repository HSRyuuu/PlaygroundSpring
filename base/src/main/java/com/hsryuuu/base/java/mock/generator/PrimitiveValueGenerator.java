package com.hsryuuu.base.java.mock.generator;

import com.hsryuuu.base.java.mock.util.MockContext;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class PrimitiveValueGenerator implements MockValueGenerator {

    private static final Random RANDOM = new Random();
    private static final Map<Class<?>, Supplier<?>> SUPPLIERS = new HashMap<>();

    static {
        SUPPLIERS.put(String.class, () -> "mock_String_" + RANDOM.nextInt(1000));
        SUPPLIERS.put(int.class, () -> RANDOM.nextInt(100));
        SUPPLIERS.put(Integer.class, () -> RANDOM.nextInt(100));
        SUPPLIERS.put(long.class, () -> RANDOM.nextLong(1000));
        SUPPLIERS.put(Long.class, () -> RANDOM.nextLong(1000));
        SUPPLIERS.put(boolean.class, RANDOM::nextBoolean);
        SUPPLIERS.put(Boolean.class, RANDOM::nextBoolean);
        SUPPLIERS.put(double.class, () -> RANDOM.nextDouble(1000));
        SUPPLIERS.put(Double.class, () -> RANDOM.nextDouble(1000));
        SUPPLIERS.put(float.class, () -> RANDOM.nextFloat(1000));
        SUPPLIERS.put(Float.class,() -> RANDOM.nextFloat(1000));
        SUPPLIERS.put(UUID.class, UUID::randomUUID);
    }


    @Override
    public boolean supports(Class<?> rawType, Type genericType) {
        return SUPPLIERS.containsKey(rawType);
    }

    @Override
    public Object generate(Class<?> rawType, Type genericType, MockContext context) {
        Object value = SUPPLIERS.get(rawType).get();
        if (rawType == String.class) {
            return value;
        }
        return value;
    }
}
