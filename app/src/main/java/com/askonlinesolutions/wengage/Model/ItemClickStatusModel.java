package com.askonlinesolutions.wengage.Model;

public class ItemClickStatusModel {
    int position;
    boolean status;
    String name;

    public ItemClickStatusModel(int position, boolean status) {
        this.position = position;
        this.status = status;
    }

    public ItemClickStatusModel(String  name, boolean status) {
        this.position = position;
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
