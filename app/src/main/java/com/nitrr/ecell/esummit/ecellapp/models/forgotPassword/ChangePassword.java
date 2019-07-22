package com.nitrr.ecell.esummit.ecellapp.models.forgotPassword;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangePassword {
    @SerializedName("email")
    @Expose
    String email;

    @SerializedName("password")
    @Expose
    String password;

    @SerializedName("otp")
    @Expose
    String otp;

    public ChangePassword(String email, String password, String otp) {
        this.email = email;
        this.password = password;
        this.otp = otp;
    }
}
