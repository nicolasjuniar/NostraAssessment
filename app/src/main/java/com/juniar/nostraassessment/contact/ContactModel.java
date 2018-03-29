package com.juniar.nostraassessment.contact;

import com.google.gson.annotations.SerializedName;

public class ContactModel {
    @SerializedName("uid")
    private int uid;
    @SerializedName("id")
    private String id;
    @SerializedName("version")
    private int version;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("phone")
    private String phone;
    @SerializedName("email")
    private String email;
    @SerializedName("picture")
    private String picture;

    public ContactModel(int uid, String id, int version, String name, String address, String phone, String email, String picture) {
        this.uid = uid;
        this.id = id;
        this.version = version;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.picture = picture;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
