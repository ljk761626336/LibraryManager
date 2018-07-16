package com.titan.baselibrary.util;

import android.Manifest;

/**
 * Created by otitan_li on 2018/6/19.
 * 存放系统常用的静态常量
 */

public class Constant {

    // 所需的定位权限，sd读写权限
    public static final String[] PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
    };

}
