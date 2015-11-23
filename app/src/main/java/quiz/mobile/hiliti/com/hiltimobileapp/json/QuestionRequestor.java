package quiz.mobile.hiliti.com.hiltimobileapp.json;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import quiz.mobile.hiliti.com.hiltimobileapp.HiltiApplication;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.Tags;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.QuestionRequest;

import static com.android.volley.Request.Method.POST;

/**
 * Created by Poorna on 22/11/2015.
 */
public class QuestionRequestor extends JsonArrayRequest {
    private Context context;
    private SharedPreferences sharedPreferences;

    public  QuestionRequestor(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener){
        //sharedPreferences = getAppli.getSharedPreferences(Tags.PREF_NAME,MODE_PRIVATE);
        super(POST, url, listener, errorListener);
    }


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {

        Log.m("overriding this method");

        SharedPreferences preferences = HiltiApplication.getAppContext().getSharedPreferences(Tags.PREF_NAME, Context.MODE_PRIVATE);

        String noOfQuestions = preferences.getString(Tags.NO_OF_QUESTION, "5");
        String topicList = preferences.getString(Tags.TOPIC_LIST, "drill");
        String difficulty = preferences.getString(Tags.DIFFICULTY_LEVELS, "3");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Tags.TOPIC_LIST, topicList);
        params.put(Tags.DIFFICULTY_LEVELS, difficulty);
//                params.put("name", "value");
        return params;
    }





}
