package com.example.define_lab.Model;

import com.google.gson.annotations.SerializedName;

public class Item_Model {

    private String id;
    private String name;

    @SerializedName("contact")
    private Info_Model contact;


    @SerializedName("location")
    private Info_Model location;



    public Item_Model(String id, String name, Info_Model contact, Info_Model location) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Info_Model getContact() {
        return contact;
    }

    public void setContact(Info_Model contact) {
        this.contact = contact;
    }

    public Info_Model getLocation() {
        return location;
    }

    public void setLocation(Info_Model location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}



