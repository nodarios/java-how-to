package com.cmp.javahowto.dsa;

public class MyLinkedList<E> {

    private Node first;
    private Node last;

    public void add(E element) {
        if (first == null) {
            this.first = new Node(element);
        } else if (last == null) {
            Node newNode = new Node(element);
            this.first.next = newNode;
            last = newNode;
        } else {
            Node newNode = new Node(element);
            this.last.next = newNode;
            last = newNode;
        }
    }

    public E get(int index) {
        if (index == 0) {
            return first.element;
        }
        int coutner = 0;
        Node node = first;
        while (node.next != null) {
            node = node.next;
            coutner++;
            if (coutner == index) {
                return node.element;
            }
        }
        return null;
    }

    private class Node {
        private E element;
        private Node next;

        private Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

}
