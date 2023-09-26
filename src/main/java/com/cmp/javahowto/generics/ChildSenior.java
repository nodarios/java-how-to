package com.cmp.javahowto.generics;

import lombok.ToString;

@ToString
public class ChildSenior extends Parent {

    private final String value = "child senior";

    @Override
    public String getValue() {
        return value;
    }

}
