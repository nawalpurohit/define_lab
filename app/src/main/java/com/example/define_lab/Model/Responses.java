package com.example.define_lab.Model;

import com.example.define_lab.Model.Item_Model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Responses {

    @SerializedName("venues")
    ArrayList<Item_Model> venues;

    public Responses(ArrayList<Item_Model> item_models) {
        this.venues = item_models;
    }

    public ArrayList<Item_Model> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Item_Model> item_models) {
        this.venues = item_models;
    }
}
