package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Agency {

    @SerializedName("id_agency")
    @Expose
    private String id_agency;

    @SerializedName("nama_agency")
    @Expose
    private String nama_agency;

    public Agency(String id_agency, String nama_agency) {
        this.id_agency = id_agency;
        this.nama_agency = nama_agency;
    }

    public String getId_agency() {
        return id_agency;
    }

    public void setId_agency(String id_agency) {
        this.id_agency = id_agency;
    }

    public String getNama_agency() {
        return nama_agency;
    }

    public void setNama_agency(String nama_agency) {
        this.nama_agency = nama_agency;
    }
}
