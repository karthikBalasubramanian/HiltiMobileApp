package quiz.mobile.hiliti.com.hiltimobileapp.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.TopicsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.JsonUtils;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Topic;

/**
 * Created by Poorna on 18/11/2015.
 */
public class TopicAsyncTask extends AsyncTask<Void,Void,ArrayList<Topic>> {



    VolleySingleton volleySingleton;
    RequestQueue mRequestQueue;
    TopicsCallBackListener topicCallBack;

    public TopicAsyncTask(TopicsCallBackListener topicCallBack){
        this.topicCallBack = topicCallBack;
        volleySingleton = VolleySingleton.getvSingletonInstance();
        mRequestQueue = volleySingleton.getmRequestQueue();
    }


    @Override
    protected ArrayList<Topic> doInBackground(Void... params) {
        Log.m("Topic Task triggered");
        ArrayList<Topic> topicsPojos = JsonUtils.getTopics(mRequestQueue);
        return topicsPojos;
    }
    @Override
    protected void onPostExecute(ArrayList<Topic> topicPojos) {
        if (topicCallBack != null) {
            Log.m("Topic onPost executed.");
            topicCallBack.getTopicList(topicPojos);
        }
    }


}
