package com.example.viswanathankp.mockapoc.list;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ListService {

  @GET("/photos")
  Call<List<ItemsResponse>> getImageListResources();

}
