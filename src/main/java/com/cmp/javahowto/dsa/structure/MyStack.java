package com.cmp.javahowto.dsa.structure;

public class MyStack<E> {

    private Object[] stack = new Object[10];
    private int position = 0;

    public void offer(E element) {
        if (position >= stack.length) {
            resize();
        }
        stack[position] = element;
        position++;
    }

    public E peek() {
        if (position > 0) {
            return (E) stack[position - 1];
        }
        return null;
    }

    public E poll() {
        if (position > 0) {
            return (E) stack[--position];
        }
        return null;
    }

    private void resize() {
        Object[] newStack = new Object[stack.length + 10];
        for (int i = 0; i < position; i++) {
            newStack[i] = stack[i];
        }
        stack = newStack;
    }

}
