package com.cmp.javahowto.lombok;

import lombok.*;

//@SuperBuilder
//@Builder
//@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {

    //@JsonProperty("ididid")
    //@JsonAlias({"ID", "id"})
    private int id;

    private String user;

    public void dump() {
        System.out.printf("person dump: %d, %s\n", id, user);
    }

}
