package com.cmp.javakafka;

public class Person {

    private int id;
    private String user;

    public Person(int id, String user) {
        this.id = id;
        this.user = user;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void dump() {
        System.out.printf("person dump: %d, %s\n", id, user);
        System.out.println(id + " " + user);
    }
}
