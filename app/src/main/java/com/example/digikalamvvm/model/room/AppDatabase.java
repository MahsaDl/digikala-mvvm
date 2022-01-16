package com.example.digikalamvvm.model.room;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.digikalamvvm.model.room.dao.ProductDao;
import com.example.digikalamvvm.model.room.entity.Product;

@Database(entities = Product.class,version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao getProdectDao();
    private static AppDatabase instance;
    public static AppDatabase getInstance(Application application){
       if (instance==null)
       {
           instance= Room.databaseBuilder(application,AppDatabase.class,"Digikala.DB")
                   .allowMainThreadQueries()
                   .build();
       }
       return instance;
    }


}
