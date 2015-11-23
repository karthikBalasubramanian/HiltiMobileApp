package quiz.mobile.hiliti.com.hiltimobileapp.json;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import quiz.mobile.hiliti.com.hiltimobileapp.pojo.QuestionRequest;

import static com.android.volley.Request.Method.POST;

/**
 * Created by Poorna on 22/11/2015.
 */
public class QuestionRequestor extends JsonArrayRequest {

    QuestionRequest questionRequest;
    public  QuestionRequestor(String url, Response.Listener<JSONArray> listener, Response.ErrorListener errorListener, QuestionRequest questionRequest){

        this.questionRequest = questionRequest;
        super(POST, url, listener, errorListener);
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", "value");
        return params;
    }



}
