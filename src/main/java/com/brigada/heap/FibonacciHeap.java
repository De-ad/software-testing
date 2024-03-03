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

    public FibonacciHeap(HeapNode minHeapNode) {
        this.minHeapNode = minHeapNode;
        this.size = 0;
    }

    public void mergeLists(HeapNode root1, HeapNode root2) {
        HeapNode L = root1.getLeft();
        HeapNode R = root2.getRight();
        root2.setRight(root1);
        root1.setLeft(root2);
        L.setRight(R);
        R.setLeft(L);
    }

    public void mergeHeaps(FibonacciHeap<T> heap){
        if (heap.getSize() == 0){
            return;
        }
        if (size == 0){
            minHeapNode = heap.getMinHeapNode();
            size = heap.getSize();
        }
        else{
            mergeLists(minHeapNode, heap.getMinHeapNode());
            size += heap.size;
        }
        minHeapNode = (minHeapNode.getKey() < heap.minHeapNode.getKey()) ? minHeapNode : heap.minHeapNode;
    }

    public int getMin(){
        return (int) minHeapNode.getKey();
    }

    public void insert(int key) {
        HeapNode newHeapNode = new HeapNode(key);
        FibonacciHeap<T> newHeap = new FibonacciHeap<>(newHeapNode);
        mergeHeaps(newHeap);
    }

    public int deleteMin(){
        HeapNode prevMin = minHeapNode;
        mergeLists(minHeapNode, minHeapNode.getChild()); 
        HeapNode L = minHeapNode.getLeft();             
        HeapNode R = minHeapNode.getRight();
        L.setRight(R);
        R.setLeft(L);
        if (prevMin.getRight() == prevMin){
            return minHeapNode.getKey();
        } 
        minHeapNode = minHeapNode.getRight();             
        compress();
        size--; 
        return prevMin.getKey();
    }

    public void compress(){
        ArrayList<HeapNode> A = new ArrayList<HeapNode>();
        A.set(minHeapNode.getDegree(), minHeapNode);   
        HeapNode current = minHeapNode.getRight();
        while(A.get(current.getDegree()) != current){     
            if (A.get(current.getDegree()) == null){     
                A.set(current.getDegree(), current);
                current = current.getRight();
            }
            else {                               
                HeapNode conflict = A.get(current.getDegree());
                HeapNode addTo;
                HeapNode adding;
                if (conflict.getKey() < current.getKey()){
                    addTo = conflict;
                    adding = current;
                }
                else {
                    addTo = current;
                    adding = conflict;
                }

                mergeLists(addTo.getChild(), adding);
                adding.setParent(addTo);
                addTo.setDegree(addTo.getDegree() + 1);
                current = addTo;    
            }
            if (minHeapNode.getKey() > current.getKey()){        
                minHeapNode = current;   
            }
        }
    }

    public void decreaseKey(HeapNode node, int newValue){
        if (newValue > node.getParent().getKey()){
            node.setKey(newValue);
            return;
        } 
        HeapNode parent = node.getParent();
        cut(node);
        cascadingCut(parent);
    }

    public void cut(HeapNode node){
        HeapNode L = node.getLeft();
        HeapNode R = node.getRight();
        R.setLeft(L);            
        L.setRight(R);
        node.getParent().setDegree( node.getParent().getDegree() - 1);
        if (node.getParent().getChild() == node ){
            if (node.getRight() == node){
                node.getParent().setChild(null);
            }
            else{
                node.getParent().setChild(node.getRight());
            } 
        } 
        node.setParent(node);
        node.setLeft(node);
        node.setParent(null);
        mergeLists(minHeapNode, node);
    }

    public void cascadingCut(HeapNode node){
        while(node.isMarked()){
            cut(node);
            node = node.getParent();
            node.setMarked(true);
        }
    }

}
