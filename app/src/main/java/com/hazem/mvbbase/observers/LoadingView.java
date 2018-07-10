package com.hazem.mvbbase.observers;

public interface LoadingView extends BaseView {

    void onLoading(String msg);
    void didLoad();

}
