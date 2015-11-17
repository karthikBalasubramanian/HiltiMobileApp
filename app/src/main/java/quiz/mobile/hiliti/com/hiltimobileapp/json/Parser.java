package quiz.mobile.hiliti.com.hiltimobileapp.json;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.constants.Keys;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
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

    public static ArrayList<Question> getQuestions(JSONArray arrayResponse){
        ArrayList<Question> questionsList = new ArrayList<Question>();
        Question questionPojo = null;
        Log.m("Responses: "+arrayResponse.toString());
        Log.m("\nLength: "+arrayResponse.length());
        try{ for(int i=0; i<arrayResponse.length();i++){
            JSONObject newJsonObject = arrayResponse.getJSONObject(i);
            questionPojo = new Question();

            questionPojo.setQid(newJsonObject.getInt(Keys.QuestionInterface.Q_ID));
            questionPojo.setType(newJsonObject.getString(Keys.QuestionInterface.TYPE));
            questionPojo.setText(newJsonObject.getString(Keys.QuestionInterface.TEXT));
            questionPojo.setDifficulty(newJsonObject.getInt(Keys.QuestionInterface.DIFFICULTY));
            questionPojo.setOptionA(newJsonObject.getString(Keys.QuestionInterface.OPTION_A));
            questionPojo.setOptionB(newJsonObject.getString(Keys.QuestionInterface.OPTION_B));
            questionPojo.setOptionC(newJsonObject.getString(Keys.QuestionInterface.OPTION_C));
            questionPojo.setOptionD(newJsonObject.getString(Keys.QuestionInterface.OPTION_D));
            questionPojo.setCorrectAns(newJsonObject.getString(Keys.QuestionInterface.CORRECT_ANS));
            questionPojo.setStatus(newJsonObject.getString(Keys.QuestionInterface.STATUS));

            questionsList.add(questionPojo);
        }}catch (JSONException e) {

            Log.m("inside json exception");
        }
        return questionsList;
    }
}
