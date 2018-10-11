package com.example.viswanathankp.mockapoc.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.viswanathankp.mockapoc.R;
import com.example.viswanathankp.mockapoc.main.ListViewModel;
import com.example.viswanathankp.mockapoc.main.MainActivity;
import java.util.List;

public class ListFragment extends Fragment implements ListListener {

  private ListViewModel mViewModel;
  private ItemsAdapter adapter;
  private ProgressBar progress;
  private RecyclerView recyclerView;
  private TextView failureText;

  public static ListFragment newInstance() {
    return new ListFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.list_fragment, container, false);
    initView(view);
    return view;
  }

  private void initView(View view){
    progress = view.findViewById(R.id.progress);
    failureText = view.findViewById(R.id.failure);
    recyclerView = view.findViewById(R.id.list_recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ((MainActivity) getActivity()).getListViewModel();
    adapter = new ItemsAdapter(this);
    recyclerView.setAdapter(adapter);
    mViewModel.imageData().observe(this,this::displayItems);
  }

  private void displayItems(List<ItemsResponse> itemsResponses) {
    progress.setVisibility(View.GONE);
    if(itemsResponses != null && !itemsResponses.isEmpty()) {
      failureText.setVisibility(View.GONE);
      adapter.setList(itemsResponses);
    } else {
      failureText.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public void onListItemClicked(ItemsResponse res) {
    mViewModel.addCartItem(res);
  }
}
