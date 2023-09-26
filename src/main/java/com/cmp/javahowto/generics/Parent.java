package com.cmp.javahowto.generics;

import lombok.ToString;

@ToString
public class Parent {

    private final String value = "parent";

    public String getValue() {
        return value;
    }

}
