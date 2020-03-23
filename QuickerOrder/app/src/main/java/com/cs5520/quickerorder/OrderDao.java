package com.cs5520.quickerorder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insertDish(OrderDish orderDish);

    @Query("SELECT * FROM orders WHERE id = :id")
    OrderDish findDish(int id);

    @Query("DELETE FROM orders WHERE id = :id")
    void deleteDish(int id);

    @Query("SELECT * FROM orders")
    LiveData<List<OrderDish>> getAllDishes();
}
