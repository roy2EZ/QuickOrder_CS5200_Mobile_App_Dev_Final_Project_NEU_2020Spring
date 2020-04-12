package com.cs5520.quickerorder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {OrderDish.class}, version = 2, exportSchema = false)
public abstract class OrderRoomDatabase extends RoomDatabase {

    public abstract OrderDao orderDao();
    private static OrderRoomDatabase INSTANCE;

    /**
     * Migrate from:
     * version 1
     * to
     * version 2 - add price column
     */
    @VisibleForTesting
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE orders "
                    + " ADD COLUMN price DOUBLE NOT NULL DEFAULT 0");
        }
    };

    static OrderRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            OrderRoomDatabase.class, "order_database")
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }

        return INSTANCE;

    }

}
