package com.hazem.mvbbase.presenters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import net.corpy.ecommerce.observers.BaseView;

public abstract class BasePresenter<V extends BaseView>  {

    private Context context;
    private V view;

    BasePresenter(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }



    public Context getContext() {
        return getView().getContext();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void startLogic(Bundle savedInstanceState) {
    }


    public abstract void resume();

    public abstract void pause();

    public abstract void create(@Nullable Bundle savedInstance);

    public abstract void destroy();

    public abstract void start();

    public abstract void stop();
}
