package com.hazem.mvbbase.navigation;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public interface navigatorCallback {

    void navigateToActivity(@NonNull Class<?> clazz);

    void navigateToFragment(Fragment fragment, int resPlaceholder, boolean addToBackStack);

    void attachExtraContract(serializableContract contract);


}
