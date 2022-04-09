package com.example.roomtesting.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.roomtesting.model.City;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface CityDAO {

    @Query("SELECT * FROM cities")
    Observable<List<City>> getAllCities();

    @Query("SELECT * FROM cities WHERE city_name = :cityName LIMIT 1")
    Single<City> getCity(String cityName);

    @Query("UPDATE cities SET number_of_people = :numberOfPeople WHERE city_name = :cityName")
    Completable updateCityPopulation(String cityName, long numberOfPeople);

    @Query("DELETE FROM cities WHERE city_name = :cityName")
    Completable deleteCity(String cityName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertCity(City city);

}
