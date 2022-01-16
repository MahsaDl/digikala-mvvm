package com.example.digikalamvvm.model.repository;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.digikalamvvm.di.component.DaggerRetrofitComponent;
import com.example.digikalamvvm.model.api.ProductApi;
import com.example.digikalamvvm.model.room.AppDatabase;
import com.example.digikalamvvm.model.room.dao.ProductDao;
import com.example.digikalamvvm.model.room.entity.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProductRepository {
    private ProductDao productDao;
    private LiveData<List<Product>> liveData;
    private ProductApi api;

    private static final String TAG = "ProductRepository";

    public ProductRepository(Application application) {
        this.productDao = AppDatabase.getInstance(application).getProdectDao();
        liveData = productDao.select();

        Retrofit retrofit = DaggerRetrofitComponent.create().getRetrofit();
        api = retrofit.create(ProductApi.class);

    }

    public void insert(Product product) {
        productDao.insert(product);

    }
    public void update(Product product) {
        productDao.update(product);

    }

    public void delete(Product product) {
        productDao.delete(product);

    }


    public LiveData<List<Product>> select(String s) {
        return productDao.select(s);
    }


    public Single<Product> selectById(int id) {
        return productDao.selectById(id);
    }

    public LiveData<List<Product>> select() {
        Log.e(TAG, "select ");
        return liveData;
    }


    public LiveData<List<Product>> selectRetrofit() {
        LiveData<List<Product>> liveData = new LiveData<List<Product>>() {
            @Override
            public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super List<Product>> observer) {
                super.observe(owner, observer);
                api.selectAll().enqueue(new Callback<List<Product>>() {

                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if (response.code() == 200) {
                            for (int i = 0; i < response.body().size(); i++) {
                                Product product = new Product(response.body().get(i).getId(), response.body().get(i).getImageUrl(), response.body().get(i).getName(), response.body().get(i).getPrice(), response.body().get(i).getAnbar(), response.body().get(i).getModel());
                                Log.e(TAG, "insert: " + "response.body().size() = " + response.body().size());
                                insert(product);
                            }
                            observer.onChanged(response.body());
                        }else {
                            observer.onChanged(null);
                        }

                    }


                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        observer.onChanged(null);
                    }
                });
            }
        };

        return liveData;

    }
}
