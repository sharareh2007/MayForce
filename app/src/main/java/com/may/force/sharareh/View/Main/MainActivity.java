package com.may.force.sharareh.View.Main;



import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.may.force.sharareh.Helper.Util;
import com.may.force.sharareh.Model.PeopleModel;
import com.may.force.sharareh.Presenter.Main.IMainPresenter;
import com.may.force.sharareh.Presenter.Main.MainPresenter;
import com.may.force.sharareh.R;
import com.may.force.sharareh.View.Main.Adapter.MainAdapter;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements IMainView{
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.srlUpdatePeoples)
    SwipeRefreshLayout srlSwipeUpdatePeoples;
    @BindView(R.id.rvPeoples)
    RecyclerView rvPeoples;

    private IMainPresenter presenter;
    private SearchView searchView;
    private MainAdapter adapter;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager layoutManager;
    private int page = 1;
    final Context context =this;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }


    private void init() {

        showProgressDialog();

        presenter = new MainPresenter(this, this);
        presenter.getPeople(page, false);

        setSupportActionBar(mToolbar);
        srlSwipeUpdatePeoples.setColorSchemeColors(
                getResources().getColor(R.color.colorPrimary),
                getResources().getColor(R.color.colorPrimaryDark),
                getResources().getColor(R.color.colorAccent));

        srlSwipeUpdatePeoples.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (searchView.isIconified()) {
                    srlSwipeUpdatePeoples.setRefreshing(true);
                    showProgressDialog();
                    page = 1;
                    presenter.getPeople(page, false);
                } else {
                    srlSwipeUpdatePeoples.setRefreshing(false);
                }

            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        final MenuItem actionSearch = menu.findItem(R.id.action_search);
        searchView = (SearchView) actionSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                actionSearch.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }


    @Override
    public void resultListPeople(List<PeopleModel> peopleList) {
        adapter = new MainAdapter(peopleList);
        layoutManager = new LinearLayoutManager(this);
        rvPeoples.setLayoutManager(layoutManager);
        rvPeoples.setAdapter(adapter);
        rvPeoples.animate().setDuration(1000);
        srlSwipeUpdatePeoples.setRefreshing(false);
        hideLoading();
        rvPeoples.addOnScrollListener(onScrollListener);
    }


    @Override
    public void resultListPeopleNextPage(List<PeopleModel> peopleList) {
        if (peopleList != null) {
            if (adapter == null) {
                resultListPeople(peopleList);
            } else {
                adapter.updateList(peopleList);
            }
        }
        srlSwipeUpdatePeoples.setRefreshing(false);
        hideLoading();

    }

    @Override
    public void errorRequest(String error) {
        srlSwipeUpdatePeoples.setRefreshing(false);
        hideLoading();
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
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

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {

            if (dy > 0 && searchView.isIconified()) {
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
                pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();
                if (true) {
                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        if (presenter != null) {
                            page += 1;
                            presenter.getPeople(page, true);
                            showProgressDialog();
                        } else {
                            init();
                        }
                    }
                }
            }
        }
    };

}