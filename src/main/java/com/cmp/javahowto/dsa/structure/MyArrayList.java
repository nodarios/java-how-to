package com.cmp.javahowto.dsa.structure;

public class MyArrayList<E> {

    private Object[] list = new Object[10];
    int position = 0;

    public void add(E element) {
        if (position >= list.length) {
            resize();
        }
        list[position] = element;
        position++;
    }

    public E get(int index) {
        if (index >= 0 && index < position) {
            return (E) list[index];
        }
        return null;
    }

    public E remove(int index) {
        if (index >= 0 && index < position) {
            E element = (E) list[index];
            shift(index);
            return element;
        }
        return null;
    }

    private void resize() {
        Object[] newList = new Object[list.length + 10];
        for (int i = 0; i < position; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    private void shift(int index) {
        for (int i = index + 1; i < position; i++) {
            list[i - 1] = list[i];
        }
        position--;
    }

}
