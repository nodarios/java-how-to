package com.cmp.javahowto.equals_hashcode;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {

        Person alexStu = new Student(1, "alex");
        Person alexWor = new Worker(1, "alex");
        System.out.println("equality: " + alexStu.equals(alexWor));
        System.out.println("hash codes: " + alexStu.hashCode() + " " + alexWor.hashCode());

        System.out.println();
        Set<Person> set = new HashSet<Person>() {{
            add(alexStu);
            add(new Student(1, "alex"));
            add(alexWor);
        }};
        System.out.println("set size: " + set.size());
        set.forEach(e -> System.out.println(e.getClass()));

    }

}
