package quiz.mobile.hiliti.com.hiltimobileapp.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.QuestionsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.TrainingMaterialsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.JsonUtils;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;

/**
 * Created by Poorna on 16/11/2015.
 */
public class QuestionAsyncTask extends AsyncTask<Void,Void,ArrayList<Question>>  {

    VolleySingleton volleySingleton;
    RequestQueue mRequestQueue;
    QuestionsCallBackListener questionCallBack;

    String selectedNumberOfQues;
    ArrayList<CharSequence> selectedTopics;
    ArrayList<CharSequence> selectedDifficultyLevel;

    public QuestionAsyncTask(QuestionsCallBackListener questionCallBack,String selectedNumberOfQues,ArrayList<CharSequence> selectedTopics,ArrayList<CharSequence> selectedDifficultyLevel){
        this.questionCallBack = questionCallBack;
        this.volleySingleton = VolleySingleton.getvSingletonInstance();
        this.mRequestQueue = volleySingleton.getmRequestQueue();
        this.selectedNumberOfQues = selectedNumberOfQues;
        this.selectedTopics = selectedTopics;
        this.selectedDifficultyLevel = selectedDifficultyLevel;
    }


    @Override
    protected ArrayList<Question> doInBackground(Void... params) {
        Log.m("Question Task triggered");
        ArrayList<Question> questionsPojos = JsonUtils.getQuestions(mRequestQueue, pass pojo);
        return questionsPojos;
    }
    @Override
    protected void onPostExecute(ArrayList<Question> questionPojos) {
        if (questionCallBack != null) {
            Log.m("Question onPost executed.");
            questionCallBack.getQuestionsList(questionPojos);
        }
    }

}
