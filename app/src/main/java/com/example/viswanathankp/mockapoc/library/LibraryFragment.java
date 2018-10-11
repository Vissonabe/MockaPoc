package com.example.viswanathankp.mockapoc.library;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.viswanathankp.mockapoc.R;

public class LibraryFragment extends Fragment implements View.OnClickListener {

  private OnLibraryInteractionListener mListener;

  public static LibraryFragment newInstance() {
    return new LibraryFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.library_fragment, container, false);
    initView(view);
    return view;
  }

  private void initView(View view){
    TextView discountTxt = view.findViewById(R.id.discount_txt);
    TextView listTxt = view.findViewById(R.id.list_txt);
    discountTxt.setOnClickListener(this);
    listTxt.setOnClickListener(this);
  }


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof OnLibraryInteractionListener) {
      mListener = (OnLibraryInteractionListener) context;
    } else {
      throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.discount_txt: mListener.onShowDiscountsClicked(); break;
      case R.id.list_txt: mListener.onShowItemsClicked(); break;
    }
  }
}
