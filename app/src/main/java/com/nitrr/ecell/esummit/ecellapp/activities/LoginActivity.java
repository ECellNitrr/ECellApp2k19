package com.nitrr.ecell.esummit.ecellapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nitrr.ecell.esummit.ecellapp.R;
import com.nitrr.ecell.esummit.ecellapp.misc.Animation.LoginAnimation;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;
import com.nitrr.ecell.esummit.ecellapp.misc.Utils;
import com.nitrr.ecell.esummit.ecellapp.models.auth.LoginDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.RegisterDetails;
import com.nitrr.ecell.esummit.ecellapp.models.auth.AuthResponse;
import com.nitrr.ecell.esummit.ecellapp.restapi.APIServices;
import com.nitrr.ecell.esummit.ecellapp.restapi.AppClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity{

    Context context;
    private boolean isLoggingIn;
    RegisterDetails details;
    ImageView lowerIcon, fbButton, googleButton;
    Button signin,register;
    TextView toSignIn, toRegister;
    EditText loginEmail, loginPassword;
    EditText firstName, lastName, registerUsername, registerPassword, registerEmail, mobileNumber;
    LinearLayout loginLayout, registerlayout;
    LoginAnimation loginanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        initializeUserStatus();
        context = this;
        loginanimation = new LoginAnimation(this);
        loginanimation.toSignInScreen(this);

        signin.setOnClickListener((View v) -> {
            signin.setEnabled(false);
            LoginApiCall();
        });

        register.setOnClickListener((View v) -> {
            register.setEnabled(false);
            RegisterApiCall();
        });

        toRegister.setOnClickListener((View v) -> {
            isLoggingIn = false;
            loginanimation.toRegisterScreen(this);
        });

        toSignIn.setOnClickListener((View v) -> {
            loginanimation.toSignInScreen(this);
            isLoggingIn = true;
        });
//
//        fbButton.setOnClickListener((View v) -> { /*Write Here*/ });
//
//        googleButton.setOnClickListener((View v) -> { /*Write Here*/ });
    }

    public void initializeViews(){
        toSignIn = findViewById(R.id.to_sign_in);
        toRegister = findViewById(R.id.to_register);

        loginLayout = findViewById(R.id.login_linear_layout);
        registerlayout = findViewById(R.id.register_linear_layout);
        lowerIcon = findViewById(R.id.ic_lower_ecell);

        signin = findViewById(R.id.sign_in_button);
        register = findViewById(R.id.register_button);

        fbButton = findViewById(R.id.fb_button);
        googleButton = findViewById(R.id.google_button);

        firstName = findViewById(R.id.register_first_name);
        lastName = findViewById(R.id.register_last_name);
        registerPassword = findViewById(R.id.register_password);
        registerEmail = findViewById(R.id.register_email);
        mobileNumber = findViewById(R.id.register_number);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);

        toSignIn.setVisibility(View.INVISIBLE);
    }

    public void initializeUserStatus() {

    }

    public void RegisterApiCall() {
        RegisterDetails details = new RegisterDetails(firstName.getText().toString(),
                lastName.getText().toString(),
                registerEmail.getText().toString(),
                registerPassword.getText().toString(),
                mobileNumber.getText().toString(),
                null, null, null);

        Call<AuthResponse> call =  AppClient.getInstance().createService(APIServices.class).postRegisterUser(details);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if(!response.isSuccessful()) {
                    Utils.showLongToast(context, "There was an error in post request "+response.toString());
                    register.setEnabled(true);
                    return;
                }

                if (response.body() != null) {
                    if(response.code() == 400) {
                        Utils.showLongToast(context, "Registration Failed");
                        register.setEnabled(true);
                    }
                    else {
                        Utils.showLongToast(context, "User Registered Successfully with token " + response.body().getToken());
//                        startActivity(new Intent(context, HomeActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Utils.showLongToast(context, "There was an error " + t.getMessage());
                register.setEnabled(true);
            }
        });
    }

    public void LoginApiCall() {
        LoginDetails details = new LoginDetails(loginEmail.getText().toString(), loginPassword.getText().toString());

        Call<AuthResponse> call =  AppClient.getInstance().createService(APIServices.class).postLoginUser(details);

        call.enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<AuthResponse> call, @NonNull Response<AuthResponse> response) {
                if(response.code() == 400) {
                    Utils.showLongToast(context, "Wrong username or password!");
                    signin.setEnabled(true);
                }

                else {
                    if(response.isSuccessful()) {
                        if (response.body() != null) {
                            if(!response.body().getMessage().equals("Login successful!")) {
                                Utils.showLongToast(context, "There was an error in logging in " + response.code());
                                signin.setEnabled(true);
                            }
                            else {
                                Utils.showLongToast(context, "User Logged In Successfully with token " + response.body().getToken());
                                SharedPref.setAccessToken(response.body().getToken());
                                signin.setEnabled(true);
//                                startActivity(new Intent(context, HomeActivity.class));
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<AuthResponse> call, @NonNull Throwable t) {
                Utils.showLongToast(context, "There was an error " + t.getMessage());
                signin.setEnabled(true);
            }
        });
    }
}
