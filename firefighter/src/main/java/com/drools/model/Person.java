package com.drools.model;

public class Person {
    private String name;
    private Pet pet;
    private int petCallCount;

    public Person() {
    }
    
    public Person(String name, Pet pet, int petCallCount) {
        this.name = name;
        this.pet = pet;
        this.petCallCount = petCallCount;

        System.out.println(name +"("+pet+")"+petCallCount);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public int getPetCallCount() {
        return petCallCount;
    }

    public void setPetCallCount(int petCallCount) {
        this.petCallCount = petCallCount;
    }

    

    @Override
    public String toString() {
        return "Person[name= '"+name+"', petName= '"+pet.getName()+"', petCallCount= '"+petCallCount+"']";
    }
    
}