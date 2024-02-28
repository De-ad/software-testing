package com.brigada.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FunctionAppTest {

    private final FunctionApp functionApp = new FunctionApp();

    @Test
    public void add() {
        assertEquals(12, functionApp.add(6, 6));
    }

}