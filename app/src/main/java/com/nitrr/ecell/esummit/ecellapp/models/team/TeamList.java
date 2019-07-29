package com.nitrr.ecell.esummit.ecellapp.models.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TeamList {

    public String getImage() {
        return image;
    }

    @SerializedName("image")
    @Expose
    String image;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("profile_url")
    @Expose
    String url;

    @SerializedName("member_type")
    @Expose
    String type;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    //Executives and Managers ka image and profile_url nai dikhana
}
