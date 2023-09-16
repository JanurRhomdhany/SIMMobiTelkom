package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("id_sales")
    @Expose
    private String id_sales;
    @SerializedName("nama_sales")
    @Expose
    private String nama_sales;
    @SerializedName("id_agency")
    @Expose
    private String id_agency;
    @SerializedName("password")
    @Expose
    private String password;

    public Register(String id_sales, String nama_sales, String id_agency, String password) {
        this.id_sales = id_sales;
        this.nama_sales = nama_sales;
        this.id_agency = id_agency;
        this.password = password;
    }



    public String getId_sales() {
        return id_sales;
    }

    public void setId_sales(String id_sales) {
        this.id_sales = id_sales;
    }

    public String getNama_sales() {
        return nama_sales;
    }

    public void setNama_sales(String nama_sales) {
        this.nama_sales = nama_sales;
    }

    public String getId_agency() {
        return id_agency;
    }

    public void setId_agency(String id_agency) {
        this.id_agency = id_agency;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
