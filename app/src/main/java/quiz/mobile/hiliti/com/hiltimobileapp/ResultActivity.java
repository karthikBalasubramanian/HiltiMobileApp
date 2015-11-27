package quiz.mobile.hiliti.com.hiltimobileapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import quiz.mobile.hiliti.com.hiltimobileapp.adapter.RecyclerViewAdapterResult;
import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.AnsweredCorrectCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.Tags;
import quiz.mobile.hiliti.com.hiltimobileapp.constants.UrlEndpoints;
import quiz.mobile.hiliti.com.hiltimobileapp.json.Requestor;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.model.ViewModel;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.AnsweredCorrect;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
import quiz.mobile.hiliti.com.hiltimobileapp.task.ResultAsyncTask;
import quiz.mobile.hiliti.com.hiltimobileapp.task.TrainingAsyncTask;


public class ResultActivity extends AppCompatActivity implements AnsweredCorrectCallBackListener/*implements RecyclerViewAdapterResult.OnItemClickListener*/ {


    private View content;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterResult recyclerViewAdapter;
    SharedPreferences sharedPreferences=HiltiApplication.getAppContext().getSharedPreferences(Tags.PREF_NAME, MODE_PRIVATE);
    Button btnExit,testScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initToolbar();
        initRecyclerView();
        content = findViewById(R.id.resultview);
        ArrayList<Question> question = (ArrayList<Question>)getIntent().getSerializableExtra("QuestionList");
        recyclerViewAdapter.setViewModels(question);
        //calculateScore(question);
        testScore = (Button)findViewById(R.id.btn_displayScore);
        testScore.setText("Test Score: "+calculateScore(question));
        if(question.size()>0) {
            try {
                URL url = collectAllRightAnswers(question);
                URL updateScoreUrl = new URL(updateUserURL());
                new ResultAsyncTask(this).execute(url,updateScoreUrl); // pass URL ARRAY
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
       // / if (jsonResponse.isEmpty()) new QuestionAsyncTask(this).execute();

        btnExit = (Button) findViewById(R.id.btn_exitLeaderboard);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyler_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setRecyclerAdapter(recyclerView);
    }



    private void setRecyclerAdapter(RecyclerView recyclerView) {
        recyclerViewAdapter = new RecyclerViewAdapterResult();
        //recyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.m("count of adapters" + recyclerViewAdapter.getItemCount());
    }

    public String calculateScore(ArrayList<Question> questions)
    {
        int score=0;
        for(Question q : questions) {

            if(q.getCorrectAns().equalsIgnoreCase(q.getAnswerByUser()))
            {
                score =score + q.getDifficulty();

            }
        }
        Log.m("final score is " + score);
        int totalScore = sharedPreferences.getInt(Tags.TOTAL_SCORE, 0) + score;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.m("current score is "+sharedPreferences.getInt(Tags.TOTAL_SCORE,0));
        editor.putInt(Tags.TOTAL_SCORE, totalScore);
        editor.commit();
        Log.m("updated score is "+ sharedPreferences.getInt(Tags.TOTAL_SCORE, 0));

    return String.valueOf(score);
}
    public URL collectAllRightAnswers(ArrayList<Question> questions) throws MalformedURLException {
        Question question = null;
        ArrayList<String> questionNumbers = new ArrayList<String>();
        for (int i=0; i<questions.size();i++){
            question = questions.get(i);
            if(question.getAnswerByUser().equalsIgnoreCase(question.getCorrectAns())){
                questionNumbers.add(String.valueOf(question.getQid()));
            }
        }

        String urlAsString = UrlEndpoints.API_SERVER+UrlEndpoints.URL_UPDATE_ALL_ANSWERS+
                UrlEndpoints.URL_CHAR_QUESTION+UrlEndpoints.EMP_ID_PARAM_ANSWERED+sharedPreferences.getInt(Tags.EMP_ID,4)
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.Q_ID_PARAM_ANSWERED_+android.text.TextUtils.join(",", questionNumbers);;
        Log.m("Url is"+urlAsString);
        return new URL(urlAsString);
    }
    public String updateUserURL() throws MalformedURLException, UnsupportedEncodingException {
        String url =UrlEndpoints.URL_USER_PROFILE_UPDATE+UrlEndpoints.URL_CHAR_QUESTION+
                UrlEndpoints.EMP_ID_PARAM_ANSWERED+ sharedPreferences.getInt(Tags.EMP_ID, 0)+
                UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_PASSWORD+sharedPreferences.getString(Tags.PASSWORD, "")
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_DISPLAY_PIC+sharedPreferences.getString(Tags.PROFILE_PIC,"")
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_FIRST_NAME+sharedPreferences.getString(Tags.FIRST_NAME,"")
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_LAST_NAME+sharedPreferences.getString(Tags.LAST_NAME,"")
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_MIDDLE_NAME+sharedPreferences.getString(Tags.MIDDLE_NAME,"")
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_DEPARTMENT+sharedPreferences.getString(Tags.DEPARTMENT,"")
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_AS_OF_DATE+URLEncoder.encode(sharedPreferences.getString(Tags.AS_OF_DATE, ""), UrlEndpoints.URL_ENCODER)
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_EMAIL+sharedPreferences.getString(Tags.EMAIL,"")
                +UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.PARAM_USER_PROF_UP_TOTAL_SCORE+sharedPreferences.getInt(Tags.TOTAL_SCORE, 0);Log.m("Url is "+ url);
        Log.m("ürl is "+ url);
        return url;
    }
    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.resultToolbar);
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
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getAnsweredCorrectUpdateResponses(ArrayList<String> answeredCorrectResponse) {
        for (int i=0; i< answeredCorrectResponse.size(); i++){
            Log.m("answeredCorrect response"+ answeredCorrectResponse.get(i));
        }
    }
}
