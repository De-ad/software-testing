package com.brigada.heap;

import lombok.Getter;

@Getter
public class FibonacciHeap<T extends Comparable<T>> {

    private FibonacciHeapNode<T> minNode;
    private int size;
    
    public FibonacciHeap() {
        this.minNode = null;
        this.size = 0;
    }

    public void insert(T key) {
        FibonacciHeapNode<T> newNode = new FibonacciHeapNode<>(key);
        if (minNode == null) {
            minNode = newNode;
        } else {
            mergeLists(minNode, newNode);
            if (newNode.getKey().compareTo(minNode.getKey()) < 0) {
                minNode = newNode;
            }
        }
        size++;
    }

    // Merge two circular doubly linked lists
    private void mergeLists(FibonacciHeapNode<T> root1, FibonacciHeapNode<T> root2) {
        FibonacciHeapNode<T> temp1 = root1.getRight();
        root1.setRight(root2.getRight());
        root2.getRight().setLeft(root1);
        root2.setRight(temp1);
        temp1.setLeft(root2);
    }

    public T extractMin() {
        if (minNode == null) return null;
        FibonacciHeapNode<T> extracted = minNode;
        if (minNode.getChild() != null) {
            FibonacciHeapNode<T> child = minNode.getChild();
            do {
                FibonacciHeapNode<T> next = child.getRight();
                minNode.getChild().setParent(null);
                mergeLists(minNode, child);
                child = next;
            } while (child != minNode.getChild());
        }
        minNode.getLeft().setRight(minNode.getRight());
        minNode.getRight().setLeft(minNode.getLeft());
        if (minNode == minNode.getRight()) {
            minNode = null;
        } else {
            minNode = minNode.getRight();
            consolidate();
        }
        size--;
        return extracted.getKey();
    }

    // Consolidate the root list
    private void consolidate() {
        int maxDegree = (int) Math.ceil(Math.log(size) / Math.log(2));
        FibonacciHeapNode<T>[] array = new FibonacciHeapNode[maxDegree * 2];
        FibonacciHeapNode<T> current = minNode;
        do {
            FibonacciHeapNode<T> next = current.getRight();
            int degree = current.getDegree();
            while (array[degree] != null) {
                FibonacciHeapNode<T> other = array[degree];
                if (current.getKey().compareTo(other.getKey()) > 0) {
                    FibonacciHeapNode<T> temp = current;
                    current = other;
                    other = temp;
                }
                link(other, current);
                array[degree] = null;
                degree++;
            }
            array[degree] = current;
            current = next;
        } while (current != minNode);
        minNode = null;
        for (FibonacciHeapNode<T> node : array) {
            if (node != null) {
                if (minNode == null) {
                    minNode = node;
                } else {
                    mergeLists(minNode, node);
                    if (node.getKey().compareTo(minNode.getKey()) < 0) {
                        minNode = node;
                    }
                }
            }
        }
    }

    // Link two trees of the same degree
    private void link(FibonacciHeapNode<T> child, FibonacciHeapNode<T> parent) {
        child.getLeft().setRight(child.getRight());
        child.getRight().setLeft(child.getLeft());
        child.setParent(parent);
        if (parent.getChild() == null) {
            parent.setChild(child);
            child.setLeft(child);
            child.setRight(child);
        } else {
            mergeLists(parent.getChild(), child);
        }
        parent.setDegree(parent.getDegree() + 1);
        child.setMarked(false);
    }

    public void decreaseKey(FibonacciHeapNode<T> node, T newKey) {
        if (newKey.compareTo(node.getKey()) > 0) {
            throw new IllegalArgumentException("New key is greater than the current key");
        }
        node.setKey(newKey);
        FibonacciHeapNode<T> parent = node.getParent();
        if (parent != null && node.getKey().compareTo(parent.getKey()) < 0) {
            cut(node, parent);
            cascadingCut(parent);
        }
        if (node.getKey().compareTo(minNode.getKey()) < 0) {
            minNode = node;
        }
    }

    // Cut a node from its parent
    private void cut(FibonacciHeapNode<T> child, FibonacciHeapNode<T> parent) {
        child.getLeft().setRight(child.getRight());
        child.getRight().setLeft(child.getLeft());
        parent.setDegree(parent.getDegree() - 1);
        if (parent.getChild() == child) {
            parent.setChild(child.getRight());
        }
        if (parent.getDegree() == 0) {
            parent.setChild(null);
        }
        mergeLists(minNode, child);
        child.setParent(null);
        child.setMarked(false);
    }

    private void cascadingCut(FibonacciHeapNode<T> node) {
        FibonacciHeapNode<T> parent = node.getParent();
        if (parent != null) {
            if (!node.isMarked()) {
                node.setMarked(true);
            } else {
                cut(node, parent);
                cascadingCut(parent);
            }
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
