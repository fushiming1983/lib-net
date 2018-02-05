package com.suning.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;

/**
 * Copyright (C), 2002-2017, 苏宁易购电子商务有限公司
 * Author : 13120678
 * Date   : 17/8/1 上午9:36
 * Desc   :
 */
public class AndroidLifecycleUtils {

    public static boolean canLoadImage(Context context) {
        if(context == null) {
            return false;
        } else if(!(context instanceof Activity)) {
            return true;
        } else {
            Activity activity = (Activity)context;
            return canLoadImage(activity);
        }
    }

    public static boolean canLoadImage(Activity activity) {
        if(activity == null) {
            return false;
        } else {
            boolean destroyed = Build.VERSION.SDK_INT >= 17 && activity.isDestroyed();
            return !destroyed && !activity.isFinishing();
        }
    }

}
