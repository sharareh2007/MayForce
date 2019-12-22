package com.may.force.sharareh.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultsPeopleModel {
    @SerializedName("results")
    private List<PeopleModel> listPeople;

    public List<PeopleModel> getListPeople() {
        return listPeople;
    }
}
