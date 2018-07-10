package com.hazem.mvbbase.observers;

import android.support.v7.widget.Toolbar;

/**
 * Created by Mickey on 4/6/17.
 */

public interface ToolbarInit {

    Toolbar getToolbar();
    String initToolbarTitle();
    void onToolbarActionClicked();
    boolean showHomeAsUp();
    int toolbarTextColor();

}
