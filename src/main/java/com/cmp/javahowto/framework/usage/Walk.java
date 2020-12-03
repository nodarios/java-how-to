package com.cmp.javahowto.framework.usage;

import com.cmp.javahowto.framework.sample.Move;

public class Walk implements Move {

    @Override
    public void action() {
        System.out.println("walking");
    }

}
