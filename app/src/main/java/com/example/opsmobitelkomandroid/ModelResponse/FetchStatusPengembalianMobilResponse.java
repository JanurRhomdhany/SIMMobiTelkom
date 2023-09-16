package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchStatusPengembalianMobilResponse {

    @SerializedName("pengembalian")
    List<Pengembalian> pengembalianList;

    String message;

    public FetchStatusPengembalianMobilResponse(List<Pengembalian> pengembalianList, String message) {
        this.pengembalianList = pengembalianList;
        this.message = message;
    }

    public List<Pengembalian> getPengembalianList() {
        return pengembalianList;
    }

    public void setPengembalianList(List<Pengembalian> pengembalianList) {
        this.pengembalianList = pengembalianList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
