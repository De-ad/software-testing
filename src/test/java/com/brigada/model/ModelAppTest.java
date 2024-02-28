package com.brigada.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelAppTest {

    private final ModelApp modelApp = new ModelApp();

    @Test
    public void add() {
        assertEquals(12, modelApp.add(6, 6));
    }

    @Test
    void div() {
        assertEquals(12d, modelApp.div(48, 4));
    }

    @Test
    void sub() {
        assertEquals(1, modelApp.sub(3, 2));
    }

    @Test
    void mul() {
        assertEquals(12, modelApp.mul(3, 4));
    }
}