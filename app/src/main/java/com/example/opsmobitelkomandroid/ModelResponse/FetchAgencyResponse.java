package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchAgencyResponse {

    @SerializedName("agency")
    List<Agency> agencyList;

    String message;

    public FetchAgencyResponse(List<Agency> agencyList, String message) {
        this.agencyList = agencyList;
        this.message = message;
    }

    public List<Agency> getAgencyList() {
        return agencyList;
    }

    public void setAgencyList(List<Agency> agencyList) {
        this.agencyList = agencyList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
