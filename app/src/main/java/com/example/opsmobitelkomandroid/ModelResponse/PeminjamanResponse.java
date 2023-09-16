package com.example.opsmobitelkomandroid.ModelResponse;

public class PeminjamanResponse {

    public PeminjamanResponse(Peminjaman peminjaman, String status, String message) {
        this.peminjaman = peminjaman;
        this.status = status;
        this.message = message;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Peminjaman peminjaman;
    String status, message;
}
