package com.example.viswanathankp.mockapoc.db;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "item_table")
public class Item {

  @PrimaryKey(autoGenerate = true)
  private int pid;

  @NonNull
  private String id;

  @NonNull
  private String title;

  @NonNull
  private String thumb_url;

  int getPid() {
    return pid;
  }

  void setPid(int pid) {
    this.pid = pid;
  }

  @NonNull
  public String getId() {
    return id;
  }

  public void setId(@NonNull String id) {
    this.id = id;
  }

  @NonNull
  public String getTitle() {
    return title;
  }

  public void setTitle(@NonNull String title) {
    this.title = title;
  }

  @NonNull
  String getThumb_url() {
    return thumb_url;
  }

  public void setThumb_url(@NonNull String thumb_url) {
    this.thumb_url = thumb_url;
  }
}
