package com.cmp.javahowto.dsa.structure;

public class MyCircularQueue<E> {

    private Object[] queue = new Object[3];
    private int tail = 0;
    private int head = 0;
    private int size = 0;

    public boolean offer(E element) {
        if (tail != head || size == 0) {
            queue[tail] = element;
            tail = (tail + 1) % queue.length;
            size++;
            return true;
        }
        return false;
    }

    public E peek() {
        if (size > 0) {
            return (E) queue[head];
        }
        return null;
    }

    public E poll() {
        if (size > 0) {
            E element = (E) queue[head];
            head = (head + 1) % queue.length;
            size--;
            return element;
        }
        return null;
    }

}
