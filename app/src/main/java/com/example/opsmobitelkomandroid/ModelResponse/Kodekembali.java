package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kodekembali {
    public String getKode_kembali() {
        return kode_kembali;
    }

    public void setKode_kembali(String kode_kembali) {
        this.kode_kembali = kode_kembali;
    }

    public Kodekembali(String kode_kembali) {
        this.kode_kembali = kode_kembali;
    }

    @SerializedName("kode_kembali")
    @Expose
    private String kode_kembali;
}
