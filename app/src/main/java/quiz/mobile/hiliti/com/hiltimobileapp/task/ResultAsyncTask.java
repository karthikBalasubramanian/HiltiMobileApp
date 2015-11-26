package quiz.mobile.hiliti.com.hiltimobileapp.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.net.URL;
import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.AnsweredCorrectCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.Tags;
import quiz.mobile.hiliti.com.hiltimobileapp.json.Requestor;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;

/**
 * Created by vaishu on 26-11-2015.
 */
public class ResultAsyncTask extends AsyncTask<URL,Void,ArrayList<String>> {

    AnsweredCorrectCallBackListener answeredCorrectCallBackListener;
    ArrayList<String> responses = new ArrayList<String>();

    public ResultAsyncTask(AnsweredCorrectCallBackListener answeredCorrectCallBackListener) {
        this.answeredCorrectCallBackListener = answeredCorrectCallBackListener;

}

    @Override
    protected ArrayList<String> doInBackground(URL... params) {
        Log.m("list of urls size"+params.length);
        for (int i=0; i<params.length; i++ ){
            responses.add(Requestor.answeredCorrectStringRequest(VolleySingleton.getvSingletonInstance().getmRequestQueue(),params[i], Tags.RESULT_TAG));
        }
        return responses;
    }
    @Override
    protected void onPostExecute(ArrayList<String> responses) {
        if (answeredCorrectCallBackListener != null) {
            answeredCorrectCallBackListener.getAnsweredCorrectUpdateResponses(responses);
        }
    }

}
