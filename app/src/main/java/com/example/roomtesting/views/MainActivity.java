package com.example.roomtesting.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomtesting.model.City;
import com.example.roomtesting.R;
import com.example.roomtesting.viewmodel.CityViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private CityViewModel cityViewModel;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText cityName = findViewById(R.id.cityNameEditText);
        EditText country = findViewById(R.id.countryEditText);
        EditText populationNumber = findViewById(R.id.numberOfPeopleEditText);
        EditText postCode = findViewById(R.id.postCodeEditText);
        EditText services = findViewById(R.id.servicesEditText);

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);


        findViewById(R.id.button).setOnClickListener(view -> {
            City city = new City(cityName.getText().toString().trim(),
                    country.getText().toString().trim(),
                    Long.parseLong(populationNumber.getText().toString().trim()),
                    Integer.parseInt(postCode.getText().toString().trim()),
                    services.getText().toString().trim());

            cityViewModel.insert(city);

        });

       findViewById(R.id.button2).setOnClickListener(view -> {

           
           Disposable disposable = cityViewModel.getAllCities()
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(cities -> {
                       Log.e("List of Cities",cities.toString());
                       Toast.makeText(MainActivity.this,
                               "Cities list have shown", Toast.LENGTH_SHORT).show();
                   });
           compositeDisposable.add(disposable);

       });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }
}