package com.example.diy_indicator.util;

import android.content.Context;
import android.graphics.Point;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

/**
 * Created by Administrator on 2016/10/8.
 */
public class ScreenSize {
    /**
     *经过测试 返回point和返回DisplayMetrics都能获得屏幕分辨率
     */

    public static Point getScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return point;
    }
    public static DisplayMetrics getScreenSize2(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
    public static float dp2px(Context context , int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP , dp ,context.getResources().getDisplayMetrics());
    }
}
