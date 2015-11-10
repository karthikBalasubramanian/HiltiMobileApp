package quiz.mobile.hiliti.com.hiltimobileapp.utility;

import android.os.Build;

/**
 * Created by vaishu on 03-11-2015.
 */
public class Util {
    public static boolean isLollipopOrGreater() {
        return Build.VERSION.SDK_INT >= 21 ? true : false;
    }
    public static boolean isJellyBeanOrGreater(){
        return Build.VERSION.SDK_INT>=16?true:false;
    }
}