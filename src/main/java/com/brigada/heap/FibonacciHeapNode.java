package com.brigada.heap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FibonacciHeapNode<T extends Comparable<T>> {
        private T key;
        private int degree;
        private FibonacciHeapNode<T> parent;
        private FibonacciHeapNode<T> child;
        private FibonacciHeapNode<T> left;
        private FibonacciHeapNode<T> right;
        private boolean marked;

        public FibonacciHeapNode(T key) {
            this.key = key;
            this.degree = 0;
            this.parent = null;
            this.child = null;
            this.left = this;
            this.right = this;
            this.marked = false;
        }
}
