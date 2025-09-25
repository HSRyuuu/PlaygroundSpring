package com.hsryuuu.base.java.mock.test;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class NestedDto {
    private Long id;
    private String name;
    private SampleEnum type;
}
