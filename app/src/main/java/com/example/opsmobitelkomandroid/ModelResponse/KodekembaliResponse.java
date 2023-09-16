package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

public class KodekembaliResponse {

    @SerializedName("id_pengembalian")
    String idPengembalian;

    public String getLatestIdPengembalian(){
        return idPengembalian;
    }
}
