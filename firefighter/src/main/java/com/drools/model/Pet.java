
package com.drools.model;

public class Pet {
    private String name;
    private String position;
    private String type;
    public Pet() {
    }

    public Pet(String name, String position, String type) {

        this.name = name;
        this.position = position;
        this.type = type;
        System.out.println(name +"("+type+"): is "+position);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        System.out.println(name + "("+ type + "): is "+position);
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Pet [type= '"+this.type+"', name= '"+this.name+"', position= '"+this.position+"']";
    }

    
}