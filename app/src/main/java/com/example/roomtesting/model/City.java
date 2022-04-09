package com.example.roomtesting.model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "cities")
public class City {

    @PrimaryKey(autoGenerate = true)
    private int city_id;

    @ColumnInfo(name = "number_of_people")
    private long numberOfPeople;

    @ColumnInfo(name = "postcode")
    private int postCode;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "country")
    private String country;

    @ColumnInfo(name = "services")
    private String services;

    public City(String cityName, String country,long numberOfPeople, int postCode, String services){
        this.cityName = cityName;
        this.country = country;
        this.numberOfPeople = numberOfPeople;
        this.postCode = postCode;
        this.services = services;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public void setNumberOfPeople(long numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public long getNumberOfPeople() {
        return numberOfPeople;
    }

    public int getPostCode() {
        return postCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountry() {
        return country;
    }

    public String getServices() {
        return services;
    }

    public int getCity_id() {
        return city_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return getCity_id() == city.getCity_id()
                && getNumberOfPeople() == city.getNumberOfPeople()
                && getPostCode() == city.getPostCode()
                && getCityName().equals(city.getCityName())
                && getCountry().equals(city.getCountry())
                && getServices().equals(city.getServices());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity_id(), getNumberOfPeople(),
                getPostCode(), getCityName(), getCountry(), getServices());
    }

    @NonNull
    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", numberOfPeople=" + numberOfPeople +
                ", postCode=" + postCode +
                ", cityName='" + cityName + '\'' +
                ", country='" + country + '\'' +
                ", services='" + services + '\'' +
                '}';
    }
}
