package com.may.force.sharareh.View.Details;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.may.force.sharareh.Helper.Util;
import com.may.force.sharareh.Presenter.Details.DetailsPresenter;
import com.may.force.sharareh.Presenter.Details.IDetailsPresenter;
import com.may.force.sharareh.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsPeopleActivity extends AppCompatActivity implements IDetailsView{

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.txt_name_people)
    TextView txtNamePeolpe;
    @BindView(R.id.txt_height)
    TextView txtHeight;
    @BindView(R.id.txt_mass)
    TextView txtMass;
    @BindView(R.id.txt_hair_color)
    TextView txtHairColor;
    @BindView(R.id.txt_skin_color)
    TextView txtSkinColor;
    @BindView(R.id.txt_eye_color)
    TextView txtEyeColor;
    @BindView(R.id.txt_birth_year)
    TextView txtBirthYear;
    @BindView(R.id.txt_gender)
    TextView txtGender;
    @BindView(R.id.txt_homeworld)
    TextView txtHomeworld;
    @BindView(R.id.txt_date_created)
    TextView txtDateCreated;
    @BindView(R.id.txt_date_edited)
    TextView txtDateEdited;
    @BindView(R.id.txt_films)
    TextView txtFilms;
    @BindView(R.id.txt_species)
    TextView txtSpecies;
    @BindView(R.id.txt_vehicles)
    TextView txtVehicles;
    @BindView(R.id.txt_starships)
    TextView txtStarships;

    private IDetailsPresenter presenter;
    private MenuItem itemFavorite;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        showProgressDialog();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new DetailsPresenter(this, this);
        presenter.getPeople(getIntent());
    }

    private void saveFavoritePeople() {
        showProgressDialog();
        presenter.saveFavorite();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        itemFavorite = menu.findItem(R.id.action_favorite);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_favorite:
                saveFavoritePeople();
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void showValuesPeople(String name, String height, String mass, String hairColor, String skinColor,
                                 String eyeColor, String birthYear, String gender,
                                 SpannableString homeworld, String dateCreated, String dateEdited,
                                 List<String> listFilms, List<String> listSpecies,
                                 List<String> listVehicles, List<String> listStarships) {

        txtNamePeolpe.setText(name);
        txtHeight.setText(height);
        txtMass.setText(mass);
        txtHairColor.setText(hairColor);
        txtSkinColor.setText(skinColor);
        txtEyeColor.setText(eyeColor);
        txtBirthYear.setText(birthYear);
        txtGender.setText(gender);
        txtHomeworld.setText(homeworld, TextView.BufferType.SPANNABLE);
        txtHomeworld.setMovementMethod(LinkMovementMethod.getInstance());
        txtDateCreated.setText(dateCreated);
        txtDateEdited.setText(dateEdited);

        StringBuilder stringBuilder = new StringBuilder();
        for (String film : listFilms) {
            stringBuilder.append(film);
            stringBuilder.append("\n");
        }
        txtFilms.setText(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        for (String specie : listSpecies) {
            stringBuilder.append(specie);
            stringBuilder.append("\n");
        }
        txtSpecies.setText(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        for (String vehicles : listVehicles) {
            stringBuilder.append(vehicles);
            stringBuilder.append("\n");
        }
        txtVehicles.setText(stringBuilder.toString());

        stringBuilder = new StringBuilder();
        for (String starships : listStarships) {
            stringBuilder.append(starships);
            stringBuilder.append("\n");
        }
        txtStarships.setText(stringBuilder.toString());

        hideLoading();
    }


    @Override
    public void sucessSaveFavorite(String message) {
        hideLoading();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        itemFavorite.setIcon(ContextCompat.getDrawable(this, android.R.drawable.star_big_on));
    }



    @Override
    public void error(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        hideLoading();
    }


    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", getString(R.string.label_loading));
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void hideLoading() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
    }
}
