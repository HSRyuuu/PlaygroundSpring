package com.hsryuuu.base.java.mock;

import static org.junit.jupiter.api.Assertions.*;


import com.hsryuuu.base.java.mock.test.MockTestDto;
import org.junit.jupiter.api.Test;

class MockDataCreatorTest {

    private final MockDataCreator mockDataCreator = new MockDataCreator();

    @Test
    void createMock(){
        MockTestDto dto = mockDataCreator.createMock(MockTestDto.class);
        System.out.println(dto);
        System.out.println(dto.getListOfNested().get(0));
        assertNotNull(dto);

        // 기본 타입
        assertTrue(dto.getIntField() >= 0);
        assertNotNull(dto.getIntegerField());
        assertNotNull(dto.getLongObjField());
        assertNotNull(dto.getBooleanObjField());
        assertNotNull(dto.getDoubleObjField());
        assertNotNull(dto.getFloatObjField());

        // 문자열 & UUID
        assertNotNull(dto.getStringField());
        assertTrue(dto.getStringField().startsWith("mock_"));
        assertNotNull(dto.getUuidField());

        // Enum
        assertNotNull(dto.getEnumField());

        // 리스트
        assertNotNull(dto.getListOfStrings());
        assertFalse(dto.getListOfStrings().isEmpty());

        assertNotNull(dto.getListOfNested());
        assertFalse(dto.getListOfNested().isEmpty());
        assertNotNull(dto.getListOfNested().get(0).getId());

        // 중첩 객체
        assertNotNull(dto.getNestedObject());
        assertNotNull(dto.getNestedObject().getName());
        assertNotNull(dto.getNestedObject().getType());
    }

}