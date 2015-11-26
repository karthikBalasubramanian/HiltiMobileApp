package quiz.mobile.hiliti.com.hiltimobileapp.json;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import quiz.mobile.hiliti.com.hiltimobileapp.HiltiApplication;
import quiz.mobile.hiliti.com.hiltimobileapp.QuestionDisplayActivity;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.Tags;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.UrlEndpoints;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.AnsweredCorrect;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.QuestionRequest;

/**
 * Created by vaishu on 03-11-2015.
 */
public class Requestor {
    public static JSONArray requestTrainingJSON(RequestQueue requestQueue, String url, String trainingTag) {
        JSONArray jsonArray = new JSONArray();
        RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();

        JsonArrayRequest request = new JsonArrayRequest(url, requestFuture, requestFuture);
        request.setTag(trainingTag);
        requestQueue.add(request);
        try {
            jsonArray = requestFuture.get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.m(e + "");
        } catch (ExecutionException e) {
            Log.m(e + "");
        } catch (TimeoutException e) {
            Log.m(e + "");
        }
        return jsonArray;
    }

    public static JSONArray requestQuestionJSON(RequestQueue requestQueue, String url, String questionTag)  {
        SharedPreferences preferences = HiltiApplication.getAppContext().getSharedPreferences(Tags.PREF_NAME, Context.MODE_PRIVATE);
        String noOfQuestions = preferences.getString(Tags.NO_OF_QUESTION, "5");
        String topicList = preferences.getString(Tags.TOPIC_LIST, "drill");
        String difficulty = preferences.getString(Tags.DIFFICULTY_LEVELS, "3");
        JSONArray jsonArray = new JSONArray();
        try {
            url = url+ UrlEndpoints.URL_CHAR_QUESTION+UrlEndpoints.QUESTION_PARAMS_TOPIC+URLEncoder.encode(topicList, UrlEndpoints.URL_ENCODER)+UrlEndpoints.URL_CHAR_AMEPERSAND+ UrlEndpoints.QUESTION_PARAMS_DIFFICULTY+URLEncoder.encode(difficulty,UrlEndpoints.URL_ENCODER)+UrlEndpoints.URL_CHAR_AMEPERSAND+ UrlEndpoints.QUESTION_PARAM_QNO+URLEncoder.encode(noOfQuestions,UrlEndpoints.URL_ENCODER);
        } catch (UnsupportedEncodingException e) {
            Log.m("encoding error");
            e.printStackTrace();
        }
        Log.m("URL is "+url);
        RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();
        //int method, String url, Listener<JSONArray> listener, ErrorListener errorListener
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,requestFuture,requestFuture);
        jsonArrayRequest.setTag(questionTag);
        requestQueue.add(jsonArrayRequest);
        try {
            jsonArray = requestFuture.get(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.m(e + "");
        } catch (ExecutionException e) {
            Log.m(e + "");
        } catch (TimeoutException e) {
            Log.m(e + "");
        }
        return jsonArray;
    }

    public static void answeredCorrectStringRequest(final ArrayList<AnsweredCorrect>correctAnswers,String answeredHistoryTag) {
        Log.m("answeredCorrectcalled");
            String url = UrlEndpoints.API_SERVER+UrlEndpoints.URL_UPDATE_ALL_ANSWERS;

            //RequestFuture<String> requestFuture = RequestFuture.newFuture();
            StringRequest strReq = new StringRequest(Request.Method.POST,
                    url, new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Log.m("inside response");
                    if (response != null) {
                        JSONArray jsonAraay = new JSONArray(correctAnswers);
                        Log.m("json array is "+ jsonAraay.toString());
                        Log.m("response for success " + response.toString());
                    }
                }
            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Gson gson = new GsonBuilder().create();
                    JsonArray jsonArray = gson.toJsonTree(correctAnswers).getAsJsonArray();
                    Log.m(jsonArray.toString());
                    Log.m("answer_correct update error" + error.getMessage().toString());
                }
            }){

                @Override
                protected Map<String, String> getParams() {
                    // Posting parameters to login url
                    Map<String, String> params = new HashMap<String, String>();
                    JSONArray jsonAraay = new JSONArray(correctAnswers);
                    Log.m("json array is " + jsonAraay.toString());
                    params.put("allAnswers", jsonAraay.toString());
                    return params;
                }

            };
            strReq.setTag(answeredHistoryTag);
            VolleySingleton.getvSingletonInstance().getmRequestQueue().add(strReq);

        }

    public static JSONArray requestLeaderJSON(RequestQueue requestQueue, String url, String trainingTag) {
        JSONArray jsonArray = new JSONArray();
        RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();

        JsonArrayRequest request = new JsonArrayRequest(url, requestFuture, requestFuture);
        request.setTag(trainingTag);
        requestQueue.add(request);
        try {
            jsonArray = requestFuture.get(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            Log.m(e + "");
        } catch (ExecutionException e) {
            Log.m(e + "");
        } catch (TimeoutException e) {
            Log.m(e + "");
        }
        return jsonArray;
    }

}
