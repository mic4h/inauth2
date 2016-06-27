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

    public static boolean isLegal(String s, String kw){
        return kw.matches("[a-zA-Z]+") && kw.matches("[a-zA-Z]+");
    }

    public static String encrypt(String str, String kw){

        //this alg is expanded a lot for readability. it could be done in fewer lines but that's not desirable for this project.

        String out = "";            //prepare empty string to return as output
        String strOrig=str;         //remember original state of input text
        str = str.toUpperCase();    //force text to all caps to simplify encryption (case restored after encryption, later)
        kw = kw.toUpperCase();      //same with keyword

        for (int i = 0; i< str.length(); i++)                   //consider each char in the str
        {
            int range = 90-65+1; //Z - A +1                     //26 letters (derived from ascii values)
            int shift = (int)(kw.charAt(i%kw.length())-65);     //pick correct char from keyword, subtract 65 so that 'a' has a shift value of 0, and force int type
            int oldchar = (int)(str.charAt(i));                 //remember original char in text
            int newchar = ((oldchar-65)+shift)%range + 65;      //subtract 65 from orig char so that a = 0, apply shift to find new char, mod range in case we exceeded 26 (z), and add 65 back to properly represent an ascii value

            //remember original case
            if ( (int)(strOrig.charAt(i)) > 90 )                //if the original char was lowercase (ascii codes greater than 90)
                newchar += (97-65); //a - A                     //shift the encrypted char from uppercase to lowercase ascii code

            out+=(char)newchar;                                 //append new encrypted char to output string
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
            int newchar = ((oldchar-65)+range-shift)%range + 65; // +range before mod to ensure positive result
                                                                 // -range instead of +range to decrypt instead of encrypt
            //remember original case
            if ( (int)(strOrig.charAt(i)) > 90 )
                newchar += (97-65); //a - A

            out+=(char)newchar;
        }

        return out;
    }

    /*
    public static boolean isNum(String s){
        return s.matches("\\d+\\.?\\d*|\\d*\\.?\\d+");
    }
    */

    public static boolean isNum(String s){
        return s.matches("\\d+");
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

