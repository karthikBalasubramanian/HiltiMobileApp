package quiz.mobile.hiliti.com.hiltimobileapp.json;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.constants.Keys;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;

/**
 * Created by vaishu on 03-11-2015.
 */
public class Parser {
    public static ArrayList<TrainingPojo> getTrainingMaterials(JSONArray arrayResponse){
        ArrayList<TrainingPojo> trainingMaterials = new ArrayList<TrainingPojo>();
        TrainingPojo trainingPojo = null;
       try{ for(int i=0; i<arrayResponse.length();i++){
            JSONObject newJsonObject = arrayResponse.getJSONObject(i);
            trainingPojo = new TrainingPojo();
            trainingPojo.setId(newJsonObject.getInt(Keys.TrainingInterface.TOOL_ID));
            trainingPojo.setTitle(newJsonObject.getString(Keys.TrainingInterface.TOOL_TITLE));
            trainingPojo.setImageRes(Endpoints.getImageFromServer(newJsonObject.getString(Keys.TrainingInterface.TOOL_IMAGE_RES)));
            trainingPojo.setApplications(newJsonObject.getString(Keys.TrainingInterface.TOOL_APPLICATION));
            trainingPojo.setFeatures(newJsonObject.getString(Keys.TrainingInterface.TOOL_FEATURE));
            trainingMaterials.add(trainingPojo);
        }}catch (JSONException e) {

           Log.m(e+"");
        }
        return trainingMaterials;
    }
}
