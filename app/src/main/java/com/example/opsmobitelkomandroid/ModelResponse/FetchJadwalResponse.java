package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchJadwalResponse {

    @SerializedName("jadwal")
    List<Jadwal> jadwalList;
    String message;

    public List<Jadwal> getJadwalList() {
        return jadwalList;
    }

    public void setJadwalList(List<Jadwal> jadwalList) {
        this.jadwalList = jadwalList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FetchJadwalResponse(List<Jadwal> jadwalList, String message) {
        this.jadwalList = jadwalList;
        this.message = message;


    }
}
