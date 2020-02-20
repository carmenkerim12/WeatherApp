package com.example.weatherapp.utils;

import com.example.weatherapp.BuildConfig;

public class Applog {
    private static boolean isDebug = BuildConfig.DEBUG;

    public static void e(String tag, String value) {
        if (isDebug) {
            Applog.e(tag, value);
        }
    }

    public static void d(String tag, String value) {
        if (isDebug) {
            Applog.d(tag, value);
        }
    }

    public static void i(String tag, String value) {
        if (isDebug) {
            Applog.i(tag, value);
        }
    }
}
