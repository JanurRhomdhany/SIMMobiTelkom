package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchStatusPeminjamanMobilResponse {


    public FetchStatusPeminjamanMobilResponse(List<Peminjaman> peminjamanList, String message) {
        this.peminjamanList = peminjamanList;
        this.message = message;
    }

    public List<Peminjaman> getPeminjamanList() {
        return peminjamanList;
    }

    public void setPeminjamanList(List<Peminjaman> peminjamanList) {
        this.peminjamanList = peminjamanList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("peminjaman")
    List<Peminjaman> peminjamanList;

    String message;
}
