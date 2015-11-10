package quiz.mobile.hiliti.com.hiltimobileapp.logging;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by vaishu on 03-11-2015.
 */
public class Log {

        public static void m(String message) {
        android.util.Log.d("Hilti App", "" + message);
    }

        public static void t(Context context, String message) {
            Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
        }
        public static void T(Context context, String message) {
            Toast.makeText(context, message + "", Toast.LENGTH_LONG).show();
        }

}
