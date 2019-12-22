package com.may.force.sharareh.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.may.force.sharareh.Helper.AppConstants;
import com.may.force.sharareh.Model.PeopleModel;
import com.may.force.sharareh.Model.ResultsPeopleModel;

import java.lang.reflect.Modifier;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IConfigurationApi {
    GsonBuilder builder = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC);
    Gson gson = builder.create();

    Retrofit retrofit =
            new Retrofit.Builder().baseUrl(AppConstants.URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

    Retrofit retrofitFavorite =
            new Retrofit.Builder().baseUrl(AppConstants.URL_FAVORITE).addConverterFactory(GsonConverterFactory.create(gson)).build();

    @GET(AppConstants.PREFIX_URL)
    Call<ResultsPeopleModel> getPeople(@Query("page") int page);

    @POST(AppConstants.PREFIX_URL_FAVORITE)
    Call<Object> saveFavorite(@Body PeopleModel people);


}

