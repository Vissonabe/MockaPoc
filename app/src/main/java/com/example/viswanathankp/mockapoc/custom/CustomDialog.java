package com.example.viswanathankp.mockapoc.custom;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.viswanathankp.mockapoc.R;
import com.example.viswanathankp.mockapoc.cart.CartItem;

public class CustomDialog extends Dialog implements View.OnClickListener {
 Button yes;
 Button no;
 TextView title;
 Button subBtn;
 Button addBtn;
 EditText editTxt;

  private CartItem mCartItem;
  private CustomDialoglistener mDialoglistener;
  private RadioGroup mRadioGroup;
  private RadioButton changedRadioBtn;
  private TextView errorTxt;

  public CustomDialog(Context context, @NonNull CustomDialoglistener listener) {
    super(context);
    mDialoglistener = listener;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.layout_custom_dialog);
    setCancelable(false);
    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
    lp.gravity = Gravity.CENTER;

    if(getWindow() != null) {
      getWindow().setAttributes(lp);
    }
  }

  private void initView(){
    title = findViewById(R.id.title);
    subBtn = findViewById(R.id.subBtn);
    addBtn = findViewById(R.id.addBtn);
    editTxt = findViewById(R.id.qtyEditTxt);
    yes = findViewById(R.id.saveBtn);
    no = findViewById(R.id.cancelBtn);
    errorTxt = findViewById(R.id.error_txt);
    mRadioGroup = findViewById(R.id.radio_group);
    mRadioGroup.setOnCheckedChangeListener((radioGroup, i) -> changedRadioBtn = radioGroup.findViewById(i));
    changedRadioBtn = (RadioButton) mRadioGroup.getChildAt(0);
    yes.setOnClickListener(this);
    no.setOnClickListener(this);
    addBtn.setOnClickListener(this);
    subBtn.setOnClickListener(this);
  }

  public void setData(CartItem item){
    show();
    initView();
    mCartItem = item;
    title.setText(mCartItem.getTitle());
    editTxt.setText(String.valueOf(mCartItem.getCount()));
    ((RadioButton)mRadioGroup.getChildAt(childIndex(mCartItem.getDiscountPercentage()))).setChecked(true);
  }

  private int childIndex(int value){
    switch (value){
      case 0: return  0;
      case 10: return  1;
      case 50: return  2;
      case 100: return  3;
      default: return 0;
    }
  }

  void increment() {
    Integer i = Integer.parseInt(editTxt.getText().toString());
    if(i < 1000) {
      editTxt.setText(String.valueOf(++i));
    }
  }


  void decrement() {
    Integer i = Integer.parseInt(editTxt.getText().toString());
    if(i > 1) {
      editTxt.setText(String.valueOf(--i));
    }
  }


  public void save() {
    if(Integer.parseInt(editTxt.getText().toString()) > 0 && Integer.parseInt(editTxt.getText().toString()) <= 1000){
      if(mDialoglistener != null && isCartChanged()){
        mCartItem.setCount(Integer.parseInt(editTxt.getText().toString()));
        mCartItem.setDiscountPercentage(Integer.parseInt(changedRadioBtn.getTag().toString()));
        mDialoglistener.onSaveClicked(mCartItem);
      }
      dismiss();
    } else {
      errorTxt.setVisibility(View.VISIBLE);
    }
  }

  private boolean isCartChanged(){
    return mCartItem.getCount() != Integer.parseInt(editTxt.getText().toString()) ||
        mCartItem.getDiscountPercentage() != Integer.parseInt(changedRadioBtn.getTag().toString());
  }

  public void cancel() {
    dismiss();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.saveBtn : save(); break;
      case R.id.cancelBtn : cancel(); break;
      case R.id.addBtn : increment(); break;
      case R.id.subBtn :decrement(); break;
    }
  }
}
