package com.example.viswanathankp.mockapoc.discount;

public class DiscountModel {
  private String mName;
  private String mDiscount;

  DiscountModel(String name, String discount){
    mName = name;
    mDiscount = discount;
  }

  public String getName() {
    return mName;
  }

  public String getDiscount() {
    return mDiscount;
  }
}
