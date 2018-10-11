package com.example.viswanathankp.mockapoc.discount;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.viswanathankp.mockapoc.library.OnLibraryInteractionListener;
import com.example.viswanathankp.mockapoc.R;
import com.example.viswanathankp.mockapoc.list.ItemsAdapter;
import java.util.List;

public class DiscountFragment extends Fragment {

  private DiscountAdapter adapter;

  public static DiscountFragment newInstance() {
    return new DiscountFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.discount_fragment, container, false);
    initView(view);
    return view;
  }

  private void initView(View view){
    RecyclerView recyclerView = view.findViewById(R.id.discount_recycler);
    adapter = new DiscountAdapter();
    recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    recyclerView.setAdapter(adapter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    DiscountViewModel viewModel = ViewModelProviders.of(this).get(DiscountViewModel.class);
    viewModel.getLiveData().observe(this, this::displayData);
  }

  private void displayData(List<DiscountModel> discountModels) {
    adapter.setList(discountModels);
  }
}
