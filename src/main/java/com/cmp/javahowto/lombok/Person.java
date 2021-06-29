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

    private int id;

    private String user;
    
    //@JsonProperty("date_time")
    //@JsonAlias({"date_time"})
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //private Date dateTime;

    public void dump() {
        System.out.printf("person dump: %d, %s\n", id, user);
    }

}
