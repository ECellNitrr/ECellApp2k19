package com.nitrr.ecell.esummit.ecellapp.models.VerifyNumber;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendNumber {

    @SerializedName("Access")
    @Expose
    String apptoken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnQiOiJhbmRyb2lkIiwib3JnYW5pemF0aW9uIjoiRUNlbGwifQ.H2aaDJuOxK44D2kwRCWwv9s5rzJGCNYKT3thtQqN-hQ";

    @SerializedName("token")
    @Expose
    String token;

    @SerializedName("otp")
    @Expose
    String otp;

    public SendNumber(String token) {
        this.token = token;
    }
}
