package com.example.helpers;

import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentChanger {
    private static final String TAG = FragmentChanger.class.getSimpleName();

    public static void replace(FragmentManager manager, int container, Fragment fragment, Boolean backStack, Bundle args) {
        Log.w(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        FragmentTransaction t = manager.beginTransaction();

        if (backStack) t.addToBackStack(null).replace(container, fragment).commit();
        else t.replace(container, fragment).commit();
    }

    public static void replaceWithAnim(FragmentManager manager, int container, Fragment fragment, Boolean backStack, Bundle args, int anim1, int anim2) {
        Log.w(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        FragmentTransaction t = manager.beginTransaction();

        if (backStack) t.addToBackStack(null).setCustomAnimations(anim1, anim2).replace(container, fragment).commit();
        else t.replace(container, fragment).commit();
    }

    public static void add(FragmentManager manager, int container, Fragment fragment, Boolean backStack, Bundle args) {
        Log.w(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        FragmentTransaction t = manager.beginTransaction();

        if (backStack) t.addToBackStack(null).add(container, fragment).commit();
        else t.add(container, fragment).commit();
    }

    public static void addWithAnim(FragmentManager manager, int container, Fragment fragment, Boolean backStack, Bundle args, int anim1, int anim2) {
        Log.w(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        FragmentTransaction t = manager.beginTransaction();

        if (backStack) t.addToBackStack(null).setCustomAnimations(anim1, anim2).add(container, fragment).commit();
        else t.add(container, fragment).commit();
    }
}
