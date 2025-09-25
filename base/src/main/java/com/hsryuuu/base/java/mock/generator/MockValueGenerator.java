package com.hsryuuu.base.java.mock.generator;

import com.hsryuuu.base.java.mock.util.MockContext;
import java.lang.reflect.Type;

public interface MockValueGenerator {
    boolean supports(Class<?> rawType, Type genericType);
    Object generate(Class<?> rawType, Type genericType, MockContext context);
}