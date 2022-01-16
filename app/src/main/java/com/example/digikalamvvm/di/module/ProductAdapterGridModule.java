package com.example.digikalamvvm.di.module;

import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.ProductAdapterGrid;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;

import dagger.Module;
import dagger.Provides;

@Module
public class ProductAdapterGridModule {
    private ProductViewmodel viewmodel;

    public ProductAdapterGridModule(ProductViewmodel viewmodel) {
        this.viewmodel = viewmodel;
    }

    @Provides
    public ProductAdapterGrid productAdapterGrid(){
        return new ProductAdapterGrid(viewmodel);
    }
}
