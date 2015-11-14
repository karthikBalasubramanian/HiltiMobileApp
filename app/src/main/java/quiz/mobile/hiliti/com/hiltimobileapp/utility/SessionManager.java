package quiz.mobile.hiliti.com.hiltimobileapp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import quiz.mobile.hiliti.com.hiltimobileapp.constants.Tags;

/**
 * Created by vaishu on 06-11-2015.
 */
public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "HiltiUserLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setUserCred(String username, String email, String profilePic){

        editor.putString(Tags.USER_NAME,username);
        editor.putString(Tags.EMAIL,email);
        editor.putString(Tags.PROFILE_PIC,profilePic);
        editor.commit();
        Log.d(TAG,"user details saved");
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
