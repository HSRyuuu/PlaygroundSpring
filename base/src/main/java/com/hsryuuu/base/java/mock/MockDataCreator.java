package com.hsryuuu.base.java.mock;

import com.hsryuuu.base.java.mock.generator.*;
import com.hsryuuu.base.java.mock.util.MockContext;
import java.util.ArrayList;
import java.util.List;

public class MockDataCreator {

    private final List<MockValueGenerator> generators;
    private final ObjectValueGenerator objectGenerator;

    public MockDataCreator() {
        this.generators = new ArrayList<>();
        this.objectGenerator = new ObjectValueGenerator(generators); // 먼저 넣기
        this.generators.add(new PrimitiveValueGenerator());
        this.generators.add(new EnumValueGenerator());
        this.generators.add(new ListValueGenerator(generators));
        this.generators.add(objectGenerator);
    }

    @SuppressWarnings("unchecked")
    public <T> T createMock(Class<T> clazz){
        return (T) objectGenerator.generate(clazz, clazz, new MockContext(3));
    }
}