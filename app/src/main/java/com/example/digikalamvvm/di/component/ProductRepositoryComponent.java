package com.example.digikalamvvm.di.component;


import com.example.digikalamvvm.di.module.ProductRepositoryModule;
import com.example.digikalamvvm.model.repository.ProductRepository;

import dagger.Component;

@Component(modules = ProductRepositoryModule.class)
public interface ProductRepositoryComponent {
    ProductRepository getProductRepository();
}
