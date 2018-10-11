package com.example.viswanathankp.mockapoc.main;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.viswanathankp.mockapoc.R;
import com.example.viswanathankp.mockapoc.cart.CartFragment;
import com.example.viswanathankp.mockapoc.library.OnLibraryInteractionListener;
import com.example.viswanathankp.mockapoc.discount.DiscountFragment;
import com.example.viswanathankp.mockapoc.library.LibraryFragment;
import com.example.viswanathankp.mockapoc.list.ListFragment;

public class MainActivity extends AppCompatActivity implements OnLibraryInteractionListener {

  ListViewModel listViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    listViewModel = ViewModelProviders.of(this).get(ListViewModel.class);

    if(savedInstanceState == null){
      getSupportFragmentManager().beginTransaction().add(R.id.lib_frame, LibraryFragment.newInstance()).commit();
      getSupportFragmentManager().beginTransaction().add(R.id.stock_frame, CartFragment.newInstance()).commit();
    }
  }

  public ListViewModel getListViewModel(){
    return listViewModel;
  }

  @Override
  public void onShowDiscountsClicked() {
    getSupportFragmentManager().beginTransaction().add(R.id.lib_frame, DiscountFragment.newInstance()).addToBackStack("discount").commit();
  }

  @Override
  public void onShowItemsClicked() {
    getSupportFragmentManager().beginTransaction().add(R.id.lib_frame, ListFragment.newInstance()).addToBackStack("list").commit();
  }
}
