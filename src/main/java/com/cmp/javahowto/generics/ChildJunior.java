package com.cmp.javahowto.generics;

import lombok.ToString;

@ToString
public class ChildJunior extends Parent {

    private final String value = "child junior";

    @Override
    public String getValue() {
        return value;
    }

}
