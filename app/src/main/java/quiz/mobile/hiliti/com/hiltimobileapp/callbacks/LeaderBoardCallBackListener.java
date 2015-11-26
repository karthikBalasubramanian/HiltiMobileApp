package quiz.mobile.hiliti.com.hiltimobileapp.callbacks;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.pojo.UserProfile;

/**
 * Created by Poorna on 26/11/2015.
 */
public interface LeaderBoardCallBackListener {

    public void getLeaderList(ArrayList<UserProfile> userProfilePojos);

}
