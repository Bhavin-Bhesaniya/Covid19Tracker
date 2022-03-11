package com.example.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.covid19tracker.Api.CountryAdapter;
import com.example.covid19tracker.Api.ApiUtilis;
import com.example.covid19tracker.Api.CountryDataPojo;
import com.example.covid19tracker.databinding.ActivityShowCountryListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowCountryList extends AppCompatActivity {

    ActivityShowCountryListBinding binding;
    private RecyclerView recyclerView;
    private List<CountryDataPojo> list;
    private ProgressDialog dialog;
    private CountryAdapter adapter;
    private EditText searchCountryBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowCountryListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        recyclerView = findViewById(R.id.countryList);
        list = new ArrayList<>();
        searchCountryBar = findViewById(R.id.searchCountryBar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new CountryAdapter(this, list);
        recyclerView.setAdapter(adapter);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setCancelable(false);
        dialog.show();

        ApiUtilis.getApiInterface().getCountryData().enqueue(new Callback<List<CountryDataPojo>>() {
            @Override
            public void onResponse(Call<List<CountryDataPojo>> call, Response<List<CountryDataPojo>> response) {
                list.addAll(response.body());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<CountryDataPojo>> call, Throwable t) {
                dialog.dismiss();
                Toast.makeText(ShowCountryList.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        searchCountryBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {

        List<CountryDataPojo> filterList = new ArrayList<>();
        for(CountryDataPojo items : list){
            if(items.getCountry().toLowerCase().contains(text.toLowerCase())){
                filterList.add(items);
            }
        }
        adapter.filterCountryList(filterList);
    }
}