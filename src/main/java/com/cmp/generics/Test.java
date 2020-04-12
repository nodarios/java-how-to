package com.cmp.generics;

import java.util.List;

public class Test {

    public static void main(String[] args) {


    }

    public static List<? super MyClassA> processElements3(List<? super MyClassA> elements) {
        for (Object a : elements) {
            //System.out.println(a.getValue());
        }

        //elements.add(new Object());
        elements.add(new MyClassA());
        elements.add(new MyClassB());
        elements.add(new MyClassC());
        return elements;
    }

    public static List<? extends MyClassA> processElements2(List<? extends MyClassA> elements) {
        for (MyClassA a : elements) {
            System.out.println(a.getValue());
        }

        //elements.add(new Object());
        //elements.add(new MyClassA());
        //elements.add(new MyClassB());
        //elements.add(new MyClassC());
        return elements;
    }

    public static <T extends MyClassA> List<T> processElements(List<T> elements) {
        for (T a : elements) {
            System.out.println(a.getValue());
        }

        //elements.add(new Object());
        //elements.add(new MyClassA());
        //elements.add(new MyClassB());
        //elements.add(new MyClassC());
        return elements;
    }

}
