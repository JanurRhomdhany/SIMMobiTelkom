package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengembalian {


    @SerializedName("id_pengembalian")
    @Expose
    private String id_pengembalian;
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
    @SerializedName("jam_pengembalian")
    @Expose
    private String jam_pengembalian;
    @SerializedName("status_pengembalian")
    @Expose
    private String status_pengembalian;

    public Pengembalian(String id_pengembalian, String tanggal, String plat_mobil, String nama_sales, String nama_agency, String nama_lokasi, String jam_pengembalian, String status_pengembalian, String ket_kegiatan, String bukti_kegiatan, String ket_status) {
        this.id_pengembalian = id_pengembalian;
        this.tanggal = tanggal;
        this.plat_mobil = plat_mobil;
        this.nama_sales = nama_sales;
        this.nama_agency = nama_agency;
        this.nama_lokasi = nama_lokasi;
        this.jam_pengembalian = jam_pengembalian;
        this.status_pengembalian = status_pengembalian;
        this.ket_kegiatan = ket_kegiatan;
        this.bukti_kegiatan = bukti_kegiatan;
        this.ket_status = ket_status;
    }

    public String getId_pengembalian() {
        return id_pengembalian;
    }

    public void setId_pengembalian(String id_pengembalian) {
        this.id_pengembalian = id_pengembalian;
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

    public String getJam_pengembalian() {
        return jam_pengembalian;
    }

    public void setJam_pengembalian(String jam_pengembalian) {
        this.jam_pengembalian = jam_pengembalian;
    }

    public String getStatus_pengembalian() {
        return status_pengembalian;
    }

    public void setStatus_pengembalian(String status_pengembalian) {
        this.status_pengembalian = status_pengembalian;
    }

    public String getKet_kegiatan() {
        return ket_kegiatan;
    }

    public void setKet_kegiatan(String ket_kegiatan) {
        this.ket_kegiatan = ket_kegiatan;
    }

    public String getBukti_kegiatan() {
        return bukti_kegiatan;
    }

    public void setBukti_kegiatan(String bukti_kegiatan) {
        this.bukti_kegiatan = bukti_kegiatan;
    }

    public String getKet_status() {
        return ket_status;
    }

    public void setKet_status(String ket_status) {
        this.ket_status = ket_status;
    }

    @SerializedName("ket_kegiatan")
    @Expose
    private String ket_kegiatan;
    @SerializedName("bukti_kegiatan")
    @Expose
    private String bukti_kegiatan;
    @SerializedName("ket_status")
    @Expose
    private String ket_status;
}
