package com.hsryuuu.base.java.mock.util;

import lombok.Setter;

@Setter
public class MockContext {

    private int depth;
    private final int maxDepth;
    public MockContext(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public boolean canGoDeeper(){
        return depth < maxDepth;
    }

    public void enter(){
        this.depth++;
    }

    public void exit(){
        this.depth--;
    }

}
