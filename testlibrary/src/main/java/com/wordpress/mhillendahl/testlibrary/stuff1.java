package com.wordpress.mhillendahl.testlibrary;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MichaelHillendahl on 6/23/2016.
 */
public class stuff1 {

    public static String getCoords(){

        //String coords = "34.0195 N 118.4912 W";

        return "34.0195 N 118.4912 W";

    }

    /*
    public static List<String> getApps() {

        final PackageManager pm = getPackageManager();

        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);
        List<String> apps = new ArrayList<String>();

        for (ApplicationInfo packageInfo : packages) {
            //Log.d(TAG, "Installed package :" + packageInfo.packageName);
            //Log.d(TAG, "Source dir : " + packageInfo.sourceDir);
            //Log.d(TAG, "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
            apps.append(packageInfo.packageName);
        }

        return apps;
    }
    */

}

