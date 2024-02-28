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

}