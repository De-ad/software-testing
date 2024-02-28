package com.brigada.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapAppTest {

    private final HeapApp heapApp = new HeapApp();

    @Test
    public void add() {
        assertEquals(12, heapApp.add(6, 6));
    }

}