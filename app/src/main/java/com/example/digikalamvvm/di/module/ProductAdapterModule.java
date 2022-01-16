package com.example.digikalamvvm.di.module;

import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductAdapterModule {
    private ProductViewmodel viewmodel;

    public ProductAdapterModule(ProductViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Provides
    public ProductAdapter productAdapter(){
        return new ProductAdapter(viewmodel);
    }
}
