package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class KodepinjamResponse {

    @SerializedName("id_peminjaman")
    String idPeminjaman;

    public String getLatestId(){
        return idPeminjaman;
    }




}
