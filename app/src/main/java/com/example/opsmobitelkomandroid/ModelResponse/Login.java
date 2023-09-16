package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("id_sales")
    @Expose
    private String idSales;
    @SerializedName("password")
    @Expose
    private String passSales;

    @SerializedName("nama_sales")
    @Expose
    private String namaSales;

    @SerializedName("nama_agency")
    @Expose
    private String namaAgency;

    @SerializedName("id_agency")
    @Expose
    private String idAgency;

    public Login(String idSales, String passSales, String namaSales, String namaAgency, String idAgency) {
        this.idSales = idSales;
        this.passSales = passSales;
        this.namaSales = namaSales;
        this.namaAgency = namaAgency;
        this.idAgency = idAgency;
    }

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    public String getNamaSales() {
        return namaSales;
    }

    public void setNamaSales(String namaSales) {
        this.namaSales = namaSales;
    }

    public String getNamaAgency() {
        return namaAgency;
    }

    public void setNamaAgency(String namaAgency) {
        this.namaAgency = namaAgency;
    }

    public String getIdSales() {
        return idSales;
    }

    public void setIdSales(String idSales) {
        this.idSales = idSales;
    }

    public String getPassSales() {
        return passSales;
    }

    public void setPassSales(String passSales) {
        this.passSales = passSales;
    }



}
