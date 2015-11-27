package quiz.mobile.hiliti.com.hiltimobileapp.constants;

import quiz.mobile.hiliti.com.hiltimobileapp.Leaderboard;
import quiz.mobile.hiliti.com.hiltimobileapp.QuestionDisplayActivity;
import quiz.mobile.hiliti.com.hiltimobileapp.TakeQuizOptions;
import quiz.mobile.hiliti.com.hiltimobileapp.TrainingActivity;
import quiz.mobile.hiliti.com.hiltimobileapp.adapter.RecyclerViewAdapterResult;

/**
 * Created by vaishu on 04-11-2015.
 */
public class Tags {

    public static final String PREF_NAME = "HiltiUserLogin";
    public static final String FIRST_NAME = "LoggedUserName";
    public static final String EMAIL = "LoggedUserEmail";
    public static final String PROFILE_PIC = "LoggedUserPic";
    public static final String EMP_ID = "empid";
    public static final String DEPARTMENT = "department";
    public static final String TOTAL_SCORE = "totalScore";
    public static final String LAST_NAME ="UserLastName";
    public static final String MIDDLE_NAME ="UserMiddleName";
    public static final String AS_OF_DATE="asOfDate";
    public static final String PASSWORD = "password";



    public static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    public static final int PRIVATE_MODE = 0;
    public static String TRAINING_TAG= TrainingActivity.class.getSimpleName();
    public static String QUESTION_TAG= QuestionDisplayActivity.class.getSimpleName();
    public static String RESULT_TAG = RecyclerViewAdapterResult.class.getSimpleName();
    public static String TOPIC_TAG= TakeQuizOptions.class.getSimpleName();
    public static String LEADER_TAG= Leaderboard.class.getSimpleName();
    public static final String NO_OF_QUESTION = "NoOfQuestions";
    public static final String TOPIC_LIST = "topicName";
    public static final String DIFFICULTY_LEVELS = "difficulty";
}
