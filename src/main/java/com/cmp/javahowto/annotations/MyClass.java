package com.cmp.javahowto.annotations;

@MyClassAnn(priority = MyClassAnn.Priority.HIGH, tags = {"sales", "test"})
public class MyClass {

    @MyMethAnn
    public void testA() {
        System.out.println("A");
    }

    @MyMethAnn(enabled = false)
    public void testB() {
        System.out.println("B");
    }

    @MyMethAnn(enabled = true)
    public void testC() {
        System.out.println("C");
    }

}
