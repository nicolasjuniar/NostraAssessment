package com.juniar.nostraassessment.contact;

import com.google.gson.annotations.SerializedName;

public class UpdateContactResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private ContactModel result;

    public UpdateContactResponse(String message, ContactModel result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ContactModel getResult() {
        return result;
    }

    public void setResult(ContactModel result) {
        this.result = result;
    }
}
