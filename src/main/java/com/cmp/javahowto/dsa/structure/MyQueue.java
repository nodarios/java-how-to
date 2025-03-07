package com.cmp.javahowto.dsa.structure;

public class MyQueue<E> {

    private Object[] queue = new Object[10];
    private int position = 0;

    public void offer(E element) {
        queue[position] = element;
        position++;
    }

    public E peek() {
        return (E) queue[0];
    }

    public E poll() {
        E element = (E) queue[0];
        shift();
        return element;
    }

    private void shift() {
        for (int i = 1; i < position; i++) {
            queue[i - 1] = queue[i];
        }
        position--;
    }


}
