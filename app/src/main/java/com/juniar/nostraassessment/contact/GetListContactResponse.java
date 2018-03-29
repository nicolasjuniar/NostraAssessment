package com.juniar.nostraassessment.contact;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GetListContactResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private ArrayList<ContactModel> result;

    public GetListContactResponse(String message, ArrayList<ContactModel> result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ContactModel> getResult() {
        return result;
    }

    public void setResult(ArrayList<ContactModel> result) {
        this.result = result;
    }
}
