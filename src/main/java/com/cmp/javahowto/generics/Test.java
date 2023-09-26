package com.cmp.javahowto.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * upper vs lower bound
 */
public class Test {

    public static void main(String[] args) {
        List<Parent> list = new ArrayList<>() {{
            add(new ChildSenior());
        }};

        lowerBoundedWildcard(list);
        upperBoundedWildcard(list);
        upperBoundedTypeParameter(list);
    }

    public static List<? super Parent> lowerBoundedWildcard(List<? super Parent> elements) {
        for (Object element : elements) {
            System.out.println(element);
        }

        // commented code does not work, because of type safety
        //elements.add(new Object());
        elements.add(new Parent());
        elements.add(new ChildSenior());
        elements.add(new ChildJunior());

        return elements;
    }

    public static List<? extends Parent> upperBoundedWildcard(List<? extends Parent> elements) {
        for (Parent element : elements) {
            System.out.println(element.getValue());
        }

        // commented code does not work, because of type safety
        //elements.add(new Parent());
        //elements.add(new ChildSenior());
        //elements.add(new ChildJunior());

        return elements;
    }

    public static <T extends Parent> List<T> upperBoundedTypeParameter(List<T> elements) {
        for (T a : elements) {
            System.out.println(a.getValue());
        }

        // commented code does not work, because of type safety
        //elements.add(new Parent());
        //elements.add(new ChildSenior());
        //elements.add(new ChildJunior());

        return elements;
    }

}
