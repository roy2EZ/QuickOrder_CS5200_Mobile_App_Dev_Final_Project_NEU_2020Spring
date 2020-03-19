package com.cs5520.quickerorder;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {OrderDish.class}, version = 1)
public abstract class OrderRoomDatabase extends RoomDatabase {

    public abstract OrderDao orderDao();
    private static OrderRoomDatabase INSTANCE;


    static OrderRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            OrderRoomDatabase.class, "order_database")
                            .build();
                }
            }
        }

        return INSTANCE;

    }

}
