package com.example.helpers;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentChanger {
    private static final String TAG = FragmentChanger.class.getSimpleName();

    public static void replace(FragmentManager manager, int container, Fragment fragment, Bundle args, Boolean backStack) {
        Log.w(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        FragmentTransaction t = manager.beginTransaction();

        if (backStack) t.addToBackStack(null).replace(container, fragment).commit();
        else t.replace(container, fragment).commit();
    }

    public static void add(FragmentManager manager, int container, Fragment fragment, Bundle args, Boolean backStack) {
        Log.w(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        FragmentTransaction t = manager.beginTransaction();

        if (backStack) t.addToBackStack(null).add(container, fragment).commit();
        else t.add(container, fragment).commit();
    }
}
