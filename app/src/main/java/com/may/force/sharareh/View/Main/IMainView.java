package com.may.force.sharareh.View.Main;

import com.may.force.sharareh.Model.PeopleModel;

import java.util.List;

public interface IMainView {
    void resultListPeople(List<PeopleModel> peopleList);
    void resultListPeopleNextPage(List<PeopleModel> peopleList);
    void errorRequest(String error);
}
