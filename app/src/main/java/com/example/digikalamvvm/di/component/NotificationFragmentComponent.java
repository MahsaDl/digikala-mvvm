package com.example.digikalamvvm.di.component;


import com.example.digikalamvvm.di.module.NotificationFragmentModule;
import com.example.digikalamvvm.di.module.ProductRepositoryModule;
import com.example.digikalamvvm.model.repository.ProductRepository;
import com.example.digikalamvvm.view.fragment.NotificationFragment;

import dagger.Component;

@Component(modules = NotificationFragmentModule.class)
public interface NotificationFragmentComponent {
    NotificationFragment getNotificationFragment();
}
