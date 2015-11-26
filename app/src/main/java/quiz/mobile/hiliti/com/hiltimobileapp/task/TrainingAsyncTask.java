package quiz.mobile.hiliti.com.hiltimobileapp.task;

import android.graphics.Movie;
import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.ResultActivity;
import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.TrainingMaterialsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.JsonUtils;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;

/**
 * Created by vaishu on 04-11-2015.
 */
public class TrainingAsyncTask extends AsyncTask<Void,Void,ArrayList<TrainingPojo>> {
    VolleySingleton volleySingleton;
    RequestQueue mRequestQueue;
    TrainingMaterialsCallBackListener trainingMaterialsCallBack;
    public TrainingAsyncTask(TrainingMaterialsCallBackListener trainingMaterialsCallBack){
        this.trainingMaterialsCallBack = trainingMaterialsCallBack;
        volleySingleton = VolleySingleton.getvSingletonInstance();
        mRequestQueue = volleySingleton.getmRequestQueue();
    }

    @Override
    protected ArrayList<TrainingPojo> doInBackground(Void... params) {
        ArrayList<TrainingPojo> trainingPojos = JsonUtils.getTrainingMaterials(mRequestQueue);
        return trainingPojos;
    }
    @Override
    protected void onPostExecute(ArrayList<TrainingPojo> trainingPojos) {
        if (trainingMaterialsCallBack != null) {
            trainingMaterialsCallBack.getTrainingMaterialsList(trainingPojos);
        }
    }

}
