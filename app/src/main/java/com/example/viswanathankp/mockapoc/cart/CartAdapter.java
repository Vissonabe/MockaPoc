package com.example.viswanathankp.mockapoc.cart;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.viswanathankp.mockapoc.R;
import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

  private List<CartItem> mList;
  private CartListener mCartListener;

  CartAdapter(CartListener listener){
    mCartListener = listener;
    mList = new ArrayList<>();
  }

  public void setList(List<CartItem> list) {
    mList = new ArrayList<>(list);
    notifyDataSetChanged();
  }

  public void setData(CartItem item) {
    mList.add(mList.size()-2, item);
    notifyItemInserted(mList.size() - 2);
  }

  @NonNull
  @Override
  public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false);
    return new CartAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
    if(mList.size() > 0 && position < mList.size()){
      CartItem data = mList.get(position);
      holder.bindData(data);
    }
  }
  @Override
  public int getItemCount() {
    return mList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView title,count, price;
    private View parent;

    ViewHolder(View itemView) {
      super(itemView);
      parent = itemView;
      title = itemView.findViewById(R.id.title);
      count = itemView.findViewById(R.id.count);
      price = itemView.findViewById(R.id.price);
      itemView.setOnClickListener(this);
    }

    void bindData(CartItem data){
      parent.setTag(data);
      title.setText(data.getTitle());
      price.setText(String.format("$%s",String.valueOf(data.getPrice())));
      count.setText(String.format("x%s", String.valueOf(data.getCount())));
    }

    @Override
    public void onClick(View v) {
      if(mCartListener != null){
        mCartListener.onItemClicked((CartItem)v.getTag());
      }
    }
  }
}
