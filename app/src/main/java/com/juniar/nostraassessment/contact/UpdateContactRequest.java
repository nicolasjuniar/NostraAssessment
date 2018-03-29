package com.juniar.nostraassessment.contact;

import com.google.gson.annotations.SerializedName;

public class UpdateContactRequest {
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("version")
    private int version;
}
