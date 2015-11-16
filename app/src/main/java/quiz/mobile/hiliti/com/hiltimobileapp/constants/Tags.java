package quiz.mobile.hiliti.com.hiltimobileapp.constants;

import quiz.mobile.hiliti.com.hiltimobileapp.QuestionDisplayActivity;
import quiz.mobile.hiliti.com.hiltimobileapp.TrainingActivity;

/**
 * Created by vaishu on 04-11-2015.
 */
public class Tags {

    public static final String PREF_NAME = "HiltiUserLogin";
    public static final String USER_NAME = "LoggedUserName";
    public static final String EMAIL = "LoggedUserEmail";
    public static final String PROFILE_PIC = "LoggedUserPic";
    public static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    public static String TRAINING_TAG= TrainingActivity.class.getSimpleName();
    public static String QUESTION_TAG= QuestionDisplayActivity.class.getSimpleName();
}
