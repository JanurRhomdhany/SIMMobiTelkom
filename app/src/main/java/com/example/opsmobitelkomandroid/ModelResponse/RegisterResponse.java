package com.example.opsmobitelkomandroid.ModelResponse;

public class RegisterResponse {

    Register register;
    String status, message;

    public RegisterResponse(Register register, String status, String message) {
        this.register = register;
        this.status = status;
        this.message = message;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
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
}
