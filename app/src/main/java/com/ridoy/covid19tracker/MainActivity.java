package com.ridoy.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.ridoy.covid19tracker.API.CoronaAPI;
import com.ridoy.covid19tracker.API.CountryData;
import com.ridoy.covid19tracker.databinding.ActivityMainBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<CountryData> countryDatalist;
    ActivityMainBinding binding;
    private PieChart chart;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        chart = findViewById(R.id.piechart);
        countryDatalist = new ArrayList<>();
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.show();
        CoronaAPI.getApi().getCountryData().enqueue(new Callback<List<CountryData>>() {
            @Override
            public void onResponse(Call<List<CountryData>> call, Response<List<CountryData>> response) {
                countryDatalist.addAll(response.body());
                for (int i = 0; i < countryDatalist.size(); i++) {
                    if (countryDatalist.get(i).getCountry().equals("Bangladesh")) {
                        int confirm = Integer.parseInt(countryDatalist.get(i).getCases());
                        int active = Integer.parseInt(countryDatalist.get(i).getActive());
                        int recovered = Integer.parseInt(countryDatalist.get(i).getRecovered());
                        int death = Integer.parseInt(countryDatalist.get(i).getDeaths());
                        int todaydeath = Integer.parseInt(countryDatalist.get(i).getTodayDeaths());
                        int todayconfirm = Integer.parseInt(countryDatalist.get(i).getTodayCases());
                        int todayrecovered = Integer.parseInt(countryDatalist.get(i).getTodayRecovered());
                        int totaltest = Integer.parseInt(countryDatalist.get(i).getTests());

                        binding.totalconfirm.setText(NumberFormat.getInstance().format(confirm));
                        binding.totalactive.setText(NumberFormat.getInstance().format(active));
                        binding.totalrecovered.setText(NumberFormat.getInstance().format(recovered));
                        binding.totaldeath.setText(NumberFormat.getInstance().format(death));

                        binding.todaydeath.setText("+ "+NumberFormat.getInstance().format(todaydeath));
                        binding.todayconfirm.setText("+ "+NumberFormat.getInstance().format(todayconfirm));
                        binding.todayrecovered.setText("+ "+NumberFormat.getInstance().format(todayrecovered));
                        binding.totaltests.setText(NumberFormat.getInstance().format(totaltest));

                        setDate(countryDatalist.get(i).getUpdated());

                        chart.addPieSlice(new PieModel("Confirm", confirm, getResources().getColor(R.color.yellow)));
                        chart.addPieSlice(new PieModel("Active", active, getResources().getColor(R.color.blue_pie)));
                        chart.addPieSlice(new PieModel("Recovered", recovered, getResources().getColor(R.color.green_pie)));
                        chart.addPieSlice(new PieModel("Death", death, getResources().getColor(R.color.red_pie)));
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CountryData>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDate(String updated) {
        DateFormat format = new SimpleDateFormat("MMM dd, yyyy");

        long mili = Long.parseLong(updated);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mili);
        binding.updatedDate.setText("Updated at: " + format.format(calendar.getTime()));
    }
}