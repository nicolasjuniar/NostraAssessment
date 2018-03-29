package com.juniar.nostraassessment.contact;

import com.google.gson.annotations.SerializedName;

public class DeleteContactResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("result")
    private boolean result;

    public DeleteContactResponse(String message, boolean result) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
