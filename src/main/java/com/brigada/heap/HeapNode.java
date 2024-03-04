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

        public HeapNode(int key) {
            this.key = key;
            this.degree = 0;
            this.parent = null;
            this.child = null;
            this.left = this;
            this.right = this;
            this.marked = false;
        }

        public HeapNode() {
            this.key = 0;
            this.degree = 0;
            this.parent = null;
            this.child = null;
            this.left = this;
            this.right = this;
            this.marked = false;
        }

        @Override
        public String toString() {
            return "HeapNode{" +
                    "key=" + key +
                    ", degree=" + degree +
                    ", parent=" + (parent != null ? parent.key : "null") +
                    ", child=" + (child != null ? child.key : "null") +
                    ", left=" + (left != null ? left.key : "null") +
                    ", right=" + (right != null ? right.key : "null") +
                    ", marked=" + marked +
                    '}';
        }
}
