package quiz.mobile.hiliti.com.hiltimobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.QuestionsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Topic;
import quiz.mobile.hiliti.com.hiltimobileapp.task.QuestionAsyncTask;
import quiz.mobile.hiliti.com.hiltimobileapp.utility.SessionManager;

public class QuestionDisplayActivity extends AppCompatActivity implements QuestionsCallBackListener  {

    String selectedNumberOfQues;
    ArrayList<CharSequence> selectedTopics;
    ArrayList<CharSequence> selectedDifficultyLevel;

    private final Object lock = new Object();

    ArrayList<Question> questionList = new ArrayList<Question>();
    TextView questionTextDisplay;

    RadioGroup group;
    RadioButton buttonA;
    RadioButton buttonB;
    RadioButton buttonC;
    RadioButton buttonD;

    FloatingActionButton buttonNext;

    Button buttonSubmit;

    int currentQuestionIndex = 0;

//    ArrayList<String> userAnswerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        questionList = new ArrayList<Question>();

        this.selectedNumberOfQues = (String) getIntent().getSerializableExtra("NumberOfQuestions");
        this.selectedTopics = (ArrayList<CharSequence>) getIntent().getSerializableExtra("Selectedtopics");
        this.selectedDifficultyLevel = (ArrayList<CharSequence>) getIntent().getSerializableExtra("SelectedDifficulty");

        SessionManager sessionManager = new SessionManager(getApplicationContext());
                sessionManager.setQuestionRequest(this.selectedNumberOfQues, tokenize(charSequenceToString(this.selectedTopics)), tokenize(charSequenceToString(this.selectedDifficultyLevel)));


        buttonSubmit =(Button)findViewById(R.id.submit_button);
        buttonSubmit.setVisibility(View.INVISIBLE);



        if (questionList.isEmpty()) new QuestionAsyncTask(this).execute();

        questionTextDisplay = (TextView) findViewById(R.id.questionText);

        buttonA = (RadioButton) findViewById(R.id.buttonOptionA);
        buttonB = (RadioButton) findViewById(R.id.buttonOptionB);
        buttonC = (RadioButton) findViewById(R.id.buttonOptionC);
        buttonD = (RadioButton) findViewById(R.id.buttonOptionD);

        buttonNext = (FloatingActionButton) findViewById(R.id.fab_next_question);

        addListenerOnButtons();
    }


    private void addListenerOnButtons() {
         final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int checkedID) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final RadioButton view = (RadioButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == checkedID);
                    if(view.getId() == checkedID){

                        if(checkedID == R.id.buttonOptionA){
                            questionList.get(getCurrentQuestionIndex()).setAnswerByUser("a");
                        }
                        else if(checkedID == R.id.buttonOptionB){
                            questionList.get(getCurrentQuestionIndex()).setAnswerByUser("b");
                        }
                        else if(checkedID == R.id.buttonOptionC){
                            questionList.get(getCurrentQuestionIndex()).setAnswerByUser("c");
                        } else if(checkedID == R.id.buttonOptionD){
                            questionList.get(getCurrentQuestionIndex()).setAnswerByUser("d");
                        }

                    }

                }

                Log.d("QuestionDisplay", "Selected"+questionList.get(getCurrentQuestionIndex()).getAnswerByUser());

            }
        };

        group = (RadioGroup) findViewById(R.id.toggleGroup);
        group.setOnCheckedChangeListener(ToggleListener);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_next_question);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Next button clicked.");

//                if(userAnswerList.get(currentQuestionIndex) == null) {
//                    Toast toast;
//                    toast = Toast.makeText(QuestionDisplayActivity.this, "Please select an answer.", Toast.LENGTH_SHORT);
//                    toast.show();
//                    return;
//                }

//                Reset the toggle buttons
//                RadioButton button;
//                for (int j = 0; j < group.getChildCount(); j++) {
//                    button = (RadioButton) group.getChildAt(j);
//                    button.setChecked(false);
//                }


                quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Button group reset.");


                if (getCurrentQuestionIndex() == questionList.size() - 2) {

                    quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("one but last question.");

                    setCurrentQuestionIndex(getCurrentQuestionIndex() + 1);
                    quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Current question incremented.");
                    buttonSubmit.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.INVISIBLE);

                    displayQuestion();
                } else {
                    quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("not last question.");
                    setCurrentQuestionIndex(getCurrentQuestionIndex() + 1);
                    quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Current question incremented.");
                    displayQuestion();
                }

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Submit button clicked");

                submitQuiz();

            }
        });

    }


//    public void onToggle(View view) {
//        ((RadioGroup)view.getParent()).check(view.getId());
//        // option specific code
//        displayQuestion();
//    }

    public  void displayQuestion(){
        quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Display question invoked.");
        quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("CurrentIndex: " + getCurrentQuestionIndex());
        RadioButton button;
//        for (int j = 0; j < group.getChildCount(); j++) {
//            button = (RadioButton) group.getChildAt(j);
//            button.setChecked(false);
//        }

        ((RadioButton)findViewById(R.id.buttonOptionA)).setChecked(false);
        ((RadioButton)findViewById(R.id.buttonOptionB)).setChecked(false);
        ((RadioButton)findViewById(R.id.buttonOptionC)).setChecked(false);
        ((RadioButton)findViewById(R.id.buttonOptionD)).setChecked(false);
        Question q = questionList.get(getCurrentQuestionIndex());
        questionTextDisplay.setText(q.getText());
        buttonA.setText(q.getOptionA());
        buttonB.setText(q.getOptionB());
        buttonC.setText(q.getOptionC());
        buttonD.setText(q.getOptionD());
    }

    public void submitQuiz(){

        quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Entered submit quiz." +"");


        if(questionList.get(getCurrentQuestionIndex()).getAnswerByUser() == null) {

            Toast toast = Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        else{


            Intent intent = new Intent(QuestionDisplayActivity.this,ResultActivity.class);
            intent.putExtra("QuestionList",this.questionList);
            startActivity(intent);

            Toast toast = Toast.makeText(this, "Answers submitted.", Toast.LENGTH_SHORT);
            toast.show();
        }



    }


    @Override
    public void getQuestionsList(ArrayList<Question> questionsPojos) {
        this.questionList = questionsPojos;
        quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("quiz size"+ questionList.size());
        currentQuestionIndex = 0;
        displayQuestion();
    }

    String tokenize(ArrayList<String> list){
        String tokenString;
        tokenString = TextUtils.join(",", list.toArray());
        return tokenString;
    }

    ArrayList<String> charSequenceToString(ArrayList<CharSequence> list){

        ArrayList<String> result = new ArrayList<String>();

        for(CharSequence i : list){
            result.add(i.toString());
        }

        return result;

    }

    public int getCurrentQuestionIndex() {

        synchronized (lock){
            return currentQuestionIndex;
        }

    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        synchronized (lock) {
            this.currentQuestionIndex = currentQuestionIndex;
        }
    }
}
