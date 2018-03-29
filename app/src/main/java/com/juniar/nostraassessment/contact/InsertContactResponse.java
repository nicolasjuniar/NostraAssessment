package com.juniar.nostraassessment.contact;

import com.google.gson.annotations.SerializedName;

public class InsertContactResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private String result;

    public InsertContactResponse(String message, String result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
