package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchLokasiResponse {

    @SerializedName("lokasi")
    List<Lokasi> lokasiList;

    String message;

    public FetchLokasiResponse(List<Lokasi> lokasiList, String message) {
        this.lokasiList = lokasiList;
        this.message = message;
    }



    public List<Lokasi> getLokasiList() {
        return lokasiList;
    }

    public void setLokasiList(List<Lokasi> lokasiList) {
        this.lokasiList = lokasiList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
