package com.hsryuuu.base.java.mock.test;

import lombok.Data;

@Data
public class RecursiveDto {
    private String name;
    private RecursiveDto children;
}
