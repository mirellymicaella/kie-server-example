
package com.drools.model;

public class Pet {
    private String name;
    private String position;
    private String type;
    private int ownerId;
    private int callCount;


    public Pet() {
    }

    public Pet(String name, String position, String type, int ownerId, int callCount) {
        this.name = name;
        this.position = position;
        this.type = type;
        this.ownerId = ownerId;
        this.callCount = callCount;
        System.out.println(name +"("+type+"): is "+position);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
        System.out.println(name + "("+ type + "): is "+position);
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getCallCount() {
        return this.callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    @Override
    public String toString() {
        return "Pet [type= '"+this.type+"', name= '"+this.name+"', position= '"+this.position+"', ownerId= '"+this.ownerId+"', callCount= '"+this.callCount+"']";
    }

    
}