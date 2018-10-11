package com.example.viswanathankp.mockapoc.discount;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

class DiscountViewModel extends ViewModel {
  private MutableLiveData<List<DiscountModel>> mLiveData;

  DiscountViewModel(){
    generateLocalData();
  }

  private void generateLocalData(){
    List<DiscountModel> list = new ArrayList<>();
    list.add(new DiscountModel("Discount 1", "0%"));
    list.add(new DiscountModel("Discount 2", "10%"));
    list.add(new DiscountModel("Discount 3", "50%"));
    list.add(new DiscountModel("Discount 4", "100%"));
    getLiveData().setValue(list);
  }

  MutableLiveData<List<DiscountModel>> getLiveData() {
    if(mLiveData == null){
      mLiveData = new MutableLiveData<>();
    }
    return mLiveData;
  }
}
