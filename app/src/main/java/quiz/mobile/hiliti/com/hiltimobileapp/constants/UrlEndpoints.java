package quiz.mobile.hiliti.com.hiltimobileapp.constants;

/**
 * Created by vaishu on 03-11-2015.
 */
public class UrlEndpoints {


        public static final String URL_CHAR_QUESTION = "?";
        public static final String URL_CHAR_AMEPERSAND = "&";
        public static String API_SERVER = "http://202.158.217.93:8080";
        public static String GENERIC_URL_IMAGE_SERVER = "http://202.158.217.93:8081/Hilti";
        public static final String URL_TRAINING = API_SERVER+"/training/viewAlltrainings";
        public static final String LOGIN_URL=API_SERVER+"/userProfile/findEmpByEmailAndPassword";
        public static final String URL_QUESTIONS=API_SERVER+"/question/viewAllQuestions";
        public static final String URL_TOPICS=API_SERVER+"/topic/viewAllTopics";
        public static final String DEFAULT_IMAGE_URL ="/Profile/displayPicture/Shawn_Tok_Profile.png";


}
