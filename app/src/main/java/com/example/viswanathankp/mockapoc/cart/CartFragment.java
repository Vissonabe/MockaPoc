package com.example.viswanathankp.mockapoc.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.viswanathankp.mockapoc.R;
import com.example.viswanathankp.mockapoc.custom.CustomDialog;
import com.example.viswanathankp.mockapoc.custom.CustomDialoglistener;
import com.example.viswanathankp.mockapoc.main.ListViewModel;
import com.example.viswanathankp.mockapoc.main.MainActivity;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment implements CustomDialoglistener, View.OnClickListener, CartListener {

  private ListViewModel mViewModel;
  private CartAdapter adapter;
  private CustomDialog dialog;
  private TextView totalPrice;
  private TextView discountPrice;
  private TextView chargeTxt;

  public static CartFragment newInstance() {
    return new CartFragment();
  }

  public CartFragment(){
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.cart_fragment, container, false);
    initView(view);
    return view;
  }

  private void initView(View view){
    RecyclerView recyclerView = view.findViewById(R.id.cart_recycler);
    totalPrice = view.findViewById(R.id.totalPrice);
    discountPrice = view.findViewById(R.id.price);
    TextView clearTxt = view.findViewById(R.id.clearTxt);
    clearTxt.setOnClickListener(this);
    chargeTxt = view.findViewById(R.id.chargeTxt);
    adapter = new CartAdapter(this);
    recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    recyclerView.setAdapter(adapter);
    dialog = new CustomDialog(this.getContext(), this);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ((MainActivity)getActivity()).getListViewModel();
    mViewModel.getCartListData().observe(this,this::cartListData);
    mViewModel.getPriceDiscountPairData().observe(this,this::displaytotalData);
  }

  private void displaytotalData(Pair<Integer,Integer> integerIntegerPair) {
    chargeTxt.setText(String.format(Locale.getDefault(), "Charge $ %d", (integerIntegerPair.first - integerIntegerPair.second)));
    totalPrice.setText(String.valueOf(integerIntegerPair.first));
    discountPrice.setText(String.valueOf(integerIntegerPair.second));
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  private void cartListData(List<CartItem> cartItems) {
    adapter.setList(cartItems);
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if(dialog != null){
      dialog.dismiss();
      dialog = null;
    }
  }

  @Override
  public void onSaveClicked(CartItem item) {
    mViewModel.updatedCartItem(item);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.clearTxt: mViewModel.clearData(); break;
    }
  }

  @Override
  public void onItemClicked(CartItem item) {
    dialog.setData(item);
  }
}
