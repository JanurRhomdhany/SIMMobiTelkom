package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sales {

    public Sales(String id_sales, String nama_sales, String id_agency, String no_telp, String nama_agency) {
        this.id_sales = id_sales;
        this.nama_sales = nama_sales;
        this.id_agency = id_agency;
        this.no_telp = no_telp;
        this.nama_agency = nama_agency;
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

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getNama_agency() {
        return nama_agency;
    }

    public void setNama_agency(String nama_agency) {
        this.nama_agency = nama_agency;
    }

    @SerializedName("id_sales")
    @Expose
    private String id_sales;


    @SerializedName("nama_sales")
    @Expose
    private String nama_sales;

    @SerializedName("id_agency")
    @Expose
    private String id_agency;

    @SerializedName("no_telp")
    @Expose
    private String no_telp;

    @SerializedName("nama_agency")
    @Expose
    private String nama_agency;


}
