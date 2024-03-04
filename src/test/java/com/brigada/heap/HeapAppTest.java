package com.brigada.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

class HeapAppTest {

    private FibonacciHeap heap;

    @BeforeEach
    public void initHeap(){
        heap = new FibonacciHeap();
    }

    @Test
    @DisplayName("Check insert and getMin")
    public void testInsertionAndMinimum() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        assertEquals(3, heap.getMin());
    }

    @Test
    @DisplayName("Check delete min")
    public void testDeleteMinimum() {
        heap.insert(5);
        heap.insert(3);
        heap.insert(7);
        assertEquals(3, heap.deleteMin());
        assertEquals(5, heap.getMin());
    }

    @Test 
    @DisplayName("Check delete min in heap with only one node")
    public void testDeleteMinimumWithOneNode(){
        heap.insert(5);
        assertEquals(5, heap.deleteMin());
    }

    @Test
    @DisplayName("Check two heaps merge")
    public void testMergeHeaps() {
        heap.insert(5);
        heap.insert(3);
        
        FibonacciHeap<Integer> heap2 = new FibonacciHeap<>();
        heap2.insert(7);
        heap2.insert(2);

        heap.mergeHeaps(heap2);

        assertEquals(2, heap.getMin());
        assertEquals(4, heap.getSize());
    }

    @Test
    @DisplayName("Check decrease key")
    public void testDecreaseKey() {

        heap.insert(5);
        heap.insert(8);
        heap.insert(12);

        HeapNode nodeToDecrease = heap.getMinHeapNode();
        heap.decreaseKey(nodeToDecrease, 3);
        assertAll(
            () -> assertEquals(5, heap.getMinHeapNode().getKey()),
            () -> assertEquals(3, heap.getSize()),
            () ->  assertEquals(5, heap.deleteMin()),
            () -> assertEquals(8, heap.deleteMin()),
            () -> assertEquals(12, heap.deleteMin())
        );
    }



    @Test
    @DisplayName("Check merge heap with empty heap")
    public void testMergeEmptyHeap(){
        heap.insert(5);
        heap.insert(3);
        
        FibonacciHeap<Integer> heap2 = new FibonacciHeap<>();

        heap.mergeHeaps(heap2);

        assertEquals(3, heap.getMin());
        assertEquals(2, heap.getSize());
    }

}