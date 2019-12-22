package com.may.force.sharareh.Presenter.Main;

import android.content.Context;

import com.may.force.sharareh.API.IConfigurationApi;
import com.may.force.sharareh.API.IRequestApi;
import com.may.force.sharareh.API.RequestApi;
import com.may.force.sharareh.Helper.Util;
import com.may.force.sharareh.Model.ResultsPeopleModel;
import com.may.force.sharareh.R;
import com.may.force.sharareh.View.Main.IMainView;

import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainPresenter {
    private Context context;
    private IMainView mainView;
    private IRequestApi requestApi;

    public MainPresenter(Context context, IMainView mainView) {
        this.context = context;
        this.mainView = mainView;
        requestApi = new RequestApi(IConfigurationApi.retrofit.create(IConfigurationApi.class));
    }

    @Override
    public void getPeople(int page, final boolean isNextPage) {

        if (Util.hasConnectivity(context)) {
            try {
                requestApi.getPeopleRequest(page).enqueue(new Callback<ResultsPeopleModel>() {
                    @Override
                    public void onResponse(Call<ResultsPeopleModel> call, Response<ResultsPeopleModel> response) {
                        if (response.body() != null) {
                            if (isNextPage) {
                                mainView.resultListPeopleNextPage(response.body().getListPeople());
                            } else {
                                mainView.resultListPeople(response.body().getListPeople());
                            }
                        } else {
                            mainView.resultListPeopleNextPage(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultsPeopleModel> call, Throwable t) {
                        mainView.errorRequest(context.getString(R.string.label_message_error_generic));
                    }
                });
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        } else {
            mainView.errorRequest(context.getString(R.string.label_message_error_internet));
        }
    }

}