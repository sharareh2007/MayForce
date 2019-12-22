package com.may.force.sharareh.Presenter.Details;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.view.View;

import com.may.force.sharareh.API.IConfigurationApi;
import com.may.force.sharareh.API.IRequestApi;
import com.may.force.sharareh.API.RequestApi;
import com.may.force.sharareh.Helper.AppConstants;
import com.may.force.sharareh.Helper.Util;
import com.may.force.sharareh.Model.PeopleModel;
import com.may.force.sharareh.R;
import com.may.force.sharareh.View.Details.IDetailsView;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPresenter implements IDetailsPresenter {
    private Context context;
    private PeopleModel people;
    private IDetailsView detailsView;
    private IRequestApi requestApi;

    public DetailsPresenter(Context context, IDetailsView detailsView) {
        this.context = context;
        this.detailsView = detailsView;
    }

    @Override
    public void getPeople(Intent intent) {
        if (intent.getExtras() != null) {
            people = (PeopleModel) intent.getExtras().getSerializable(AppConstants.BUNDLE_PEOPLE);
            if (people != null) {
                SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy");


                detailsView.showValuesPeople(people.getName(), people.getHeight(), people.getMass(), people.getColorHair(),
                        people.getColorSkin(), people.getColorEye(), people.getYearBirth(), people.getGender(),
                        prepareLink(people.getHomeworld()), simpleFormatter.format(people.getCreated()),
                        simpleFormatter.format(people.getEdited()), people.getListFilms(), people.getListSpecies(),
                        people.getListVehicles(), people.getListStarships());
            } else {
                detailsView.error("");
            }
        } else {
            detailsView.error("");
        }
    }

    private SpannableString prepareLink(final String url) {
        SpannableString spannableStringLink = new SpannableString(context.getString(R.string.label_link_one));
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        };
        spannableStringLink.setSpan(clickableSpan, 0,
                spannableStringLink.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return spannableStringLink;
    }

    @Override
    public void saveFavorite() {
        if (Util.hasConnectivity(context)){
            requestApi = new RequestApi(IConfigurationApi.retrofitFavorite.create(IConfigurationApi.class));
            try {
                requestApi.savePeopleFavorite(people).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        detailsView.sucessSaveFavorite(context.getString(R.string.label_message_sucess));
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        detailsView.error(context.getString(R.string.label_message_error_generic));
                    }
                });
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }else {
            detailsView.error(context.getString(R.string.label_message_error_internet));
        }

    }
}
