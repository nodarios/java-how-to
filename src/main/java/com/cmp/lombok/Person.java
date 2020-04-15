package com.cmp.lombok;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@SuperBuilder
//@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Person {

    private int id;
    private String user;

    public void dump() {
        System.out.printf("person dump: %d, %s\n", id, user);
    }
}
