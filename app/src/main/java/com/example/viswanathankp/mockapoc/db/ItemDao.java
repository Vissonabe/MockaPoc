package com.example.viswanathankp.mockapoc.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface ItemDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(List<Item> item);

  @Query("SELECT * from item_table limit 50")
  LiveData<List<Item>> getAllItems();

  @Query("SELECT count(*) from item_table")
  Integer getAllItemsCount();
}
