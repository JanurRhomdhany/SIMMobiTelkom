package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Peminjaman {

    public Peminjaman(String id_peminjaman, String tanggal, String plat_mobil, String nama_sales, String nama_agency, String nama_lokasi, String jam_peminjaman, String status_peminjaman, String keterangan, String idSales, String idMobil, String idLokasi, String idAgency) {
        this.id_peminjaman = id_peminjaman;
        this.tanggal = tanggal;
        this.plat_mobil = plat_mobil;
        this.nama_sales = nama_sales;
        this.nama_agency = nama_agency;
        this.nama_lokasi = nama_lokasi;
        this.jam_peminjaman = jam_peminjaman;
        this.status_peminjaman = status_peminjaman;
        this.keterangan = keterangan;
        this.idSales = idSales;
        this.idMobil = idMobil;
        this.idLokasi = idLokasi;
        this.idAgency = idAgency;
    }

    public String getId_peminjaman() {
        return id_peminjaman;
    }

    public void setId_peminjaman(String id_peminjaman) {
        this.id_peminjaman = id_peminjaman;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPlat_mobil() {
        return plat_mobil;
    }

    public void setPlat_mobil(String plat_mobil) {
        this.plat_mobil = plat_mobil;
    }

    public String getNama_sales() {
        return nama_sales;
    }

    public void setNama_sales(String nama_sales) {
        this.nama_sales = nama_sales;
    }

    public String getNama_agency() {
        return nama_agency;
    }

    public void setNama_agency(String nama_agency) {
        this.nama_agency = nama_agency;
    }

    public String getNama_lokasi() {
        return nama_lokasi;
    }

    public void setNama_lokasi(String nama_lokasi) {
        this.nama_lokasi = nama_lokasi;
    }

    public String getJam_peminjaman() {
        return jam_peminjaman;
    }

    public void setJam_peminjaman(String jam_peminjaman) {
        this.jam_peminjaman = jam_peminjaman;
    }

    public String getStatus_peminjaman() {
        return status_peminjaman;
    }

    public void setStatus_peminjaman(String status_peminjaman) {
        this.status_peminjaman = status_peminjaman;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIdSales() {
        return idSales;
    }

    public void setIdSales(String idSales) {
        this.idSales = idSales;
    }

    public String getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(String idMobil) {
        this.idMobil = idMobil;
    }

    public String getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(String idLokasi) {
        this.idLokasi = idLokasi;
    }

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    @SerializedName("id_peminjaman")
    @Expose
    private String id_peminjaman;
    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("plat_mobil")
    @Expose
    private String plat_mobil;
    @SerializedName("nama_sales")
    @Expose
    private String nama_sales;
    @SerializedName("nama_agency")
    @Expose
    private String nama_agency;
    @SerializedName("nama_lokasi")
    @Expose
    private String nama_lokasi;
    @SerializedName("jam_peminjaman")
    @Expose
    private String jam_peminjaman;
    @SerializedName("status_peminjaman")
    @Expose
    private String status_peminjaman;

    @SerializedName("ket")
    @Expose
    private String keterangan;

    @SerializedName("id_sales")
    @Expose
    private String idSales;

    @SerializedName("id_mobil")
    @Expose
    private String idMobil;

    @SerializedName("id_lokasi")
    @Expose
    private String idLokasi;

    @SerializedName("id_agency")
    @Expose
    private String idAgency;
}
