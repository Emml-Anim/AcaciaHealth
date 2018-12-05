package net.syynclab.acaciahealth.activities;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.Toast;


import net.syynclab.acaciahealth.R;
import net.syynclab.acaciahealth.adapter.MyAdapter;
import net.syynclab.acaciahealth.model.HealthProvider;
import net.syynclab.acaciahealth.retrofit.IAPI;
import net.syynclab.acaciahealth.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class GreaterAccra extends AppCompatActivity {

    private SearchView searchView;
    MyAdapter adapter;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    List<HealthProvider> dataList = new ArrayList<>();
    IAPI iapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greater_accra);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filters");

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance();
        iapi = retrofit.create(IAPI.class);

        //Views
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        adapter = new MyAdapter(this, dataList);
        recyclerView.setAdapter(adapter);
        
        fetchData();
    }

    private void fetchData() {
        compositeDisposable.add(iapi.loadData()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(new Consumer<List<HealthProvider>>() {
            @Override
            public void accept(List<HealthProvider> healthProviders) throws Exception {
                displayData(healthProviders);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(final Throwable throwable) throws Exception {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(GreaterAccra.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }));

    }

    private void displayData(List<HealthProvider> healthProviders) {
        dataList.clear();
        dataList.addAll(healthProviders);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE);

        searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search)
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified())
        {
            searchView.setIconified(true);
            return;
        }

        super.onBackPressed();
    }
}


