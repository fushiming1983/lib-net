package com.suning.baseui.utlis;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

/**
 * @author 彭赞
 * @ClassName: DensityUtil
 * @Description: 尺寸工具类
 * @date 2016/9/8
 * @Version 1.0
 */
public class DensityUtil {

    public static int SCREEN_WIDTH_PIXELS;
    public static int SCREEN_HEIGHT_PIXELS;
    public static float SCREEN_DENSITY;
    public static int SCREEN_WIDTH_DP;
    public static int SCREEN_HEIGHT_DP;
    public static int STATUS_BAT_HEIGHT = 0;
    private static boolean sInitialed;

    public static void init(Context context) {
        if (sInitialed || context == null) {
            return;
        }
        sInitialed = true;
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH_PIXELS = dm.widthPixels;
        SCREEN_HEIGHT_PIXELS = dm.heightPixels;
        SCREEN_DENSITY = dm.density;
        SCREEN_WIDTH_DP = (int) (SCREEN_WIDTH_PIXELS / dm.density);
        SCREEN_HEIGHT_DP = (int) (SCREEN_HEIGHT_PIXELS / dm.density);
    }

    public static int dp2px(float dp) {
        final float scale = SCREEN_DENSITY;
        return (int) (dp * scale + 0.5f);
    }

    public static int designedDP2px(float designedDp) {
        if (SCREEN_WIDTH_DP != 480) {
            designedDp = designedDp * SCREEN_WIDTH_DP / 480;
        }
        return dp2px(designedDp);
    }

    public static int getStateBarHeight(Context context) {
        if (STATUS_BAT_HEIGHT > 0) return STATUS_BAT_HEIGHT;
        Resources resources = context.getResources();
        try {
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            return STATUS_BAT_HEIGHT = resources.getDimensionPixelSize(resourceId);
        } catch (Exception e) {
            return STATUS_BAT_HEIGHT = dp2px(24);
        }
    }

    public static void setPadding(final View view, float left, float top, float right, float bottom) {
        view.setPadding(designedDP2px(left), dp2px(top), designedDP2px(right), dp2px(bottom));
    }
}
