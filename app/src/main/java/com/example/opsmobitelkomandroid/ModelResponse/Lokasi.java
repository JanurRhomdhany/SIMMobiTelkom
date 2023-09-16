package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lokasi {

    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }

    public Lokasi(String id_lokasi, String nama_lokasi) {
        this.id_lokasi = id_lokasi;
        this.nama_lokasi = nama_lokasi;
    }

    @SerializedName("id_lokasi")
    @Expose
    private String id_lokasi;

    @SerializedName("nama_lokasi")
    @Expose
    private String nama_lokasi;
}
