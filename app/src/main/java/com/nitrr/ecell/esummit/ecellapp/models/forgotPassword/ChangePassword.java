package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassword {

    public ChangePassword(String email, String password, String otp) {
        this.email = email;
        this.password = password;
        this.otp = otp;
    }

    @SerializedName("Access")
    @Expose
    String apptoken ="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnQiOiJhbmRyb2lkIiwib3JnYW5pemF0aW9uIjoiRUNlbGwifQ.H2aaDJuOxK44D2kwRCWwv9s5rzJGCNYKT3thtQqN-hQ";

    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;

<<<<<<< HEAD


    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
=======
    @SerializedName("otp")
    @Expose
    String otp;
>>>>>>> 719965933b7f70d84fad24bea03b6e300ba40834
}
