package com.example.viswanathankp.mockapoc.main;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.support.annotation.NonNull;

public class LifeCycleViewModel extends AndroidViewModel implements LifecycleOwner {

  private LifecycleRegistry mLifecycle;

  LifeCycleViewModel(Application application){
    super(application);
    mLifecycle = new LifecycleRegistry(this);
    updateState();
  }

  private void updateState(){
    mLifecycle.markState(Lifecycle.State.RESUMED);
  }

  @Override
  protected void onCleared() {
    super.onCleared();
    mLifecycle.markState(Lifecycle.State.DESTROYED);
  }

  @NonNull
  @Override
  public Lifecycle getLifecycle() {
    return mLifecycle;
  }
}
