package com.example.viswanathankp.mockapoc.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.viswanathankp.mockapoc.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

  private List<ItemsResponse> mList;
  private ListListener mListener;

  ItemsAdapter(ListListener listener){
    mListener = listener;
    mList = new ArrayList<>();
  }

  public void setList(List<ItemsResponse> list) {
    mList.addAll(list);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_recycler_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    if(mList.size() > 0 && position < mList.size()){
      ItemsResponse data = mList.get(position);
      holder.bindData(data);
    }
  }

  @Override
  public int getItemCount() {
    return mList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView img;
    private TextView title, price;
    private View parent;

    ViewHolder(View itemView) {
      super(itemView);
      parent = itemView;
      img =  itemView.findViewById(R.id.img);
      title = itemView.findViewById(R.id.title);
      price = itemView.findViewById(R.id.price);
      itemView.setOnClickListener(this);
    }

    void bindData(ItemsResponse data){
      parent.setTag(data);
      Picasso.with(img.getContext())
          .load(data.thumbnailUrl)
          .placeholder(R.mipmap.ic_launcher)
          .into(img);
      title.setText(data.title);
      price.setText((String.format("$%s", data.randomPrice)));
    }

    @Override
    public void onClick(View v) {
      if(mListener != null){
        mListener.onListItemClicked((ItemsResponse) v.getTag());
      }
    }
  }
}
