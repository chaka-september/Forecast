package com.chakaseptember.forecast.data;

/**
 * Created by Chaka on 21/03/2017.
 */

public interface Callback<T> {

    void onSuccess(T t);

    void onFailure(Throwable throwable);
}
