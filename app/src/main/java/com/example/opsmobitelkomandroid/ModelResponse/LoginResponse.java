package com.example.opsmobitelkomandroid.ModelResponse;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponse implements Serializable {


    @SerializedName("salesLogin")
    Login login;
    @SerializedName("status")
    Boolean status;
    @SerializedName("message")
    String message;

    public LoginResponse(Login login, Boolean status, String message) {
        this.login = login;
        this.status = status;
        this.message = message;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Boolean getStatus(Boolean status) {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
