package com.example.define_lab.Model;

import com.google.gson.annotations.SerializedName;

public class Message_Model {

    @SerializedName("response")
    private Responses response;

    public Message_Model(Responses response) {
        this.response = response;
    }

    public Responses getResponse() {
        return response;
    }

    public void setResponse(Responses response) {
        this.response = response;
    }
}
