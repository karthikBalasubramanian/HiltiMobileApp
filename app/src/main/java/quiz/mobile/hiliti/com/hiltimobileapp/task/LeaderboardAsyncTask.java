package quiz.mobile.hiliti.com.hiltimobileapp.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.LeaderBoardCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.JsonUtils;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.network.VolleySingleton;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.UserProfile;

/**
 * Created by Poorna on 26/11/2015.
 */
public class LeaderboardAsyncTask  extends AsyncTask<Void,Void,ArrayList<UserProfile>> {



    VolleySingleton volleySingleton;
    RequestQueue mRequestQueue;
    LeaderBoardCallBackListener leaderCallBack;

    public LeaderboardAsyncTask(LeaderBoardCallBackListener leaderCallBack){
        this.leaderCallBack = leaderCallBack;
        volleySingleton = VolleySingleton.getvSingletonInstance();
        mRequestQueue = volleySingleton.getmRequestQueue();
    }


    @Override
    protected ArrayList<UserProfile> doInBackground(Void... params) {
        Log.m("Leaderboard Task triggered");
        ArrayList<UserProfile> leaderPojos = JsonUtils.getLeaders(mRequestQueue);
        return leaderPojos;
    }
    @Override
    protected void onPostExecute(ArrayList<UserProfile> leaderPojos) {
        if (leaderCallBack != null) {
            Log.m("Topic onPost executed.");
            leaderCallBack.getLeaderList(leaderPojos);
        }
    }


}
