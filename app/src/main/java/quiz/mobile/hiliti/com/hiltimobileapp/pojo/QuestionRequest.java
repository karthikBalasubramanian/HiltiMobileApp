package quiz.mobile.hiliti.com.hiltimobileapp.pojo;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by Poorna on 23/11/2015.
 */
public class QuestionRequest {

    String selectedNumberOfQues;
    String selectedTopics;
    String selectedDifficultyLevel;

    public QuestionRequest(String selectedNumberOfQues, ArrayList<CharSequence> selectedTopics, ArrayList<CharSequence> selectedDifficultyLevel) {

        this.selectedNumberOfQues = selectedNumberOfQues;
        this.selectedTopics = tokenize(charSequenceToString(selectedTopics));
        this.selectedDifficultyLevel = tokenize(charSequenceToString(selectedDifficultyLevel));
    }

    public String getSelectedNumberOfQues() {
        return selectedNumberOfQues;
    }

    public void setSelectedNumberOfQues(String selectedNumberOfQues) {
        this.selectedNumberOfQues = selectedNumberOfQues;
    }

    public String getSelectedTopics() {
        return selectedTopics;
    }

    public void setSelectedTopics(String selectedTopics) {
        this.selectedTopics = selectedTopics;
    }

    public String getSelectedDifficultyLevel() {
        return selectedDifficultyLevel;
    }

    public void setSelectedDifficultyLevel(String selectedDifficultyLevel) {
        this.selectedDifficultyLevel = selectedDifficultyLevel;
    }

    String tokenize(ArrayList<String> list){
        String tokenString;
        tokenString = TextUtils.join(",",list.toArray());
        return tokenString;
    }

    ArrayList<String> charSequenceToString(ArrayList<CharSequence> list){

        ArrayList<String> result = new ArrayList<String>();

        for(CharSequence i : list){
            result.add(i.toString());
        }

        return result;

    }




}
