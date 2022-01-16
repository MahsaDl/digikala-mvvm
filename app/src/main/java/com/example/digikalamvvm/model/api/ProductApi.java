package com.example.digikalamvvm.model.api;

import com.example.digikalamvvm.model.room.entity.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductApi {
    String BASE_URL="https://run.mocky.io/";

    @GET("v3/2b5bc46a-fd51-4488-88ff-b847fd4518de")
    Call<List<Product>> selectAll();

}
