package quiz.mobile.hiliti.com.hiltimobileapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import javax.xml.datatype.Duration;

import quiz.mobile.hiliti.com.hiltimobileapp.adapter.RecyclerViewAdapter;
import quiz.mobile.hiliti.com.hiltimobileapp.adapter.RecyclerViewAdapterResult;
import quiz.mobile.hiliti.com.hiltimobileapp.callbacks.QuestionsCallBackListener;
import quiz.mobile.hiliti.com.hiltimobileapp.logging.Log;
import quiz.mobile.hiliti.com.hiltimobileapp.model.ViewModel;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.Question;
import quiz.mobile.hiliti.com.hiltimobileapp.pojo.TrainingPojo;
import quiz.mobile.hiliti.com.hiltimobileapp.task.QuestionAsyncTask;
import quiz.mobile.hiliti.com.hiltimobileapp.task.TrainingAsyncTask;



public class ResultActivity extends AppCompatActivity implements RecyclerViewAdapterResult.OnItemClickListener, QuestionsCallBackListener {


    private View content;
    private RecyclerView recyclerView;
    private RecyclerViewAdapterResult recyclerViewAdapter;
    private ArrayList<Question> jsonResponse = new ArrayList<Question>();
    private ArrayList<ViewModel> viewModels = new ArrayList<ViewModel>();

    ViewModel viewModel = null;
    Question Qpojo = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        initRecyclerView();
        content = findViewById(R.id.resultview);
        if (jsonResponse.isEmpty()) new QuestionAsyncTask(this).execute();
    }

    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyler_result);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setRecyclerAdapter(recyclerView);
    }

    private void setRecyclerAdapter(RecyclerView recyclerView) {
        recyclerViewAdapter = new RecyclerViewAdapterResult();
        recyclerViewAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.m("count of adapters" + recyclerViewAdapter.getItemCount());
    }



    @Override
    public void onItemClick(View view, Question Qpojo) {
      //  ToolTrainingActivity.navigate(this, view.findViewById(R.id.networkImageView), trainingPojo);
        /*Snackbar.make(ontent,trainingPojo.getTitle()+ " pressed",Snackbar.LENGTH_LONG).show();*/
        Toast.makeText(getApplicationContext(),"seected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void getQuestionsList(ArrayList<Question> Qpojo) {
        jsonResponse = Qpojo;
        recyclerViewAdapter.setViewModels(jsonResponse);
    }


}
