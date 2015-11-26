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

import java.net.URL;
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
    private ArrayList<Question> jsonResponse = new ArrayList<Question>();
    private ArrayList<ViewModel> viewModels = new ArrayList<ViewModel>();
    SharedPreferences sharedPreferences=HiltiApplication.getAppContext().getSharedPreferences(Tags.PREF_NAME, MODE_PRIVATE);
    ArrayList<String> answeredCorrectsUrls = new ArrayList<String>();
    ViewModel viewModel = null;
    Question Qpojo = null;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initToolbar();
        initRecyclerView();
        content = findViewById(R.id.resultview);
        ArrayList<Question> question = (ArrayList<Question>)getIntent().getSerializableExtra("QuestionList");
      //  ArrayList<String> answers=(ArrayList<String>)getIntent().getSerializableExtra("AnswerList");
        recyclerViewAdapter.setViewModels(question);
        calculateScore(question);
        collectAllRightAnswers(question);
        if(question.size()>0) {
            new ResultAsyncTask(this).execute(answeredCorrectsUrls.toArray(new URL[answeredCorrectsUrls.size()])); // pass URL ARRAY
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

    public void calculateScore(ArrayList<Question> questions)
    {
        int score=0;
        for(Question q : questions) {

            if(q.getCorrectAns().equalsIgnoreCase(q.getAnswerByUser()))
            {
                score =score + q.getDifficulty();
            }
        }

        String qscore=String.valueOf(score);

    }
    public void collectAllRightAnswers(ArrayList<Question> questions){
        AnsweredCorrect answeredCorrect = null;
        Question question = null;
        String url = null;
        for (int i=0; i<questions.size();i++){
            question = questions.get(i);
            if(question.getAnswerByUser().equalsIgnoreCase(question.getCorrectAns())){

                url = UrlEndpoints.API_SERVER+UrlEndpoints.ANSWERED_CORRECT_URL+UrlEndpoints.URL_CHAR_QUESTION+UrlEndpoints.Q_ID_PARAM_ANSWERED_+question.getQid()+UrlEndpoints.URL_CHAR_AMEPERSAND+UrlEndpoints.EMP_ID_PARAM_ANSWERED+sharedPreferences.getInt(Tags.EMP_ID,0);
                answeredCorrectsUrls.add(url);
            }
        }



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
