package com.myliaobei.app;

import de.robv.android.xposed.XSharedPreferences;

public class pre {
    private static XSharedPreferences instance = null;

    private static XSharedPreferences getInstance() {
        if (instance == null) {
            instance = new XSharedPreferences(pre.class.getPackage().getName());
            instance.makeWorldReadable();
        } else {
            instance.reload();
        }
        return instance;
    }
    public static boolean open() {
        return getInstance().getBoolean("open", true);
    }
    public static boolean amount() {
        return getInstance().getBoolean("amount", true);
    }
    public static boolean self() {
        return getInstance().getBoolean("self", false);
    }
    public static String set_uid() {
        return getInstance().getString("set_uid", "").replace(" ", "");
    }
    public static String set_uuid() {
        return getInstance().getString("set_uuid", "").replace(" ", "");
    }
    public static String set_token() {
        return getInstance().getString("set_token", "").replace(" ", "");
    }
    public static String how_to_qiang() {
        return getInstance().getString("how_to_qiang", "1800").replace(" ", "");
    }
}
