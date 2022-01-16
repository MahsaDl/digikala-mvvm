package com.example.digikalamvvm.view.fragment;

import android.app.Service;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.digikalamvvm.R;
import com.example.digikalamvvm.databinding.FragmentHomeBinding;
import com.example.digikalamvvm.di.component.DaggerProductAdapterComponent;
import com.example.digikalamvvm.di.component.DaggerProductAdapterGridComponent;
import com.example.digikalamvvm.di.component.DaggerProductAdapterHorizontalComponent;
import com.example.digikalamvvm.di.component.DaggerSliderAdapterComponent;
import com.example.digikalamvvm.di.component.ProductAdapterComponent;
import com.example.digikalamvvm.di.component.ProductAdapterGridComponent;
import com.example.digikalamvvm.di.component.SliderAdapterComponent;
import com.example.digikalamvvm.di.module.ProductAdapterGridModule;
import com.example.digikalamvvm.di.module.ProductAdapterHorizontalModule;
import com.example.digikalamvvm.di.module.ProductAdapterModule;
import com.example.digikalamvvm.di.module.SliderAdapterModule;
import com.example.digikalamvvm.model.room.entity.Product;
import com.example.digikalamvvm.model.room.entity.SliderItem;
import com.example.digikalamvvm.view.adapter.ProductAdapter;
import com.example.digikalamvvm.view.adapter.ProductAdapterGrid;
import com.example.digikalamvvm.view.adapter.ProductAdapterHorizontal;
import com.example.digikalamvvm.view.adapter.SliderAdapter;
import com.example.digikalamvvm.viewmodel.ProductViewmodel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;



