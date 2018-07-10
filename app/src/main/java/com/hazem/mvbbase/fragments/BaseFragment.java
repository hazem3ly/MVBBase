package com.hazem.mvbbase.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.hazem.mvbbase.R;
import com.hazem.mvbbase.observers.LoadingView;
import com.hazem.mvbbase.observers.ToolbarInit;
import com.hazem.mvbbase.presenters.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements LoadingView {

    T presenter;
    View rootView;
    private boolean isViewPrepared = false;
    private Bundle savedInstanceState = null;
    private boolean itemsLoaded = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
        presenter.setContext(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(setResourceLayout(), container, false);
        ButterKnife.bind(this, rootView);
        isViewPrepared = true;

        initViews();

        return rootView;
    }

    protected abstract void initViews();


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.create(savedInstanceState);
        isViewPrepared = true;

//        lazyLoad(savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (this instanceof ToolbarInit) {
            final ToolbarInit toolbarImpl = (ToolbarInit) this;

            Toolbar toolbar = toolbarImpl.getToolbar();

            if (toolbar != null) {
                toolbar.setTitleTextColor(getResources().getColor(toolbarImpl.toolbarTextColor()));
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toolbarImpl.onToolbarActionClicked();
                    }
                });
            }

            if (getActivity() instanceof AppCompatActivity) {
                ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

                ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setTitle(toolbarImpl.initToolbarTitle());
                    actionBar.setDisplayHomeAsUpEnabled(toolbarImpl.showHomeAsUp());
                    actionBar.setDisplayShowHomeEnabled(toolbarImpl.showHomeAsUp());
                }
            }



        }

    }

    @Override
    public Context getContext() {
        return getActivity();
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.pause();
    }

    public T getPresenter() {
        return presenter;
    }

    @Override
    public void showMessage(String msg) {

    }

    protected abstract int setResourceLayout();


    protected abstract T initPresenter();


}
