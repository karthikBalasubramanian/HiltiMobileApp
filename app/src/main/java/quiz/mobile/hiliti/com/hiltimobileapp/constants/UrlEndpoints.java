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
        public static final String URL_QUESTIONS=API_SERVER+"/question/findQuestionByTopic";
        public static final String URL_TOPICS=API_SERVER+"/topic/viewAllTopics";
        public static final String URL_LEADERS=API_SERVER+"/userProfile/findFirstByOrderBytotalScore";
        public static final String DEFAULT_IMAGE_URL ="/Profile/displayPicture/Shawn_Tok_Profile.png";
        public static final String QUESTION_PARAMS_TOPIC ="topicName=";
        public static final String QUESTION_PARAMS_DIFFICULTY="difficulty=";
        public static final String URL_ENCODER = "UTF-8";
        public static final String QUESTION_PARAM_QNO = "qno=";
        public static final String ANSWERED_CORRECT_URL="/answeredCorrect/createAnsweredCorrect";
        public static final String Q_ID_PARAM_ANSWERED_="questionId=";
        public static final String EMP_ID_PARAM_ANSWERED="empId=";
        public static final String URL_UPDATE_ALL_ANSWERS="/answeredCorrect/updateAllAnswers";

}
