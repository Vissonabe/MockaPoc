package com.example.viswanathankp.mockapoc.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ApiResponse<S, F> {

  @NonNull
  private Status mStatus;
  @Nullable
  private S mSuccess;
  @Nullable
  private F mFailure;

  private ApiResponse(@NonNull Status status, @Nullable S sucess, @Nullable F failure){
    mStatus = status;
    mSuccess = sucess;
    mFailure = failure;
  }

  public static <S, F> ApiResponse<S, F> failure(F cls){
    return new ApiResponse<>(Status.FAILURE, null, cls);
  }

  public static <S, F> ApiResponse<S, F> success(@NonNull S cls){
    return new ApiResponse<>(Status.SUCCESS, cls, null);
  }

  @Nullable
  public F getFailure() {
    return mFailure;
  }

  @Nullable
  public S getSuccess() {
    return mSuccess;
  }

  @NonNull
  public Status getStatus() {
    return mStatus;
  }
}
