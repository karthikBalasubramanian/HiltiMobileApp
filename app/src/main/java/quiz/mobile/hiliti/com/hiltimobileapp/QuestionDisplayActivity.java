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
import android.widget.ToggleButton;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;

public class QuestionDisplayActivity extends AppCompatActivity {

    ArrayList<Question> questionList;
    TextView questionTextDisplay;
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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_next_question);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        questionList = (ArrayList<Question>) getIntent().getSerializableExtra("QuestionList");

        userAnswerList = new ArrayList<String>(questionList.size());
        questionTextDisplay = (TextView) findViewById(R.id.questionText);

        buttonA = (Button) findViewById(R.id.buttonOptionA);
        buttonB = (Button) findViewById(R.id.buttonOptionB);
        buttonC = (Button) findViewById(R.id.buttonOptionC);
        buttonD = (Button) findViewById(R.id.buttonOptionD);

        buttonNext = (FloatingActionButton) findViewById(R.id.fab_next_question);

        addListenerOnButtons();

        //TODO: Change ON color of toggle button



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
                        }
                        else if(checkedID == R.id.buttonOptionD){
                            userAnswerList.add(currentQuestionIndex,"d");
                        }

                    }

                }

                Log.d("QuestionDisplay", "Selected"+userAnswerList.get(currentQuestionIndex));

            }
        };

        ((RadioGroup) findViewById(R.id.toggleGroup)).setOnCheckedChangeListener(ToggleListener);
    }


    public void onToggle(View view) {
        ((RadioGroup)view.getParent()).check(view.getId());
        // option specific code
    }

}
