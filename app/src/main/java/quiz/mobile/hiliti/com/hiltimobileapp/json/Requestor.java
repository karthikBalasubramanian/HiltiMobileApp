package quiz.mobile.hiliti.com.hiltimobileapp.json;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.RequestFuture;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import quiz.mobile.hiliti.com.hiltimobileapp.HiltiApplication;
import quiz.mobile.hiliti.com.hiltimobileapp.QuestionDisplayActivity;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.Tags;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
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

    public static JSONArray requestQuestionJSON(RequestQueue requestQueue, String url, String trainingTag) {
        JSONArray jsonArray = new JSONArray();
        RequestFuture<JSONArray> requestFuture = RequestFuture.newFuture();
        //int method, String url, Listener<JSONArray> listener, ErrorListener errorListener
        QuestionRequestor jsonArrayRequest = new QuestionRequestor(url,requestFuture,requestFuture);
        //QuestionRequestor request = new QuestionRequestor(Request.Method.POST,url,null, requestFuture, requestFuture);
        jsonArrayRequest.setTag(trainingTag);
        requestQueue.add(jsonArrayRequest);
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
