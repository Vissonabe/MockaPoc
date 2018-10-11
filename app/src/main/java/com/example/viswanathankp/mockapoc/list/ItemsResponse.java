package com.example.viswanathankp.mockapoc.list;

import com.google.gson.annotations.SerializedName;

public class ItemsResponse {

  @SerializedName("albumId")
  public String albumId;

  @SerializedName("id")
  public String Id;

  @SerializedName("title")
  public String title;

  @SerializedName("url")
  public String url;

  @SerializedName("thumbnailUrl")
  public String thumbnailUrl;
}
