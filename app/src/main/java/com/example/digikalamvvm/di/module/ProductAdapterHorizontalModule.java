package com.example.digikalamvvm.di.module;

import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.ProductAdapterHorizontal;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductAdapterHorizontalModule {
    private ProductViewmodel viewmodel;

    public ProductAdapterHorizontalModule(ProductViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Provides
    public ProductAdapterHorizontal productAdapterHorizontal(){
        return new ProductAdapterHorizontal(viewmodel);
    }
}
