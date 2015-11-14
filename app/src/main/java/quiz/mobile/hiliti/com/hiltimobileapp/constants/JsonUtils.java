package quiz.mobile.hiliti.com.hiltimobileapp.constants;

import com.android.volley.RequestQueue;

import org.json.JSONArray;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.json.Parser;
import quiz.mobile.hiliti.com.hiltimobileapp.json.Requestor;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;

/**
 * Created by vaishu on 04-11-2015.
 */
public class JsonUtils {
    public static ArrayList<TrainingPojo> getTrainingMaterials(RequestQueue requestQueue){
        JSONArray jsonArray = Requestor.requestTrainingJSON(requestQueue,UrlEndpoints.URL_TRAINING,Tags.TRAINING_TAG);
        Log.m("jsonArray size"+jsonArray.length());
        ArrayList<TrainingPojo> trainingPojos = Parser.getTrainingMaterials(jsonArray);
        return trainingPojos;
    }
}
