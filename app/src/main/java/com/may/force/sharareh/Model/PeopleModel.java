package com.may.force.sharareh.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PeopleModel implements Serializable {
    @SerializedName("name")
    private String name;
    @SerializedName("height")
    private String height;
    @SerializedName("mass")
    private String mass;
    @SerializedName("hair_color")
    private String colorHair;
    @SerializedName("skin_color")
    private String colorSkin;
    @SerializedName("eye_color")
    private String colorEye;
    @SerializedName("birth_year")
    private String yearBirth;
    @SerializedName("gender")
    private String gender;
    @SerializedName("homeworld")
    private String homeworld;
    @SerializedName("films")
    private List<String> listFilms;
    @SerializedName("species")
    private List<String> listSpecies;
    @SerializedName("vehicles")
    private List<String> listVehicles;
    @SerializedName("starships")
    private List<String> listStarships;
    @SerializedName("created")
    private Date created;
    @SerializedName("edited")
    private Date edited;
    @SerializedName("url")
    private String url;


    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getColorHair() {
        return colorHair;
    }

    public String getColorSkin() {
        return colorSkin;
    }

    public String getColorEye() {
        return colorEye;
    }

    public String getYearBirth() {
        return yearBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public List<String> getListFilms() {
        return listFilms;
    }

    public List<String> getListSpecies() {
        return listSpecies;
    }

    public List<String> getListVehicles() {
        return listVehicles;
    }

    public List<String> getListStarships() {
        return listStarships;
    }

    public Date getCreated() {
        return created;
    }

    public Date getEdited() {
        return edited;
    }

    public String getUrl() {
        return url;
    }
}
