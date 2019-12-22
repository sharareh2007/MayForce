package com.may.force.sharareh.View.Main.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;


import com.may.force.sharareh.Model.PeopleModel;
import com.may.force.sharareh.R;
import com.may.force.sharareh.View.Main.Adapter.MainHolder;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainHolder> implements Filterable  {

    private List<PeopleModel> people;
    private List<PeopleModel> peopleFiltered;



    public MainAdapter(List<PeopleModel> people) {
        this.people = people;
        this.peopleFiltered = people;

    }


    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_adapter, parent, false);
        return new MainHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull MainHolder mainHolder, int position) {
        PeopleModel people = peopleFiltered.get(position);
        mainHolder.bind(people);
    }

    @Override
    public int getItemCount() {
        return peopleFiltered != null ? peopleFiltered.size() : 0;
    }


    public void updateList(List<PeopleModel> peopleUpdate) {
        this.people.addAll(peopleUpdate);
        notifyDataSetChanged();
    }



    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    peopleFiltered = people;
                } else {
                    List<PeopleModel> filteredList = new ArrayList<>();
                    for (PeopleModel peopleModel : people) {
                        if (peopleModel.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(peopleModel);
                        }
                    }
                    peopleFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = peopleFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                peopleFiltered = (ArrayList<PeopleModel>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}