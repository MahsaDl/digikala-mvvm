package com.example.digikalamvvm.di.component;


import com.example.digikalamvvm.di.module.NextShoppingListFragmentModule;
import com.example.digikalamvvm.di.module.NotificationFragmentModule;
import com.example.digikalamvvm.view.fragment.NextShoppingListFragment;
import com.example.digikalamvvm.view.fragment.NotificationFragment;

import dagger.Component;

@Component(modules = NextShoppingListFragmentModule.class)
public interface NextShoppingListFragmentComponent {
    NextShoppingListFragment getNextShoppingListFragment();
}
