package quiz.mobile.hiliti.com.hiltimobileapp.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import quiz.mobile.hiliti.com.hiltimobileapp.constants.Tags;
import quiz.mobile.hiliti.com.hiltimobileapp.json.Endpoints;

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

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(Tags.PREF_NAME, Tags.PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {

        editor.putBoolean(Tags.KEY_IS_LOGGEDIN, isLoggedIn);

        // commit changes
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setUserCred(String username, String email, String profilePic, int empid, String department, int totalScore){

        editor.putString(Tags.USER_NAME,username);
        editor.putString(Tags.EMAIL,email);
        editor.putString(Tags.PROFILE_PIC, Endpoints.getImageFromServer(profilePic));
        editor.putString(Tags.EMP_ID,Integer.toString(empid));
        editor.putString(Tags.DEPARTMENT,department);
        editor.putString(Tags.TOTAL_SCORE,Integer.toString(totalScore));

        editor.commit();
        Log.d(TAG,"user details saved");
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(Tags.KEY_IS_LOGGEDIN, false);
    }

    public void setQuestionRequest(String no_of_questions, String topics, String levels){

        editor.putString(Tags.NO_OF_QUESTION,no_of_questions);
        editor.putString(Tags.TOPIC_LIST,topics);
        editor.putString(Tags.DIFFICULTY_LEVELS, levels);
        editor.commit();
        Log.d(TAG,"Question request details saved");
    }

}
