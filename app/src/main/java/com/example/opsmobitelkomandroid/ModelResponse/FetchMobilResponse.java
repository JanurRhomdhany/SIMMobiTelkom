package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchMobilResponse {

    @SerializedName("mobil")
    List<Mobil> mobilList;

    String message;


    public List<Mobil> getMobilList() {
        return mobilList;
    }

    public void setMobilList(List<Mobil> mobilList) {
        this.mobilList = mobilList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FetchMobilResponse(List<Mobil> mobilList, String message) {
        this.mobilList = mobilList;
        this.message = message;
    }





}
