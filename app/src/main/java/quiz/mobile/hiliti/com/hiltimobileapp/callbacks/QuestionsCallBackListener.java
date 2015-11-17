package quiz.mobile.hiliti.com.hiltimobileapp.callbacks;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;

/**
 * Created by Poorna on 16/11/2015.
 */
public interface QuestionsCallBackListener {

    public void getQuestionsList(ArrayList<Question> questionsPojos);
}
