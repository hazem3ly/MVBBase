package com.hazem.mvbbase.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hazem.mvbbase.R;
import com.hazem.mvbbase.observers.BaseView;
import com.hazem.mvbbase.observers.MenuInit;
import com.hazem.mvbbase.observers.ToolbarInit;
import com.hazem.mvbbase.presenters.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    T presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(loadResourceLayout());
        ButterKnife.bind(this);

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
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(toolbarImpl.initToolbarTitle());
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(toolbarImpl.showHomeAsUp());
            getSupportActionBar().setDisplayShowHomeEnabled(toolbarImpl.showHomeAsUp());


        }

        presenter = initPresenter();
        presenter.setContext(this);
        presenter.create(savedInstanceState);

        initViews();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this instanceof MenuInit) {
            MenuInit menuInit = (MenuInit) this;
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(menuInit.initMenuRes(), menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onMenuItemClicked(item.getItemId());
        return true;
    }

    protected abstract void onMenuItemClicked(int itemId);

    protected abstract void initViews();

    public abstract int loadResourceLayout();

    public abstract T initPresenter();

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    public void showMessage(String msg) {

    }

    public T getPresenter() {
        return presenter;
    }


    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

}
