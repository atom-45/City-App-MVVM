package com.example.roomtesting.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomtesting.model.City;

@Database(entities = {City.class}, version = 1 ,exportSchema = false)

public abstract class CityDatabase extends RoomDatabase {

    private static volatile CityDatabase INSTANCE;


    //It's a Singleton method.
   public static CityDatabase getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (CityDatabase.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CityDatabase.class,"city_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract CityDAO cityDAO();
}
