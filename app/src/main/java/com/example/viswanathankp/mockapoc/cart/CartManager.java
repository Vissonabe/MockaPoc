package com.example.viswanathankp.mockapoc.cart;

import android.util.Pair;
import com.example.viswanathankp.mockapoc.list.ItemsResponse;
import com.example.viswanathankp.mockapoc.main.ListViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartManager {

  private final static int minRange = 10;
  private final static int maxRange = 99;

  private List<CartItem> mCartItemList;
  private ListViewModel mViewmodel;
  private Random random;

  public CartManager(ListViewModel vm){
    mViewmodel = vm;
    mCartItemList = new ArrayList<>();
    random = new Random();
  }

  private void initCartList(CartItem c){
    if(mCartItemList.contains(c)){
      int index = mCartItemList.indexOf(c);
      CartItem item = mCartItemList.get(index);
      int count = item.getCount();
      item.setCount(++count);
      mCartItemList.set(index, item);
    } else {
      mCartItemList.add(c);
    }
    calculatePrice();
  }

  private void calculatePrice(){
    int tempTotal = 0, tempDiscount = 0;
    for (CartItem item :mCartItemList) {
      tempTotal += item.getPrice();
      tempDiscount += item.getDiscountPrice();
    }
    mViewmodel.getPriceDiscountPairData().setValue(new Pair<>(tempTotal,tempDiscount));
  }

  private void populateCartList() {
    if(!mCartItemList.isEmpty()){
      mViewmodel.getCartListData().setValue(mCartItemList);
    }
  }

  public void updatedCartItem(CartItem item){
    int it = mCartItemList.indexOf(item);
    mCartItemList.set(it, item);
    populateCartList();
    calculatePrice();
  }

  public void convertToCart(ItemsResponse item){
    CartItem c = new CartItem(item.Id, item.title, item.randomPrice);
    //CartItem c = new CartItem(item.Id, item.title, randomNumberInRange());
    c.setCount(1);
    initCartList(c);
    populateCartList();
  }

  private int randomNumberInRange() {
    return random.nextInt((maxRange - minRange) + 1) + minRange;
  }

  public void clearData() {
    mCartItemList.clear();
    mViewmodel.getCartListData().setValue(mCartItemList);
    mViewmodel.getPriceDiscountPairData().setValue(new Pair<>(0,0));
  }
}
