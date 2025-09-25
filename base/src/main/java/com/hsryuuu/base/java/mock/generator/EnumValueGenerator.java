package com.hsryuuu.base.java.mock.generator;

import com.hsryuuu.base.java.mock.util.MockContext;
import java.lang.reflect.Type;

public class EnumValueGenerator implements MockValueGenerator {

    @Override
    public boolean supports(Class<?> rawType, Type genericType) {
        return rawType.isEnum();
    }

    @Override
    public Object generate(Class<?> rawType, Type genericType, MockContext context) {
        Object[] constants = rawType.getEnumConstants();
        return constants != null && constants.length > 0 ? constants[0] : null;
    }
}