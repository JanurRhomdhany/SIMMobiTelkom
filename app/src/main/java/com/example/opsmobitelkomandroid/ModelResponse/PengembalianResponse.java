package com.example.opsmobitelkomandroid.ModelResponse;

public class PengembalianResponse {
    Pengembalian pengembalian;
    String status;
    String message;

    public Pengembalian getPengembalian() {
        return pengembalian;
    }

    public void setPengembalian(Pengembalian pengembalian) {
        this.pengembalian = pengembalian;
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


    public PengembalianResponse(Pengembalian pengembalian, String status, String message) {
        this.pengembalian = pengembalian;
        this.status = status;
        this.message = message;
    }


}