public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ProductViewmodel viewModel;

    //@Inject
    SliderAdapter adapterSlider;

    ProductAdapterGrid adapterGrid;
    ProductAdapterHorizontal adapterHorizontal;


    int layoutview;

    Animation animAlpha;
    private static final String TAG = "MainFragment";

    //@Inject
    ProductAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable ViewGroup container, @Nullable  Bundle savedInstanceState) {
        binding=FragmentHomeBinding.inflate(inflater);
        Log.e(TAG, "MainFragment");

        binding.scroll.post(new Runnable() {
            @Override
            public void run() {
                binding.scroll.fullScroll(View.FOCUS_RIGHT);
            }
        });

        initSlider();

        //adapterHorizontal=new ProductAdapterHorizontal(viewModel);
        adapterHorizontal=DaggerProductAdapterHorizontalComponent.builder().productAdapterHorizontalModule(new ProductAdapterHorizontalModule(viewModel)).build().getProductAdapterHorizontal();
        binding.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
        binding.horizontalRecyclerView.setAdapter(adapterHorizontal);

        animAlpha = AnimationUtils.loadAnimation(getActivity(), R.anim.btn_animation);
        //binding.btnSortList.startAnimation(animAlpha);
        //binding.btnSortList.setImageResource(R.drawable.ic_sort_list_selected);
        //binding.btnSortGrid.setImageResource(R.drawable.ic_sort_gridview);

        //binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        /////DaggerProductAdapterComponent.builder().productAdapterModule(new ProductAdapterModule(viewModel)).build().inject(this);
        ProductAdapterComponent productAdapterComponent = DaggerProductAdapterComponent.builder().productAdapterModule(new ProductAdapterModule(viewModel)).build();
        adapter=productAdapterComponent.getProductAdapter();
        //binding.recyclerView.setAdapter(adapter);

        //////DaggerSliderAdapterComponent.builder().sliderAdapterModule(new SliderAdapterModule(getContext())).build().inject(this);

        viewModel= ViewModelProviders.of(this).get(ProductViewmodel.class);

        if (layoutview==0 || layoutview==1){

            binding.btnSortList.startAnimation(animAlpha);
            binding.btnSortList.setImageResource(R.drawable.ic_sort_list_selected);
            binding.btnSortGrid.setImageResource(R.drawable.ic_sort_gridview);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.recyclerView.setAdapter(adapter);

            viewModel.select().observe(getViewLifecycleOwner(),new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.setList(products);
                adapterHorizontal.setList(products);
            }
        });
            layoutview=1;
        }else if (layoutview==2){
            binding.btnSortList.setImageResource(R.drawable.ic_sort_list);
            animAlpha = AnimationUtils.loadAnimation(getActivity(), R.anim.btn_animation);
            binding.btnSortGrid.startAnimation(animAlpha);
            binding.btnSortGrid.setImageResource(R.drawable.ic_sort_gridview_selected);

            binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
            //adapterGrid=new ProductAdapterGrid(viewModel);
            ProductAdapterGridComponent productAdapterGridComponent = DaggerProductAdapterGridComponent.builder().productAdapterGridModule(new ProductAdapterGridModule(viewModel)).build();
            adapterGrid=productAdapterGridComponent.getProductAdapterGrid();
            binding.recyclerView.setAdapter(adapterGrid);
            viewModel.select().observe(getViewLifecycleOwner(),new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                    adapterGrid.setList(products);
                    adapterHorizontal.setList(products);
                }
            });
            layoutview=2;



        }

        setHasOptionsMenu(true);

        return binding.getRoot();


    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSortList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutview==2){
                    final Animation animAlpha = AnimationUtils.loadAnimation(getActivity(), R.anim.btn_animation);
                    binding.btnSortList.startAnimation(animAlpha);
                    binding.btnSortList.setImageResource(R.drawable.ic_sort_list_selected);
                    binding.btnSortGrid.setImageResource(R.drawable.ic_sort_gridview);
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    binding.recyclerView.setAdapter(adapter);
                    viewModel.select().observe(getViewLifecycleOwner(),new Observer<List<Product>>() {
                        @Override
                        public void onChanged(List<Product> products) {
                            adapter.setList(products);
                        }
                    });
                    layoutview=1;
                }



            }
        });

        binding.btnSortGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (layoutview==1){
                    binding.btnSortList.setImageResource(R.drawable.ic_sort_list);
                    final Animation animAlpha = AnimationUtils.loadAnimation(getActivity(), R.anim.btn_animation);
                    binding.btnSortGrid.startAnimation(animAlpha);
                    binding.btnSortGrid.setImageResource(R.drawable.ic_sort_gridview_selected);

                    binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                    adapterGrid=new ProductAdapterGrid(viewModel);
                    binding.recyclerView.setAdapter(adapterGrid);
                    viewModel.select().observe(getViewLifecycleOwner(),new Observer<List<Product>>() {
                        @Override
                        public void onChanged(List<Product> products) {
                            adapterGrid.setList(products);
                        }
                    });
                    layoutview=2;
                }

            }
        });

    }


    private void closeSoftKeyboard(){
        InputMethodManager manager= (InputMethodManager) getActivity().getSystemService(Service.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),0);
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint("جستجو در دیجی کالا");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                getNameFromDb(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getNameFromDb(newText);

                return true;
            }
            });
    }
    private void getNameFromDb(String searchText){

        searchText=searchText+"%";
        if (layoutview==2){
            viewModel.select(searchText).observe(this, new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                    adapterGrid.setList(products);
                }
            });
        }
        if (layoutview==1){
            viewModel.select(searchText).observe(this, new Observer<List<Product>>() {
                @Override
                public void onChanged(List<Product> products) {
                    adapter.setList(products);
                }
            });
        }


    }








    @Override
    public void onDestroy() {
        super.onDestroy();
        binding=null;
    }
    public void initSlider(){
        //adapterSlider = new SliderAdapter(getActivity());
        SliderAdapterComponent sliderAdapterComponent = DaggerSliderAdapterComponent.builder().sliderAdapterModule(new SliderAdapterModule(getContext())).build();
        adapterSlider=sliderAdapterComponent.getSliderAdapter();
        binding.imageSlider.setSliderAdapter(adapterSlider);

        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM);
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        binding.imageSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        binding.imageSlider.setIndicatorSelectedColor(getResources().getColor(R.color.myview));
        binding.imageSlider.setIndicatorUnselectedColor(Color.GRAY);
        binding.imageSlider.setScrollTimeInSec(3);
        binding.imageSlider.setAutoCycle(true);
        binding.imageSlider.startAutoCycle();

        binding.imageSlider.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + binding.imageSlider.getCurrentPagePosition());
            }
        });
        renewItems(binding.imageSlider);
        removeLastItem(binding.imageSlider);
        addNewItem(binding.imageSlider);
    }
    public void renewItems(View view){
        List<SliderItem> sliderItemList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            SliderItem sliderItem = new SliderItem();
            //sliderItem.setDescription("Slider Item " + i);
            if (i % 2 == 0) {
                sliderItem.setImageUrl("https://torangkala.com/wp-content/uploads/2021/01/%D8%AE%D8%B1%DB%8C%D8%AF-%D8%A7%D9%88%D9%84%DB%8C-%D8%AF%DB%8C%D8%AC%DB%8C-%DA%A9%D8%A7%D9%84%D8%A7.jpg");
            } else {
                sliderItem.setImageUrl("https://mokhatab.org/wp-content/uploads/Digikala-Discount.png");
            }
            sliderItemList.add(sliderItem);
        }
        adapterSlider.renewItems(sliderItemList);
    }

    public void removeLastItem(View view) {
        if (adapterSlider.getCount() - 1 >= 0)
            adapterSlider.deleteItem(adapterSlider.getCount() - 1);
    }

    public void addNewItem(View view) {
        SliderItem sliderItem = new SliderItem();
        //sliderItem.setDescription("Slider Item Added Manually");
        sliderItem.setImageUrl("https://www.digikala.com/mag/wp-content/uploads/2020/12/news.jpg");
        adapterSlider.addItem(sliderItem);
    }

}
