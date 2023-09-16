package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kodepinjam {


    public String getKode_pinjam() {
        return kode_pinjam;
    }

    public void setKode_pinjam(String kode_pinjam) {
        this.kode_pinjam = kode_pinjam;
    }

    public Kodepinjam(String kode_pinjam) {
        this.kode_pinjam = kode_pinjam;
    }

    @SerializedName("kode_pinjam")
    @Expose
    private String kode_pinjam;
}
