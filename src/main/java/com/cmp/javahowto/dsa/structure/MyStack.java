package com.cmp.javahowto.dsa.structure;

public class MyStack<E> {

    private Object[] stack = new Object[10];
    private int position = 0;

    public void offer(E element) {
        stack[position] = element;
        position++;
    }

    public E peek() {
        return (E) stack[position-1];
    }

    public E poll() {
        position--;
        return (E) stack[position];

    }

}
