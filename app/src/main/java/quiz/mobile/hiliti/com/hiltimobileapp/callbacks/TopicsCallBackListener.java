package quiz.mobile.hiliti.com.hiltimobileapp.callbacks;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Topic;

/**
 * Created by Poorna on 18/11/2015.
 */
public interface TopicsCallBackListener {

    public void getTopicList(ArrayList<Topic> topicPojos);
}
