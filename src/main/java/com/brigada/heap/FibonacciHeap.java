package com.brigada.heap;

import java.util.ArrayList;

import lombok.Getter;

@Getter
public class FibonacciHeap<T extends Comparable<T>> {

   
    private HeapNode minHeapNode;
    private int size;

    public FibonacciHeap() {
        this.minHeapNode = null;
        this.size = 0;
    }

    public void mergeLists(HeapNode root1, HeapNode root2) {
        if (root1 == null || root2 == null)
            return;

        HeapNode L = root1.getLeft();
        HeapNode R = root2.getRight();

        root2.setRight(root1);
        root1.setLeft(root2);

        if (L != null && R != null) {
            L.setRight(R);
            R.setLeft(L);
        }
    }

    public void mergeHeaps(FibonacciHeap<T> heap) {
        if (heap.getSize() == 0)
            return;

        if (size == 0) {
            minHeapNode = heap.getMinHeapNode();
            size = heap.getSize();
        } else {
            mergeLists(minHeapNode, heap.getMinHeapNode());
            size += heap.size;
        }

        minHeapNode = (minHeapNode.getKey() < heap.minHeapNode.getKey()) ? minHeapNode : heap.minHeapNode;
    }

    public int getMin() {
        return minHeapNode.getKey();
    }

    public void insert(int key) {
        HeapNode newNode = new HeapNode();
        newNode.setKey(key);

        if (size == 0) {
            minHeapNode = newNode;
            minHeapNode.setLeft(newNode);
            minHeapNode.setRight(newNode);
        } else {
            HeapNode prevRight = minHeapNode.getRight();
            minHeapNode.setRight(newNode);
            newNode.setLeft(minHeapNode);
            newNode.setRight(prevRight);
            prevRight.setLeft(newNode);

            if (newNode.getKey() < minHeapNode.getKey())
                minHeapNode = newNode;
        }

        newNode.setParent(null);
        size++;
    }

    public Integer deleteMin() {
        if (size == 0)
            return null;
        
        HeapNode prevMin = minHeapNode;
        mergeLists(minHeapNode, minHeapNode.getChild());
        HeapNode L = minHeapNode.getLeft();
        HeapNode R = minHeapNode.getRight();
        L.setRight(R);
        R.setLeft(L);

        if (prevMin.getRight() == prevMin) {
            minHeapNode = prevMin;
            size--;
            return prevMin.getKey();
        }

        minHeapNode = minHeapNode.getRight();
        compress();
        size--;
        return prevMin.getKey();
    }

    private void compress() {
        ArrayList<HeapNode> A = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            A.add(null);
        }

        A.set(minHeapNode.getDegree(), minHeapNode);
        HeapNode current = minHeapNode.getRight();

        while (A.get(current.getDegree()) != current) {
            HeapNode next = current.getRight();

            if (A.get(current.getDegree()) == null) {
                A.set(current.getDegree(), current);
                current = next;
            } else {
                HeapNode conflict = A.get(current.getDegree());
                HeapNode addTo, adding;

                if (conflict.getKey() < current.getKey()) {
                    addTo = conflict;
                    adding = current;
                } else {
                    addTo = current;
                    adding = conflict;
                }

                mergeLists(addTo.getChild(), adding);
                adding.setParent(addTo);
                addTo.setDegree(addTo.getDegree() + 1);
                current = addTo;
            }

            if (minHeapNode.getKey() > current.getKey()) {
                minHeapNode = current;
            }
        }
    }

    public void decreaseKey(HeapNode node, int newValue) {
        if (node.getParent() != null && (newValue < node.getParent().getKey())) {
            node.setKey(newValue);
            return;
        }

        HeapNode parent = node.getParent();
        cut(node);
        cascadingCut(parent);
    }

    private void cut(HeapNode node) {
        if (node == null || node.getParent() == null)
            return;

        HeapNode L = node.getLeft();
        HeapNode R = node.getRight();
        R.setLeft(L);
        L.setRight(R);
        node.getParent().setDegree(node.getParent().getDegree() - 1);

        if (node.getParent().getChild() == node) {
            if (node.getRight() == node) {
                node.getParent().setChild(null);
            } else {
                node.getParent().setChild(node.getRight());
            }
        }

        node.setRight(node);
        node.setLeft(node);
        node.setParent(null);
        mergeLists(minHeapNode, node);
    }

    private void cascadingCut(HeapNode node) {
        if (node == null || node.getParent() == null)
            return;

        while (node.isMarked()) {
            cut(node);
            node = node.getParent();
            node.setMarked(true);
        }
    }
}