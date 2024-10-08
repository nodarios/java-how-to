package com.cmp.javahowto.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.lang.reflect.Method;

public class LoadMultipleLibraryVersions {

    public static void main(String[] args) throws Exception {
        String libraryVersion1Path = "path/to/library-version-1.jar";
        String libraryVersion2Path = "path/to/library-version-2.jar";

        URLClassLoader classLoader1 = new URLClassLoader(new URL[]{new URL("file:" + libraryVersion1Path)});
        URLClassLoader classLoader2 = new URLClassLoader(new URL[]{new URL("file:" + libraryVersion2Path)});

        Class<?> classVersion1 = classLoader1.loadClass("com.example.library.LibraryClass");
        Class<?> classVersion2 = classLoader2.loadClass("com.example.library.LibraryClass");

        Object instance1 = classVersion1.getDeclaredConstructor().newInstance();
        Object instance2 = classVersion2.getDeclaredConstructor().newInstance();

        Method method1 = classVersion1.getMethod("libraryMethod");
        Method method2 = classVersion2.getMethod("libraryMethod");

        method1.invoke(instance1);
        method2.invoke(instance2);

        classLoader1.close();
        classLoader2.close();
    }

}
