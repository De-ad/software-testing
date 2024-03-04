package com.brigada.heap;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeapNode{
        private int key;
        private int degree;
        private HeapNode parent;
        private HeapNode child;
        private HeapNode left;
        private HeapNode right;
        private boolean marked;

        public HeapNode() {
            this.key = 0;
            this.degree = 0;
            this.parent = null;
            this.child = null;
            this.left = this;
            this.right = this;
            this.marked = false;
        }
}
