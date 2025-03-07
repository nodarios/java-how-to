package com.cmp.javahowto.dsa.structure;

public class MyQueue<E> {

    private Object[] queue = new Object[10];
    private int tail = 0;
    private int head = 0;

    public void offer(E element) {
        if (tail >= queue.length) {
            if (actualLength() > queue.length / 2) {
                resize();
            } else {
                shift();
            }
        }
        queue[tail] = element;
        tail++;
    }

    public E peek() {
        if (actualLength() > 0) {
            return (E) queue[head];
        }
        return null;
    }

    public E poll() {
        if (actualLength() > 0) {
            return (E) queue[head++];
        }
        return null;
    }

    private void shift() {
        int index = 0;
        for (int i = head; i < tail; i++) {
            queue[index] = queue[i];
            index++;
        }
        tail = index;
        head = 0;
    }

    private void resize() {
        int index = 0;
        Object[] newQueue = new Object[queue.length + 10];
        for (int i = head; i < tail; i++) {
            newQueue[index] = queue[i];
            index++;
        }
        queue = newQueue;
        tail = index;
        head = 0;
    }

    private int actualLength() {
        return tail - head;
    }

}
