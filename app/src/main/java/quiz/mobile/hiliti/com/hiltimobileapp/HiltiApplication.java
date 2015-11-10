package quiz.mobile.hiliti.com.hiltimobileapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by vaishu on 02-11-2015.
 */
public class HiltiApplication extends Application {

    private static HiltiApplication hInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        hInstance = this;
    }
    public static HiltiApplication gethInstance(){
        return hInstance;
    }
    public static Context getAppContext(){
        return hInstance.getApplicationContext();
    }
}
