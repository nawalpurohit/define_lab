package com.example.define_lab.Model;

public class Save_Model {
    private String data_id;
    private String name;
    private String contact;
    private String location;
    private String star;

    public Save_Model(String data_id, String name, String contact, String location, String star) {
        this.data_id = data_id;
        this.name = name;
        this.contact = contact;
        this.location = location;
        this.star = star;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getData_id() {
        return data_id;
    }

    public void setData_id(String data_id) {
        this.data_id = data_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
