package com.cs5520.quickerorder;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "orders")
public class  OrderDish {
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "quantity")
    private int quantity;
}
