package com.cs5520.quickerorder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DishesDao {

    @Query("SELECT * FROM dishes")
    LiveData<List<Dishes>> getAllDishes();
}
