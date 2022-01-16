package com.example.digikalamvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.digikalamvvm.di.component.DaggerProductRepositoryComponent;
import com.example.digikalamvvm.di.module.ProductRepositoryModule;
import com.example.digikalamvvm.model.repository.ProductRepository;
import com.example.digikalamvvm.model.room.entity.Product;


import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ProductViewmodel extends AndroidViewModel{
    private ProductRepository repository;
    private LiveData<List<Product>> liveData;


    public ProductViewmodel( Application application) {
        super(application);
        repository= DaggerProductRepositoryComponent.builder().productRepositoryModule(new ProductRepositoryModule(application)).build().getProductRepository();
        liveData=repository.select();
    }
    public void insert(Product product){
        repository.insert(product);
    }
    public void update(Product product){
        repository.update(product);
    }

    public void delete(Product product){
        repository.delete(product);
    }


    public LiveData<List<Product>> selectRetrofit(){
        return repository.selectRetrofit();
    }

    public LiveData<List<Product>> select(){
        return liveData;
    }

    public Single<Product> selectById(int id){
        return repository.selectById(id);
    }
    public LiveData<List<Product>> select(String s){
        return repository.select(s);
    }


}
