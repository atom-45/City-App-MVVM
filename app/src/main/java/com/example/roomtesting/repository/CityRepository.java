package com.example.roomtesting.repository;

import android.content.Context;
import android.util.Log;

import com.example.roomtesting.model.City;
import com.example.roomtesting.database.CityDAO;
import com.example.roomtesting.database.CityDatabase;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * This only allows us to access data from Room database.
 * The next question should be, can I use a repository to access both the local database
 * and or api? Answer: Yes.
 */

public class CityRepository {

    private final CityDAO cityDAO;
    private final Observable<List<City>> allCities;

    //I can simply pass through application context.
    public CityRepository(Context context){
        CityDatabase db = CityDatabase.getInstance(context);
        cityDAO = db.cityDAO();
        allCities = cityDAO.getAllCities();
    }

    public Observable<List<City>> getAllCities(){
        return allCities;
    }

    public Single<City> getCity(String cityName){
        return cityDAO.getCity(cityName);
    }

    public void insert(City city){
        cityDAO.insertCity(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("onSubscribe", String.valueOf(d.isDisposed()));
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete","Inserted: "+city.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError", e.toString());
                    }
                })
        ;
    }

    public void update(String cityName, long numberOfPeople){
        cityDAO.updateCityPopulation(cityName, numberOfPeople)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("onSubscribe", String.valueOf(d.isDisposed()));
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete","City got updated in the database");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError", e.toString());
                    }
                })
        ;

    }

    public void delete(String cityName){
        cityDAO.deleteCity(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("onSubscribe", String.valueOf(d.isDisposed()));
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete","City got deleted from the database");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("onError", e.toString());

                    }
                })
        ;
    }
}
