package com.example.viswanathankp.mockapoc.cart;

public class CartItem {

  private String id;
  private String mTitle;
  private int mPrice;
  private int mCount = 1;
  private int mDiscountPercentage = 0;

  CartItem(String d, String title, int price){
    id = d;
    mTitle = title;
    mPrice = price;
  }

  public String getTitle() {
    return mTitle;
  }

  public int getPrice() {
    return mCount * mPrice;
  }

  public int getCount() {
    return mCount;
  }

  public void setCount(int count) {
    mCount = count;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object v) {
    boolean retVal = false;

    if (v instanceof CartItem){
      CartItem ptr = (CartItem) v;
      retVal = ptr.id.equals(this.id) && ptr.getDiscountPercentage() == this.getDiscountPercentage();
    }

    return retVal;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
    hash = 17 * hash + this.getDiscountPrice();
    return hash;
  }

  int getDiscountPrice(){
    switch (mDiscountPercentage){
      case 0: return 0;
      case 10: return getPrice() / 10;
      case 50: return getPrice() / 2;
      case 100: return getPrice();
      default: return mPrice;
    }
  }

  public int getDiscountPercentage() {
    return mDiscountPercentage;
  }

  public void setDiscountPercentage(int discountPercentage) {
    mDiscountPercentage = discountPercentage;
  }
}
