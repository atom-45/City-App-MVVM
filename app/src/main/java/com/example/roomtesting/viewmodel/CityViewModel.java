package com.example.roomtesting.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.roomtesting.model.City;
import com.example.roomtesting.repository.CityRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class CityViewModel extends AndroidViewModel {

    private final CityRepository cityRepository;
    private final Observable<List<City>> allCities;

    public CityViewModel(@NonNull Application application) {
        super(application);
        cityRepository = new CityRepository(application);
        allCities = cityRepository.getAllCities();
    }

    /**
     * Returns a list of cities that are currently present in the database.
     * @return a List wrapped around an Observable object.
     */
    public Observable<List<City>> getAllCities(){ return allCities; }


    /**
     * Returns a city object that is in the database.
     * @param cityName a city name used to return a city object.
     * @return a city object wrapped in a Single object.
     */
    public Single<City> getCity(String cityName){
        return cityRepository.getCity(cityName);
    }

    /**
     * Inserts a city object into the database.
     * @param city a City object that is going to be inserted into the database
     */
    public void insert(City city){
        cityRepository.insert(city);
    }

    /**
     * Updates an existing city object that is stored in the database
     *
     * @param cityName a String name of the city that already exist in the database.
     * @param numberOfPeople long value of the number of people of the city.
     */
    public void update(String cityName, long numberOfPeople) {
        cityRepository.update(cityName,numberOfPeople);
    }

    /**
     * Deletes an existing city in the database.
     * @param cityName String value of city name.
     */
    public void delete(String cityName){
        cityRepository.delete(cityName);
    }

}
