package com.example.digikalamvvm.di.component;


import com.example.digikalamvvm.di.module.NextShoppingListFragmentModule;
import com.example.digikalamvvm.di.module.ShoppingListFragmentModule;
import com.example.digikalamvvm.view.fragment.NextShoppingListFragment;
import com.example.digikalamvvm.view.fragment.ShoppingListFragment;

import dagger.Component;

@Component(modules = ShoppingListFragmentModule.class)
public interface ShoppingListFragmentComponent {
    ShoppingListFragment getShoppingListFragment();
}
