package com.nitrr.ecell.esummit.ecellapp.misc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPref {

    private Activity activity;

    private static String accessToken = "",
            firstName = "",
            lastName = "",
            email = "",
            contact = "",
            avatar = "",
            facebook = "",
            linkedin = "";

    private static boolean isLoggedIn = false,
            isFBLoggedIn = false,
            isGLoggedIn = false;

    public void setSharedPref(Activity activity, String access_token,
                              String firstName, String lastName,
                              String email, String contact,
                              String avatar, String facebook,
                              String linkedin, boolean isLoggedIn,
                              boolean isFBLoggedIn, boolean isGLoggedIn) {
        this.activity = activity;
        SharedPref.accessToken = access_token;
        SharedPref.firstName = firstName;
        SharedPref.lastName = lastName;
        SharedPref.email = email;
        SharedPref.contact = contact;
        SharedPref.avatar = avatar;
        SharedPref.facebook = facebook;
        SharedPref.linkedin = linkedin;
        SharedPref.isLoggedIn = isLoggedIn;
        SharedPref.isFBLoggedIn = isFBLoggedIn;
        SharedPref.isGLoggedIn = isGLoggedIn;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString("token", access_token);
        editor.putString("first_name", firstName);
        editor.putString("last_name", lastName);
        editor.putString("email", email);
        editor.putString("contact", contact);
        editor.putString("avatar", avatar);
        editor.putString("facebook", facebook);
        editor.putString("linkedin", linkedin);
        editor.putBoolean("isLoggedIn", isLoggedIn);
        editor.putBoolean("isFBLoggedIn", isFBLoggedIn);
        editor.putBoolean("isGLoggedIn", isGLoggedIn);

        editor.apply();
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static String getContact() {
        return contact;
    }

    public static String getAvatar() {
        return avatar;
    }

    public static String getFacebook() {
        return facebook;
    }

    public static String getLinkedin() {
        return linkedin;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean isFBLoggedIn() {
        return isFBLoggedIn;
    }

    public boolean isGLoggedIn() {
        return isGLoggedIn;
    }

    public static SharedPreferences.Editor getEditor(Activity activity) {
        return PreferenceManager.getDefaultSharedPreferences(activity).edit();
    }

    public void clearPrefs() {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(activity).edit();
        editor.clear();
        editor.apply();
    }

    public static void setAccessToken(String accessToken) {
        SharedPref.accessToken = accessToken;
    }
}
