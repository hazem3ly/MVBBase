package com.hazem.mvbbase.navigation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class Navigator implements navigatorCallback {

    private final Context context;
    private serializableContract extraContract = null;

    public Navigator(Context context) {
        this.context = context;
    }


    @Override
    public void navigateToActivity(@NonNull Class<?> clazz) {
        Intent intent = new Intent(context, clazz);
        if (extraContract != null)
            intent.putExtra(extraContract.getKey(), extraContract);
        context.startActivity(intent);
    }

    @Override
    public void navigateToFragment(Fragment fragment, int resPlaceHolder, boolean addToBackStack) {
        if (extraContract != null) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(extraContract.getKey(), extraContract);
            fragment.setArguments(bundle);
        }

        FragmentTransaction ft = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        ft.replace(resPlaceHolder, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack)
            ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    @Override
    public void attachExtraContract(serializableContract contract) {
        this.extraContract = contract;
    }
}
