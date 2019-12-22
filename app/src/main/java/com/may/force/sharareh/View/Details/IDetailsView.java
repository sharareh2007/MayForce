package com.may.force.sharareh.View.Details;

import android.text.SpannableString;

import java.util.List;

public interface IDetailsView {
    void showValuesPeople(String name, String height, String mass, String hairColor, String skinColor,
                          String eyeColor, String birthYear, String gender, SpannableString homeworld,
                          String dateCreated, String dateEdited, List<String> listFilms, List<String> listSpecies,
                          List<String> listVehicles, List<String> listStarships);
    void sucessSaveFavorite(String message);
    void error(String error);

}
