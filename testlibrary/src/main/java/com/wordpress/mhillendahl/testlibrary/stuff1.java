package com.wordpress.mhillendahl.testlibrary;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Pattern;

//encrypt stuff
import java.io.*;

/**
 * Created by MichaelHillendahl on 6/23/2016.
 */
public class stuff1 {

    public static String getCoords(){

        //String coords = "34.0195 N 118.4912 W";

        return "34.0195 N 118.4912 W";

    }

    public static List<String> getApps(Context context){

        PackageManager pm = context.getPackageManager();

        List<ApplicationInfo> listAppInfo = pm.getInstalledApplications(0);

        List<String> apps = new ArrayList<String>();

        for (ApplicationInfo app : listAppInfo) {
            apps.add(app.className);
        }

        return apps;
    }

    public static boolean isLegal(String s, String c){
        return s.matches("[a-zA-Z]+") && c.matches("[a-zA-Z]+");
    }

    public static String encrypt(String str, String cy){

        String out = "";
        String strOrig=str;
        str = str.toUpperCase();
        cy = cy.toUpperCase();

        for (int i = 0; i< str.length(); i++)
        {
            int range = 90-65+1; //Z - A +1
            int shift = (int)(cy.charAt(i%cy.length())-65);
            int oldchar = (int)(str.charAt(i));
            int newchar = ((oldchar-65)+shift)%range + 65;

            //remember original case
            if ( (int)(strOrig.charAt(i)) > 90 )
                newchar += (97-65); //a - A

            out+=(char)newchar;
        }

        return out;
    }

    public static String decrypt(String str, String cy){

        String out = "";
        String strOrig=str;
        str = str.toUpperCase();
        cy = cy.toUpperCase();

        for (int i = 0; i< str.length(); i++)
        {
            int range = 90-65+1; //Z - A +1
            int shift = (int)(cy.charAt(i%cy.length())-65);
            int oldchar = (int)(str.charAt(i));
            int newchar = ((oldchar-65)+range-shift)%range + 65;

            //remember original case
            if ( (int)(strOrig.charAt(i)) > 90 )
                newchar += (97-65); //a - A

            out+=(char)newchar;
        }

        return out;
    }

    public static boolean isNum(String s){
        return s.matches("\\d+\\.?\\d*|\\d*\\.?\\d+");
    }

    //public static void sort(List<Integer> a) {
    public static List<Integer> sort(List<Integer> a) {
        for (int i = 0; i<a.size()-1; i++) {                    //consider every index, starting w 0
            int min = a.get(i);                                 //remember the value at this index
            for (int j = i + 1; j < a.size() - 1 - i; j++) {    //  consider all subsequent indices
                if (a.get(j) < a.get(i))                        //    if lower values are found
                    min = j;                                    //      remember the index of the lowest value
            }
            if (min < a.get(i)) {                               //  if a lower value was found,
                int num = a.remove(min);                        //    remove and store the lower value.
                a.add(min, a.get(i));                           //    replace it w the higher number
                a.set(i, num);                                  //    assign the lower number to the index of the higher number
            }
        }

        return a;
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

