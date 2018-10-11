package com.example.viswanathankp.mockapoc.discount;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.viswanathankp.mockapoc.R;
import java.util.ArrayList;
import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.ViewHolder> {
  private List<DiscountModel> mList;

  DiscountAdapter(){
    mList = new ArrayList<>();
  }

  public void setList(List<DiscountModel> list) {
    mList.addAll(list);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public DiscountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_discount_item, parent, false);
    return new DiscountAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull DiscountAdapter.ViewHolder holder, int position) {
    if(mList.size() > 0 && position < mList.size()){
      DiscountModel data = mList.get(position);
      holder.bindData(data);
    }
  }

  @Override
  public int getItemCount() {
    return mList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    private TextView title, price;

    ViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.title);
      price = itemView.findViewById(R.id.discount);
    }

    void bindData(DiscountModel data){
      title.setText(data.getName());
      price.setText(data.getDiscount());
    }
  }
}
