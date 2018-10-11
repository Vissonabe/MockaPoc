package com.example.viswanathankp.mockapoc.main;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.util.Pair;
import com.example.viswanathankp.mockapoc.cart.CartManager;
import com.example.viswanathankp.mockapoc.list.ItemsResponse;
import com.example.viswanathankp.mockapoc.cart.CartItem;
import com.example.viswanathankp.mockapoc.network.ApiResponse;
import java.util.List;

public class ListViewModel extends LifeCycleViewModel {
  private CartManager mCartManager;

  private MutableLiveData<List<ItemsResponse>> imageData;
  private MutableLiveData<List<CartItem>> cartListData;
  private MutableLiveData<Pair<Integer,Integer>> priceDiscountPairData;

  public MutableLiveData<List<ItemsResponse>> imageData() {
    if(imageData == null){
      imageData = new MutableLiveData<>();
    }
    return imageData;
  }

  public ListViewModel(Application application){
    super(application);
    mCartManager = new CartManager(this);
    Repository repository = new Repository(application);
    repository.getAllItems().observe(this, this::handleResponse);
  }

  private void handleResponse(ApiResponse<List<ItemsResponse>,String> response) {
    switch (response.getStatus()){
      case FAILURE: handleFailure(response.getFailure()); break;
      case SUCCESS: handleSuccess(response.getSuccess()); break;
      case LOADING: break;
    }
  }

  private void handleSuccess(List<ItemsResponse> success) {
    imageData().setValue(success);
  }

  private void handleFailure(String failure) {
    imageData().setValue(null);
  }

  public void addCartItem(ItemsResponse item){
    mCartManager.convertToCart(item);
  }

  public MutableLiveData<List<CartItem>> getCartListData() {
    if(cartListData == null){
      cartListData = new MutableLiveData<>();
    }
    return cartListData;
  }

  public void updatedCartItem(CartItem item){
    mCartManager.updatedCartItem(item);
  }

  public void clearData() {
    mCartManager.clearData();
  }

  public MutableLiveData<Pair<Integer, Integer>> getPriceDiscountPairData() {
    if(priceDiscountPairData == null){
      priceDiscountPairData = new MutableLiveData<>();
    }
    return priceDiscountPairData;
  }
}
