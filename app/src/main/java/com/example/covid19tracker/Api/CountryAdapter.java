package com.example.covid19tracker.Api;

import android.content.Context;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.covid19tracker.MainActivity;
import com.example.covid19tracker.R;

import java.util.List;
import java.util.Map;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.countryViewHolder> {

    private Context context;
    private List<CountryDataPojo> list;

    public CountryAdapter(Context context, List<CountryDataPojo> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public countryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_item_layout, parent, false);
        return new countryViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull countryViewHolder holder, int position) {

        CountryDataPojo data = list.get(position);
        holder.countryTotalCase.setText(NumberFormat.getInstance().format(Integer.parseInt(data.getCases())));
        holder.countryName.setText(data.getCountry());
        holder.countrySno.setText(String.valueOf(position+1));

        Map<String, String> img = data.getCountryInfo();
        Glide.with(context).load(img.get("flag")).into(holder.countryImg);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("Country", data.getCountry());
            context.startActivity(intent);
        });
    }

    //After
    public void filterCountryList(List<CountryDataPojo> filterCountryList){
        list = filterCountryList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class countryViewHolder extends RecyclerView.ViewHolder {

        private TextView countrySno, countryName, countryTotalCase;
        private ImageView countryImg;

        public countryViewHolder(@NonNull View itemView) {
            super(itemView);
            countrySno = itemView.findViewById(R.id.countrySno);
            countryName = itemView.findViewById(R.id.countryName);
            countryTotalCase = itemView.findViewById(R.id.countryTotalCase);
            countryImg = itemView.findViewById(R.id.countryImg);

        }
    }
}
