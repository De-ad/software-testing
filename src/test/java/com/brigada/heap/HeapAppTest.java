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
    @DisplayName("Check delete min in heap with empty heap")
    public void testDeleteMinWithEmptyHeap(){
        assertEquals(null, heap.deleteMin());
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
        heap.insert(2);
        heap.insert(8);
        HeapNode nodeToDecrease = heap.getMinHeapNode();
        heap.decreaseKey(nodeToDecrease, 1);
        assertEquals(1, heap.getMinHeapNode().getKey());
    }

    @Test
    @DisplayName("Check decrease key")
    public void testDecreaseMiddleKey() {
        heap.insert(5);
        heap.insert(2);
        heap.insert(8);
        HeapNode parent = heap.getMinHeapNode();
        HeapNode node = new HeapNode();
        node.setKey(5);
        parent.setChild(node);
        HeapNode node2 = new HeapNode();
        node2.setKey(8);
        node.setChild(node2);
        heap.decreaseKey(node, 1);
        assertEquals(1, heap.getMinHeapNode().getKey());
    }

    @Test
    @DisplayName("Check decrease key")
    public void testDecreaseEndKey() {
        heap.insert(5);
        heap.insert(2);
        heap.insert(8);
        HeapNode parent = heap.getMinHeapNode();
        HeapNode node = new HeapNode();
        node.setKey(5);
        parent.setChild(node);
        node.setParent(parent);
        HeapNode node2 = new HeapNode();
        node2.setKey(8);
        node.setChild(node2);
        node2.setParent(node);
        heap.decreaseKey(node, 1);
        assertEquals(1, heap.getMinHeapNode().getKey());
    }




    @Test
    @DisplayName("Check merge heap with empty heap")
    public void testMergeEmptyHeap(){

        FibonacciHeap<Integer> heap2 = new FibonacciHeap<>();
        heap2.insert(5);
        heap2.insert(3);

        heap.mergeHeaps(heap2);

        assertEquals(3, heap.getMin());
        assertEquals(2, heap.getSize());
    }

}