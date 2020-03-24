package com.cs5520.quickerorder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert
    void insertDish(OrderDish orderDish);

    @Query("SELECT * FROM orders WHERE id = :id")
    OrderDish findDish(int id);

    // delete usually delete by 1
    @Query("DELETE FROM orders WHERE id = :id")
    void deleteDish(int id);

    @Query("SELECT * FROM orders")
    LiveData<List<OrderDish>> getAllDishes();

    // if the quatity of a dish in database is 0, then remove it
    @Delete
    public int removedish(OrderDish dish);

    @Update
    public void updateDish(OrderDish dish);
}
