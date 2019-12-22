package com.may.force.sharareh.API;

import com.may.force.sharareh.Model.PeopleModel;
import com.may.force.sharareh.Model.ResultsPeopleModel;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;

public class RequestApi implements IRequestApi {
    private IConfigurationApi configurationApi;

    public RequestApi(IConfigurationApi configurationApi){
        this.configurationApi = configurationApi;
    }

    @Override
    public Call<ResultsPeopleModel> getPeopleRequest(int page) throws NoSuchAlgorithmException {
        return configurationApi.getPeople(page);
    }

    @Override
    public Call<Object> savePeopleFavorite(PeopleModel peopleModel) throws NoSuchAlgorithmException {
        return configurationApi.saveFavorite(peopleModel);
    }
}