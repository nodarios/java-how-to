package com.cmp.javahowto.framework.usage;

import com.cmp.javahowto.framework.sample.Human;

/*
 * A library is a set of functions that you call.
 *
 * A framework is a "skeleton" where you define the "meat".
 * The framework's code then calls your code.
 */

public class Main {

    public static void main(String[] args) {
        Human walkingHuman = new Human(new Walk());
        walkingHuman.doMove();
        Human sprintingHuman = new Human(new Sprint());
        sprintingHuman.doMove();
    }

}
