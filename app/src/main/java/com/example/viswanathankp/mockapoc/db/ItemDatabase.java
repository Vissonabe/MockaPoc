package com.example.viswanathankp.mockapoc.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
  public abstract ItemDao itemDao();

  private static volatile ItemDatabase INSTANCE;

  public static ItemDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (ItemDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
              ItemDatabase.class, "item_database")
              .build();
        }
      }
    }
    return INSTANCE;
  }
}
