package quiz.mobile.hiliti.com.hiltimobileapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.QuestionsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
import quiz.mobile.hiliti.com.hiltimobileapp.task.QuestionAsyncTask;

public class QuestionDisplayActivity extends AppCompatActivity implements QuestionsCallBackListener  {

    ArrayList<Question> questionList = new ArrayList<Question>();;
    TextView questionTextDisplay;

    RadioGroup group;
    Button buttonA;
    Button buttonB;
    Button buttonC;
    Button buttonD;

    FloatingActionButton buttonNext;

    Button buttonSubmit;

    int currentQuestionIndex = 0;

    ArrayList<String> userAnswerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        questionList = new ArrayList<Question>();



        buttonSubmit =(Button)findViewById(R.id.submit_button);
        buttonSubmit.setVisibility(View.INVISIBLE);

//        questionList = (ArrayList<Question>) getIntent().getSerializableExtra("QuestionList");

        //TODO: Remove the below call. It is for testing only

//        populateQuestions();

        if (questionList.isEmpty()) new QuestionAsyncTask(this).execute();





        questionTextDisplay = (TextView) findViewById(R.id.questionText);

        buttonA = (Button) findViewById(R.id.buttonOptionA);
        buttonB = (Button) findViewById(R.id.buttonOptionB);
        buttonC = (Button) findViewById(R.id.buttonOptionC);
        buttonD = (Button) findViewById(R.id.buttonOptionD);

        buttonNext = (FloatingActionButton) findViewById(R.id.fab_next_question);

        addListenerOnButtons();

        //TODO: Change ON color of toggle button to RED






    }
//TODO: Remove this method
    private void populateQuestions() {
        questionList = new ArrayList<Question>(5);
        questionList.add(0, new Question("MCQ","1+1=?",2,"1","2","3","4","a","active"));
        questionList.add(1, new Question("MCQ","2+4=?",4, "1", "2", "6", "4", "c", "active"));
        questionList.add(2, new Question("MCQ","What is the price of Hilti Hammer?",5,"$100","$200","$300","$400","a","active"));
        questionList.add(3, new Question("MCQ","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa=?",2,"1","2","3","4","a","active"));
        questionList.add(4, new Question("MCQ","4-1=?",2,"1","2","3","4","c","active"));


    }

    private void addListenerOnButtons() {
         final RadioGroup.OnCheckedChangeListener ToggleListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int checkedID) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == checkedID);
                    if(view.getId() == checkedID){

                        if(checkedID == R.id.buttonOptionA){
                            userAnswerList.add(currentQuestionIndex,"a");
                        }
                        else if(checkedID == R.id.buttonOptionB){
                            userAnswerList.add(currentQuestionIndex,"b");
                        }
                        else if(checkedID == R.id.buttonOptionC){
                            userAnswerList.add(currentQuestionIndex,"c");
                        } else if(checkedID == R.id.buttonOptionD){
                            userAnswerList.add(currentQuestionIndex, "d");
                        }

                    }

                }

                Log.d("QuestionDisplay", "Selected"+userAnswerList.get(currentQuestionIndex));

            }
        };

        group = (RadioGroup) findViewById(R.id.toggleGroup);
        group.setOnCheckedChangeListener(ToggleListener);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_next_question);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("Next button clicked.");

                for (int j = 0; j < group.getChildCount(); j++) {
                    final ToggleButton button = (ToggleButton) group.getChildAt(j);
                    button.setChecked(false);
                }


                if (currentQuestionIndex == questionList.size() - 2) {

                    currentQuestionIndex++;
                    buttonSubmit.setVisibility(View.VISIBLE);
                    buttonNext.setVisibility(View.INVISIBLE);

                    displayQuestion();
                } else {
                    currentQuestionIndex++;
                    displayQuestion();
                }

            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitQuiz();

            }
        });

    }


    public void onToggle(View view) {
        ((RadioGroup)view.getParent()).check(view.getId());
        // option specific code
        displayQuestion();
    }

    public void displayQuestion(){
        quiz.mobile.hiliti.com.hiltimobileapp.logging.Log.m("CurrentIndex: " + currentQuestionIndex);
        Question q = questionList.get(currentQuestionIndex);
        questionTextDisplay.setText(q.getText());
        buttonA.setText(q.getOptionA());
        buttonB.setText(q.getOptionB());
        buttonC.setText(q.getOptionC());
        buttonD.setText(q.getOptionD());
    }

    public void submitQuiz(){

        //TODO: Check if all questions have been answered.

        Toast toast = Toast.makeText(this,"Answers submitted.",Toast.LENGTH_SHORT);
        toast.show();

        //TODO: Add code to submit the data to results screen.

    }


    @Override
    public void getQuestionsList(ArrayList<Question> questionsPojos) {
        this.questionList = questionsPojos;
        userAnswerList = new ArrayList<String>(questionList.size());


        displayQuestion();
    }
}
