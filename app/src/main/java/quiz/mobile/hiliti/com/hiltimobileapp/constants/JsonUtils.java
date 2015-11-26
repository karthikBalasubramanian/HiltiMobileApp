package quiz.mobile.hiliti.com.hiltimobileapp.constants;

import android.content.Context;

import com.android.volley.RequestQueue;

import org.json.JSONArray;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.json.Parser;
import quiz.mobile.hiliti.com.hiltimobileapp.json.Requestor;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Topic;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.UserProfile;

/**
 * Created by vaishu on 04-11-2015.
 */
public class JsonUtils {
    public static ArrayList<TrainingPojo> getTrainingMaterials(RequestQueue requestQueue){
        JSONArray jsonArray = Requestor.requestTrainingJSON(requestQueue,UrlEndpoints.URL_TRAINING,Tags.TRAINING_TAG);
        ArrayList<TrainingPojo> trainingPojos = Parser.getTrainingMaterials(jsonArray);
        return trainingPojos;
    }

    public static ArrayList<Question> getQuestions(RequestQueue requestQueue){
        JSONArray jsonArray = Requestor.requestQuestionJSON(requestQueue, UrlEndpoints.URL_QUESTIONS, Tags.QUESTION_TAG);
        ArrayList<Question> questionPojos = Parser.getQuestions(jsonArray);
        return questionPojos;
    }

    public static ArrayList<Topic> getTopics(RequestQueue requestQueue){
        JSONArray jsonArray = Requestor.requestTrainingJSON(requestQueue,UrlEndpoints.URL_TOPICS,Tags.TOPIC_TAG);
        ArrayList<Topic> topicPojos = Parser.getTopic(jsonArray);
        return topicPojos;
    }

    public static ArrayList<UserProfile> getLeaders(RequestQueue requestQueue){
        JSONArray jsonArray = Requestor.requestLeaderJSON(requestQueue, UrlEndpoints.URL_LEADERS, Tags.LEADER_TAG);
        ArrayList<UserProfile> leaderPojos = Parser.getLeader(jsonArray);
        return leaderPojos;
    }



}
