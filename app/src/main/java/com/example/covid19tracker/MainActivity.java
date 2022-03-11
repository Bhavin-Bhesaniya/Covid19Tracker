package com.example.covid19tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.covid19tracker.Api.ApiUtilis;
import com.example.covid19tracker.Api.CountryDataPojo;
import com.example.covid19tracker.databinding.ActivityMainBinding;

import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private List<CountryDataPojo> list;
    ProgressDialog progress;
    String Country = "India";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progress = new ProgressDialog(this);
        progress.setMessage("Loading Data");
        progress.setCancelable(false);
        progress.show();


        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(50); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        binding.covidTitle.startAnimation(anim);

        if(getIntent().getStringExtra("Country") != null){
            Country = getIntent().getStringExtra("Country") ;
        }

        TextView cname = findViewById(R.id.countryHomeName);
        cname.setText(Country);
        cname.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ShowCountryList.class)));

//        binding.countryHomeName.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ShowCountryList.class)));

        list = new ArrayList<>();
        ApiUtilis.getApiInterface().getCountryData()
                .enqueue(new Callback<List<CountryDataPojo>>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(Call<List<CountryDataPojo>> call, Response<List<CountryDataPojo>> response) {
                        list.addAll(response.body());
                        progress.dismiss();
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getCountry().equals(Country)) {
                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int active = Integer.parseInt(list.get(i).getActive());
                                int recovered = Integer.parseInt(list.get(i).getRecovered());
                                int death = Integer.parseInt(list.get(i).getDeaths());

                                binding.totalConfirmCase.setText(NumberFormat.getInstance().format(confirm));
                                binding.totalActiveCase.setText(NumberFormat.getInstance().format(active));
                                binding.totalRecover.setText(NumberFormat.getInstance().format(recovered));
                                binding.totalDeath.setText(NumberFormat.getInstance().format(death));

                                binding.todayConfirmCase.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                binding.todayRecover.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                                binding.todayDeath.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                binding.totalTest.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));


                                setText(list.get(i).getUpdated());


                                binding.pieChart.addPieSlice(new PieModel("Confirm", confirm, getResources().getColor(R.color.yellow)));
                                binding.pieChart.addPieSlice(new PieModel("Active", active, getResources().getColor(R.color.bluepie)));
                                binding.pieChart.addPieSlice(new PieModel("Recovered", recovered, getResources().getColor(R.color.greenpie)));
                                binding.pieChart.addPieSlice(new PieModel("Death", death, getResources().getColor(R.color.redpie)));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<CountryDataPojo>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setText(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");
        long milliseconds = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);

        binding.apiUpdatedAt.setText("Updated At" + format.format(calendar.getTime()));
    }
}