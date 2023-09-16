package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mobil {

    public Mobil(String id_mobil, String plat_mobil, String status_mobil) {
        this.id_mobil = id_mobil;
        this.plat_mobil = plat_mobil;
        this.status_mobil = status_mobil;
    }

    @SerializedName("id_mobil")
    @Expose
    private String id_mobil;
    @SerializedName("plat_mobil")
    @Expose
    private String plat_mobil;
    @SerializedName("status")
    @Expose
    private String status_mobil;



    public String getId_mobil() {
        return id_mobil;
    }

    public void setId_mobil(String id_mobil) {
        this.id_mobil = id_mobil;
    }

    public String getPlat_mobil() {
        return plat_mobil;
    }

    public void setPlat_mobil(String plat_mobil) {
        this.plat_mobil = plat_mobil;
    }

    public String getStatus_mobil() {
        return status_mobil;
    }

    public void setStatus_mobil(String status_mobil) {
        this.status_mobil = status_mobil;
    }



}
