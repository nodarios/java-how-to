package com.cmp.javahowto.annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        /*
         * Java annotations are a form of metadata that can be added to Java source code
         * to provide additional information about the code to the compiler and/or runtime environment.
         *
         * e.g.
         * @SuppressWarnings: This annotation is used to suppress compiler warnings
         */

        Class<MyClass> myClazz = MyClass.class;

        // Process @MyClassAnn
        if (myClazz.isAnnotationPresent(MyClassAnn.class)) {
            MyClassAnn myClassAnn = myClazz.getDeclaredAnnotation(MyClassAnn.class);
            System.out.printf("Priority: %s%n", myClassAnn.priority());
            System.out.printf("Tags: %s%n", Arrays.toString(myClassAnn.tags()));
            System.out.printf("CreatedBy: %s%n", myClassAnn.createdBy());
            System.out.printf("LastModified: %s%n", myClassAnn.lastModified());
        }

        // Process @MyMethAnn
        for (Method method : myClazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MyMethAnn.class)) {
                MyMethAnn myMethAnn = method.getAnnotation(MyMethAnn.class);
                if (myMethAnn.enabled()) {
                    System.out.printf("%s ", method.getName());
                    method.setAccessible(true);
                    method.invoke(myClazz.newInstance());
                } else {
                    System.out.printf("%s ignored%n", method.getName());
                }
            }
        }

    }

}
