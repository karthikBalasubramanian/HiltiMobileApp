package quiz.mobile.hiliti.com.hiltimobileapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.TopicsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Topic;
import quiz.mobile.hiliti.com.hiltimobileapp.task.TopicAsyncTask;

public class TakeQuizOptions extends AppCompatActivity implements TopicsCallBackListener {

    Spinner spinnerNumberOfQues;
    ArrayAdapter<CharSequence> adapterSpinnerNumberOfQues;
    String selectedNumberOfQues;
    Button btnTakeQuizOptions;

    protected Button selectTopicsButton;
    protected CharSequence[] topics;
    protected ArrayList<CharSequence> selectedTopics = new ArrayList<CharSequence>();

    protected Button selectDifficultyLevelButton;
    protected CharSequence[] difficultyLevel = { "1", "2", "3", "4", "5" };
    protected ArrayList<CharSequence> selectedDifficultyLevel = new ArrayList<CharSequence>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_quiz_options);

        if (topics == null) new TopicAsyncTask(this).execute();

        adapterSpinnerNumberOfQues = ArrayAdapter.createFromResource(this, R.array.spinnerNumberOfQues,android.R.layout.simple_spinner_item);
        adapterSpinnerNumberOfQues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNumberOfQues = (Spinner) findViewById(R.id.spinner_numberOfQues);

        btnTakeQuizOptions = (Button) findViewById(R.id.button_takeQuizOptions);

        selectTopicsButton = (Button) findViewById(R.id.select_topics);
        initToolbar();
        selectTopicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()) {
                    case R.id.select_topics:
                        showSelectTopicsDialog();
                        break;
                    default:
                        break;
                }
            }
        });

        selectDifficultyLevelButton = (Button) findViewById(R.id.select_difficultyLevel); //

        selectDifficultyLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //
                switch(v.getId()) {
                    case R.id.select_difficultyLevel:
                        showSelectDifficultyLevelDialog();
                        break;
                    default:
                        break;
                }
            }
        });
        spinnerNumberOfQues.setAdapter(adapterSpinnerNumberOfQues);
        spinnerNumberOfQues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedNumberOfQues = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnTakeQuizOptions.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (selectedTopics.isEmpty())
                            Toast.makeText(TakeQuizOptions.this,"Please select Topics" , Toast.LENGTH_LONG).show();
                        else if (selectedDifficultyLevel.isEmpty())
                            Toast.makeText(TakeQuizOptions.this,"Please select Difficulty Level" , Toast.LENGTH_LONG).show();
                        

                        else{

                            Intent intent = new Intent(TakeQuizOptions.this, QuestionDisplayActivity.class);
//
                            intent.putExtra("Selectedtopics",selectedTopics);
                            intent.putExtra("SelectedDifficulty",selectedDifficultyLevel);
                            intent.putExtra("NumberOfQuestions",selectedNumberOfQues);

                            startActivity(intent);


//                            Toast.makeText(TakeQuizOptions.this, editNumberOfQues + "  " + selectedTopics.toString() + "  " + editDifficultyLevel, Toast.LENGTH_LONG).show();


                        }
                    }
                }
        );
    }

    private void showSelectTopicsDialog()
    {
        boolean[] checkedTopics = new boolean[topics.length];
        int count = topics.length;
        for(int i = 0; i < count; i++)
            checkedTopics[i] = selectedTopics.contains(topics[i]);
        DialogInterface.OnMultiChoiceClickListener topicsDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                    selectedTopics.add(topics[which]);
                else
                    selectedTopics.remove(topics[which]);
                onChangeSelectedTopics();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Topics");
        builder.setMultiChoiceItems(topics, checkedTopics, topicsDialogListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void onChangeSelectedTopics() {
        StringBuilder stringBuilder = new StringBuilder();

        for(CharSequence colour : selectedTopics)
            stringBuilder.append(" " + colour + ",");
        if (stringBuilder.length()>0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(0);
        }
        selectTopicsButton.setText(stringBuilder.toString());
    }

    @Override
    public void getTopicList(ArrayList<Topic> topicPojos) {
        this.topics = new CharSequence[topicPojos.size()];

        for(int i=0; i<topicPojos.size(); i++) {
            this.topics[i]= topicPojos.get(i).getTopicName();                        }
    }

    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.takeQuizToolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showSelectDifficultyLevelDialog()
    {
        boolean[] checkedDifficultyLevel = new boolean[difficultyLevel.length];
        int count = difficultyLevel.length;
        for(int i = 0; i < count; i++)
            checkedDifficultyLevel[i] = selectedDifficultyLevel.contains(difficultyLevel[i]);
        DialogInterface.OnMultiChoiceClickListener difficultyLevelDialogListener = new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if(isChecked)
                    selectedDifficultyLevel.add(difficultyLevel[which]);
                else
                    selectedDifficultyLevel.remove(difficultyLevel[which]);
                onChangeSelectedDifficultyLevel();
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Difficulty Level");
        builder.setMultiChoiceItems(difficultyLevel, checkedDifficultyLevel, difficultyLevelDialogListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void onChangeSelectedDifficultyLevel() {
        StringBuilder stringBuilder = new StringBuilder();

        for(CharSequence colour : selectedDifficultyLevel)
            stringBuilder.append(" " + colour + ",");
        if (stringBuilder.length()>0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.deleteCharAt(0);
        }
        selectDifficultyLevelButton.setText(stringBuilder.toString());
    }
}
