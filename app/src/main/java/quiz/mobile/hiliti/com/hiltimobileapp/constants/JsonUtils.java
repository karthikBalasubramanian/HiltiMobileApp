package quiz.mobile.hiliti.com.hiltimobileapp.constants;

import com.android.volley.RequestQueue;

import org.json.JSONArray;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.json.Parser;
import quiz.mobile.hiliti.com.hiltimobileapp.json.Requestor;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;

/**
 * Created by vaishu on 04-11-2015.
 */
public class JsonUtils {
    public static ArrayList<TrainingPojo> getTrainingMaterials(RequestQueue requestQueue){
        JSONArray jsonArray = Requestor.requestTrainingJSON(requestQueue,UrlEndpoints.URL_TRAINING,Tags.TRAINING_TAG);
        ArrayList<TrainingPojo> trainingPojos = Parser.getTrainingMaterials(jsonArray);
        return trainingPojos;
    }

    public static ArrayList<Question> getQuestions(RequestQueue requestQueue){
        JSONArray jsonArray = Requestor.requestTrainingJSON(requestQueue,UrlEndpoints.URL_QUESTIONS,Tags.QUESTION_TAG);
        ArrayList<Question> questionPojos = Parser.getQuestions(jsonArray);
        return questionPojos;
    }
}
