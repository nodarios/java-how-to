package com.cmp.javahowto.equals_hashcode;

//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Worker extends Person {

    private int id;
    private String name;

    public Worker(int id, String name) {
        this.id = id;
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Worker worker = (Worker) o;
//        return new EqualsBuilder()
//                .append(id, worker.id)
//                .append(name, worker.name)
//                .isEquals();
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder(17, 37)
//                .append(id)
//                .append(name)
//                .toHashCode();
//    }

}
