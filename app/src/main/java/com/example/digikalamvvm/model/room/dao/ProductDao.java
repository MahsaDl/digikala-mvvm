package com.example.digikalamvvm.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.digikalamvvm.model.room.entity.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Delete
    void delete(Product product);

    @Update
    void update(Product product);

    @Query("select * from Product order by id desc" )
    LiveData<List<Product>> select();

    @Query("select * from Product where id = :id" )
    Single<Product> selectById(int id);


    @Query("select * from Product where name like :s" )
    LiveData<List<Product>> select(String s);


    @Query("select * from Product order by id desc" )
    LiveData<List<Product>> selectRetrofit();
}
