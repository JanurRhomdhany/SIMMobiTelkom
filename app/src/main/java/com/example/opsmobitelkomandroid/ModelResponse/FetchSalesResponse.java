package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FetchSalesResponse {

    @SerializedName("sales")
    List<Sales> salesList;

    public FetchSalesResponse(List<Sales> salesList, String message) {
        this.salesList = salesList;
        this.message = message;
    }

    String message;

    public List<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
