package com.example.opsmobitelkomandroid.ModelResponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Jadwal {

    @SerializedName("tanggal")
    @Expose
    private String tanggal;
    @SerializedName("plat_mobil")
    @Expose
    private String platMobil;
    @SerializedName("nama_agency")
    @Expose
    private String namaAgency;
    @SerializedName("nama_lokasi")
    @Expose
    private String namaLokasi;

    @SerializedName("id_lokasi")
    @Expose
    private String idLokasi;

    @SerializedName("id_mobil")
    @Expose
    private String idMobil;

    @SerializedName("id_agency")
    @Expose
    private String idAgency;


    public Jadwal(String tanggal, String platMobil, String namaAgency, String namaLokasi, String idLokasi, String idMobil, String idAgency) {
        this.tanggal = tanggal;
        this.platMobil = platMobil;
        this.namaAgency = namaAgency;
        this.namaLokasi = namaLokasi;
        this.idLokasi = idLokasi;
        this.idMobil = idMobil;
        this.idAgency = idAgency;
    }

    public String getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(String idLokasi) {
        this.idLokasi = idLokasi;
    }

    public String getIdMobil() {
        return idMobil;
    }

    public void setIdMobil(String idMobil) {
        this.idMobil = idMobil;
    }

    public String getIdAgency() {
        return idAgency;
    }

    public void setIdAgency(String idAgency) {
        this.idAgency = idAgency;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPlatMobil() {
        return platMobil;
    }

    public void setPlatMobil(String platMobil) {
        this.platMobil = platMobil;
    }

    public String getNamaAgency() {
        return namaAgency;
    }

    public void setNamaAgency(String namaAgency) {
        this.namaAgency = namaAgency;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }
}
