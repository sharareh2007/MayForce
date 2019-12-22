package com.may.force.sharareh.View.Main.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.may.force.sharareh.Helper.AppConstants;
import com.may.force.sharareh.Model.PeopleModel;
import com.may.force.sharareh.R;
import com.may.force.sharareh.View.Details.DetailsPeopleActivity;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainHolder  extends RecyclerView.ViewHolder {
    @BindView(R.id.txt_name_people)
    TextView txtNamePeople;
    @BindView(R.id.txt_height)
    TextView txtHeight;
    @BindView(R.id.txt_gender)
    TextView txtGender;
    @BindView(R.id.txt_date_create)
    TextView txtDateCreated;
    @BindView(R.id.txt_date_edited)
    TextView txtDateEdited;

    private Context context;
    private PeopleModel peopleModel;



    public MainHolder(@NonNull View view) {
        super(view);
        ButterKnife.bind(this, view);
        this.context = view.getContext();
    }


    public void bind(PeopleModel people){
        peopleModel = people;
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("dd/MM/yyyy");
        txtNamePeople.setText(people.getName());
        txtHeight.setText(people.getHeight());
        txtGender.setText(people.getGender());
        txtDateCreated.setText(simpleFormatter.format(people.getCreated()));
        txtDateEdited.setText(simpleFormatter.format(people.getEdited()));
    }

    @OnClick(R.id.cv_people)
    void onClickCardView(View view){
        Intent add = new Intent(context, DetailsPeopleActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AppConstants.BUNDLE_PEOPLE, peopleModel);
        add.putExtras(bundle);
        context.startActivity(add);
    }


}
