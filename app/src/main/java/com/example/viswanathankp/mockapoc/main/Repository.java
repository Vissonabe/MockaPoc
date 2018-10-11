package com.example.viswanathankp.mockapoc.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import com.example.viswanathankp.mockapoc.db.Item;
import com.example.viswanathankp.mockapoc.db.ItemDao;
import com.example.viswanathankp.mockapoc.db.ItemDatabase;
import com.example.viswanathankp.mockapoc.db.SharePreferenceHelper;
import com.example.viswanathankp.mockapoc.list.ItemsResponse;
import com.example.viswanathankp.mockapoc.list.ListService;
import com.example.viswanathankp.mockapoc.network.ApiClient;
import com.example.viswanathankp.mockapoc.network.ApiResponse;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

class Repository {

  private final Retrofit mRetrofit;
  private final ItemDao mItemDao;
  private SharePreferenceHelper mPreferenceHelper;

  Repository(Context context){
    mRetrofit = ApiClient.getClient();
    ItemDatabase db = ItemDatabase.getDatabase(context);
    mItemDao = db.itemDao();
    mPreferenceHelper = new SharePreferenceHelper(context.getApplicationContext());
    //new itemsAsyncTask(mItemDao).execute();
  }

  LiveData<ApiResponse<List<ItemsResponse>, String>> getAllItems(){
    final MutableLiveData<ApiResponse<List<ItemsResponse>, String>> live = new MutableLiveData<>();
    ListService call = mRetrofit.create(ListService.class);
    Call<List<ItemsResponse>> imageCall = call.getImageListResources();
    imageCall.enqueue(new Callback<List<ItemsResponse>>() {
      @Override
      public void onResponse(@NonNull Call<List<ItemsResponse>> call, @NonNull retrofit2.Response<List<ItemsResponse>> response) {
        if(response.isSuccessful() && response.body() != null){
          live.setValue(ApiResponse.success(response.body()));
          if(!mPreferenceHelper.isDbLoaded()) {
            mPreferenceHelper.setToPreference();
            new insertAsyncTask(mItemDao).execute(response.body().toArray(new ItemsResponse[response.body().size()]));
          }
        }
      }

      @Override
      public void onFailure(Call<List<ItemsResponse>> call, Throwable t) {
        live.setValue(ApiResponse.failure(t.getMessage()));
      }
    });
    return live;
  }

  private static class insertAsyncTask extends AsyncTask<ItemsResponse, Void, Void> {

    private ItemDao mAsyncTaskDao;

    insertAsyncTask(ItemDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(ItemsResponse... lists) {
      mAsyncTaskDao.insert(getItems(lists));
      return null;
    }

    private List<Item> getItems(ItemsResponse... lists){
      List<Item> items = new ArrayList<>();
      for (int i = 0; i < 200; i++) {
        ItemsResponse res = lists[i];
        Item it = new Item();
        it.setId(res.Id);
        it.setTitle(res.title);
        it.setThumb_url(res.thumbnailUrl);
        items.add(it);
      }
      return items;
    }
  }


  private static class itemsAsyncTask extends AsyncTask<Void, Void, Integer> {

    private ItemDao mAsyncTaskDao;

    itemsAsyncTask(ItemDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Integer doInBackground(Void... voids) {
      return mAsyncTaskDao.getAllItemsCount();
    }

    @Override
    protected void onPostExecute(Integer i) {
      super.onPostExecute(i);
      Log.d("items count: " , String.valueOf(i));
    }
  }
}
