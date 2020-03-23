package com.cs5520.quickerorder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private OrderRepository repository;
    private LiveData<List<OrderDish>> allDishes;
    private MutableLiveData<List<OrderDish>> searchResults;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderRepository(application);
        allDishes = repository.getAllDish();
        searchResults = repository.getSearchResults();
    }

    public MutableLiveData<List<OrderDish>> getSearchResults() {
        return searchResults;
    }

    public void findDish(int id) {
        repository.findDish(id);
    }

    public LiveData<List<OrderDish>> getAllDishes() {
        return allDishes;
    }


    public void insertDish(OrderDish dish) {
        repository.insertDish(dish);
    }

    public void deleteDish(int id) {
        repository.deleteDish(id);
    }
}
