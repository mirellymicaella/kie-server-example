package com.drools.model;

public class Person {
    private int id;
    private String name;

    public Person() {
    }
    
    public Person( int id,String name) {
        this.id = id;
        this.name = name;

        System.out.println(this);
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person[id= "+id+"name= '"+name+"']";
    }
    
}
