package com.cmp.javahowto.dsa.structure;

public class MyCircularQueue<E> {

    private Object[] queue = new Object[3];
    private int tail = 0;
    private int head = 0;
    private boolean isEmpty = true;

    public boolean offer(E element) {
        if (tail != head || isEmpty) {
            queue[tail] = element;
            tail = (tail + 1) % queue.length;
            isEmpty = false;
            return true;
        }
        return false;
    }

    public E peek() {
        if (!isEmpty) {
            return (E) queue[head];
        }
        return null;
    }

    public E poll() {
        if (!isEmpty) {
            E element = (E) queue[head];
            head = (head + 1) % queue.length;
            if (head == tail) {
                isEmpty = true;
            }
            return element;
        }
        return null;
    }

}
