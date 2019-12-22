package com.may.force.sharareh.API;

import com.may.force.sharareh.Model.PeopleModel;
import com.may.force.sharareh.Model.ResultsPeopleModel;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;

public interface IRequestApi {
    Call<ResultsPeopleModel> getPeopleRequest(int page) throws NoSuchAlgorithmException;
    Call<Object> savePeopleFavorite(PeopleModel peopleModel) throws NoSuchAlgorithmException;
}
