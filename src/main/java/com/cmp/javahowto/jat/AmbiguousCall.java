package com.cmp.javahowto.jat;

public class AmbiguousCall {

    public static void main(String[] args) {
        AmbiguousCall t1 = new AmbiguousCall();
        t1.method1(null);
    }

    public void method1(Object o) {
        System.out.println("object call");
    }

    public void method1(String o) {
        System.out.println("String call");
    }

    //public void method1(Integer o) {
    //    System.out.println("Integer call");
    //}

}
